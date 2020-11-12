package com.dc.project.finance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.common.constant.CustomConstant;
import com.dc.common.constant.SalesConstant;
import com.dc.common.exception.ServiceException;
import com.dc.common.lang.annotation.DataScope;
import com.dc.common.utils.BeanUtil;
import com.dc.common.utils.BigDecimalUtil;
import com.dc.common.utils.ObjectMapperUtil;
import com.dc.project.finance.dao.SysWriteoffDao;
import com.dc.project.finance.entity.SysReceipt;
import com.dc.project.finance.entity.SysReceivable;
import com.dc.project.finance.entity.SysWriteoff;
import com.dc.project.finance.entity.SysWriteoffSub;
import com.dc.project.finance.service.ISysReceiptService;
import com.dc.project.finance.service.ISysReceivableService;
import com.dc.project.finance.service.ISysWriteoffService;
import com.dc.project.finance.service.ISysWriteoffSubService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 应收核销表 服务实现类
 *
 * @author zhuangcy
 * @since 2020-10-26
 */
@Service
public class SysWriteoffServiceImpl extends ServiceImpl<SysWriteoffDao, SysWriteoff> implements ISysWriteoffService {
    @Autowired
    private ISysWriteoffSubService writeoffSubService;
    @Autowired
    private ISysReceiptService receiptService;
    @Autowired
    private ISysReceivableService receivableService;

    @DataScope(userColumn = "create_id")
    @Override
    public IPage<SysWriteoff> page(Page<SysWriteoff> page, SysWriteoff writeoff) {
        QueryWrapper<SysWriteoff> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(writeoff.getClienteleNum()), "clientele_num", writeoff.getClienteleNum())
                .like(StringUtils.isNotEmpty(writeoff.getClienteleName()), "clientele_name", writeoff.getClienteleName())
                .apply(writeoff.getParams().get(CustomConstant.DATA_SCOPE).toString().replaceFirst("and", ""))
                .orderByDesc("create_time");
        return this.page(page, queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertAndUpdate(Map<String, Object> formMap) throws InvocationTargetException, IllegalAccessException {
        Object clienteleForm = formMap.get("clienteleForm");
        Object selectionReceipts = formMap.get("selectionReceipts");
        Object selectionReceivables = formMap.get("selectionReceivables");
        if (null == clienteleForm || null == selectionReceipts || null == selectionReceivables) {
            throw new ServiceException();
        }
        SysWriteoff sysWriteoff = new SysWriteoff();
        BeanUtil.register();
        BeanUtils.populate(sysWriteoff, ObjectMapperUtil.toObject(clienteleForm.toString(), Map.class));
        if (null == sysWriteoff.getClienteleId()) {
            throw new ServiceException("未选择客户");
        }
        if (this.save(sysWriteoff)) {
            List<Map<String, Object>> receiptMapList = ObjectMapperUtil.toObject(selectionReceipts.toString(), List.class);
            List<Map<String, Object>> receivableMapList = ObjectMapperUtil.toObject(selectionReceivables.toString(), List.class);
            if (receiptMapList.isEmpty() || receivableMapList.isEmpty()) {
                throw new ServiceException("未选择收款单或应收款单");
            }
            List<SysReceipt> receiptList = new ArrayList<>();
            List<SysReceivable> receivableList = new ArrayList<>();
            for (Map<String, Object> map : receiptMapList) {
                SysReceipt receipt = new SysReceipt();
                BeanUtils.populate(receipt, map);
                receiptList.add(receipt);
            }
            for (Map<String, Object> map : receivableMapList) {
                SysReceivable receivable = new SysReceivable();
                BeanUtils.populate(receivable, map);
                receivableList.add(receivable);
            }
            insertWriteoffSub(sysWriteoff, receiptList, receivableList);
        } else {
            throw new ServiceException("保存失败");
        }
        return true;
    }

    private void insertWriteoffSub(SysWriteoff sysWriteoff, List<SysReceipt> receiptList, List<SysReceivable> receivableList) {
        BigDecimal frontVerificaPrice = BigDecimalUtil.ZERO;
        for (SysReceipt receipt : receiptList) {
            for (SysReceivable receivable : receivableList) {
                // 收款可核销金额 = 收款金额 - 已核销金额
                BigDecimal receiptTotal = BigDecimalUtil.sub(receipt.getReceiptPrice(), receipt.getHasVerificaPrice());
                // 应收款可核销金额
                BigDecimal receivableTotalPrice = BigDecimalUtil.sub(receivable.getTotalPrice(), receivable.getHasVerificaPrice());
                // 应收款本次核销金额
                BigDecimal verificaPrice = receivable.getVerificaPrice();
                if (BigDecimalUtil.compareTo(receivableTotalPrice, BigDecimalUtil.ZERO) == 0) {
                    continue;
                } else if (BigDecimalUtil.compareTo(receiptTotal, BigDecimalUtil.ZERO) == 0) {
                    break;
                } else if (BigDecimalUtil.compareTo(verificaPrice, receivableTotalPrice) > 0) {
                    if (BigDecimalUtil.compareTo(frontVerificaPrice, BigDecimalUtil.ZERO) > 0) {
                        verificaPrice = frontVerificaPrice;
                    } else {
                        throw new ServiceException(String.format("%s应收款本次核销金额大于可核销金额", receivable.getReceivableNum()));
                    }
                }
                // 收款可核销金额小于等于应收款本次核销金额
                SysWriteoffSub writeoffSub = new SysWriteoffSub();
                writeoffSub.setWriteoffId(sysWriteoff.getWriteoffId());
                // 收款
                writeoffSub.setReceiptId(receipt.getReceiptId());
                writeoffSub.setReceiptNum(receipt.getReceiptNum());
                writeoffSub.setReceiptPrice(receipt.getReceiptPrice());
                // 应收款
                writeoffSub.setReceivableId(receivable.getReceivableId());
                writeoffSub.setReceivableNum(receivable.getReceivableNum());
                writeoffSub.setReceivablePrice(receivable.getTotalPrice());
                if (BigDecimalUtil.compareTo(receiptTotal, verificaPrice) <= 0) {// 收款金额小于本次核销金额
                    writeoffSub.setFrontReceiptPrice(receiptTotal);
                    writeoffSub.setFrontReceivablePrice(receivableTotalPrice);
                    writeoffSub.setBackReceiptPrice(BigDecimalUtil.ZERO);
                    writeoffSub.setBackReceivablePrice(BigDecimalUtil.sub(receivableTotalPrice, receiptTotal));
                    writeoffSub.setWriteoffPrice(receiptTotal);
                    receipt.setHasVerificaPrice(BigDecimalUtil.add(receipt.getHasVerificaPrice(), receiptTotal));
                    receivable.setHasVerificaPrice(BigDecimalUtil.add(receivable.getHasVerificaPrice(), receiptTotal));
                    frontVerificaPrice = BigDecimalUtil.sub(verificaPrice, receiptTotal);
                    // 保存
                    if (!writeoffSubService.save(writeoffSub)) throw new ServiceException("保存失败");
                    break; // 跳出循环
                } else if (BigDecimalUtil.compareTo(receiptTotal, verificaPrice) > 0) { // 收款金额大于本次核销金额
                    writeoffSub.setFrontReceiptPrice(receiptTotal);
                    writeoffSub.setBackReceiptPrice(BigDecimalUtil.sub(receiptTotal, verificaPrice));
                    writeoffSub.setFrontReceivablePrice(verificaPrice);
                    writeoffSub.setBackReceivablePrice(BigDecimalUtil.ZERO);
                    writeoffSub.setWriteoffPrice(verificaPrice);
                    receipt.setHasVerificaPrice(BigDecimalUtil.add(receipt.getHasVerificaPrice(), verificaPrice));
                    receivable.setHasVerificaPrice(verificaPrice);
                    // 保存
                    if (!writeoffSubService.save(writeoffSub)) throw new ServiceException("保存失败");
                } else {
                    throw new ServiceException("核销异常，其他情况");
                }
            }
        }

//        if (!receiptService.updateBatchById(receiptList)&&!receivableService.updateBatchById(receivableList)){
//            throw new ServiceException("核销异常，修改核销金额错误");
//        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(Integer id) {
        SysWriteoff one = this.getById(id);
        if (null == one) throw new ServiceException("已删除");
        if (CustomConstant.YES_STATUS.equals(one.getStatus())) {
            throw new ServiceException("已核销不允许删除");
        }
        QueryWrapper<SysWriteoffSub> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("writeoff_id", id);
        if (!writeoffSubService.remove(queryWrapper)) {
            throw new ServiceException("删除失败");
        }
        if (!this.removeById(id)) {
            throw new ServiceException("删除失败");
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean audit(SysWriteoff writeoff) {
        if (null == writeoff.getWriteoffId() || StringUtils.isEmpty(writeoff.getWriteoffStatus())) {
            throw new ServiceException("核销失败，参数丢失");
        }
        SysWriteoff one = this.getById(writeoff.getWriteoffId());
        if (CustomConstant.YES_STATUS.equals(writeoff.getWriteoffStatus())) {
            if (CustomConstant.YES_STATUS.equals(one.getWriteoffStatus())) throw new ServiceException("已核销");
            List<SysWriteoffSub> receiptPriceList = writeoffSubService.findReceiptPrice(writeoff.getWriteoffId());
            for (SysWriteoffSub sub : receiptPriceList) {
                QueryWrapper<SysReceipt> qw = new QueryWrapper<>();
                qw.select("receipt_id,receipt_num,receipt_price,has_verifica_price,status");
                qw.eq("receipt_id", sub.getReceiptId());
                SysReceipt receipt = receiptService.getOne(qw);
                if (!SalesConstant.AUDIT.equals(receipt.getStatus())) {
                    throw new ServiceException(String.format("核销失败，%s收款单未审核", receipt.getReceiptNum()));
                }
                receipt.setHasVerificaPrice(BigDecimalUtil.add(receipt.getHasVerificaPrice(), sub.getReceiptPrice()));
                if (BigDecimalUtil.compareTo(receipt.getReceiptPrice(), receipt.getHasVerificaPrice()) < 0) {
                    throw new ServiceException(String.format("核销失败，%s收款金额校验异常", receipt.getReceiptNum()));
                }
                if (!receiptService.updateById(receipt)) {
                    throw new ServiceException();
                }
            }
            List<SysWriteoffSub> receivablePriceList = writeoffSubService.findReceivablePrice(writeoff.getWriteoffId());
            for (SysWriteoffSub sub : receivablePriceList) {
                QueryWrapper<SysReceivable> qw = new QueryWrapper<>();
                qw.select("receivable_id,receivable_num,total_price,has_verifica_price,status");
                qw.eq("receivable_id", sub.getReceivableId());
                SysReceivable receivable = receivableService.getOne(qw);
                if (!SalesConstant.AUDIT.equals(receivable.getStatus())) {
                    throw new ServiceException(String.format("核销失败，%s应收款单未审核", receivable.getReceivableNum()));
                }
                receivable.setHasVerificaPrice(BigDecimalUtil.add(receivable.getHasVerificaPrice(), sub.getReceivablePrice()));
                if (BigDecimalUtil.compareTo(receivable.getTotalPrice(), receivable.getHasVerificaPrice()) < 0) {
                    throw new ServiceException(String.format("核销失败，%应收款金额校验异常", receivable.getReceivableNum()));
                }
                if (!receivableService.updateById(receivable)) {
                    throw new ServiceException();
                }
            }
        } else {
            if (CustomConstant.NO_STATUS.equals(one.getWriteoffStatus())) throw new ServiceException("已反核销");
        }
        if (!this.updateById(writeoff)) {
            throw new ServiceException("核销失败");
        }
        return true;
    }
}
