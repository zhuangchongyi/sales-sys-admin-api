package com.dc.project.finance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.common.constant.SalesConstant;
import com.dc.common.exception.ServiceException;
import com.dc.common.utils.BigDecimalUtil;
import com.dc.common.utils.UserSecurityUtil;
import com.dc.project.finance.dao.SysReceiptDao;
import com.dc.project.finance.entity.SysReceipt;
import com.dc.project.finance.service.ISysReceiptService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 财务收款表 服务实现类
 *
 * @author zhuangcy
 * @since 2020-10-23
 */
@Service
public class SysReceiptServiceImpl extends ServiceImpl<SysReceiptDao, SysReceipt> implements ISysReceiptService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean submit(Integer[] ids, String status) {
        if (ids.length == 0 || StringUtils.isEmpty(status))
            throw new ServiceException("未选择数据");
        List<SysReceipt> list = this.list(new QueryWrapper<SysReceipt>().select("receipt_id,receipt_num,status").in("receipt_id", Arrays.asList(ids)));
        if (list == null || list.isEmpty())
            throw new ServiceException("操作失败");
        List<Integer> idList = new ArrayList<>();
        for (SysReceipt receipt : list) {
            SalesConstant.verifySubmitStatus(status, receipt.getReceiptNum(), receipt.getStatus());
            idList.add(receipt.getReceiptId());
        }
        UpdateWrapper<SysReceipt> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status", status).in("receipt_id", idList);
        return this.update(updateWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean audit(SysReceipt receipt) {
        SysReceipt one = this.getById(receipt.getReceiptId());
        if (SalesConstant.NO_AUDIT.equals(receipt.getStatus()) && BigDecimalUtil.compareTo(one.getHasVerificaPrice(), BigDecimalUtil.ZERO) > 0) {
            throw new ServiceException("已核销，无法反审核");
        }
        if (BigDecimalUtil.compareTo(one.getHasVerificaPrice(), BigDecimalUtil.ZERO) > 0) {
            throw new ServiceException("已核销不允许操作");
        }
        SalesConstant.verifyAuditStatus(receipt.getStatus(), one.getStatus());
        receipt.setAuditTime(new Date());
        receipt.setAuditBy(UserSecurityUtil.getUsername());
        return this.updateById(receipt);
    }

    @Override
    public boolean delete(Integer id) {
        SysReceipt one = this.getById(id);
        if (one == null) {
            throw new ServiceException("未找到收款单");
        }
        if (BigDecimalUtil.compareTo(one.getHasVerificaPrice(), BigDecimalUtil.ZERO) > 0) {
            throw new ServiceException("已核销不允许删除");
        }
        if (!SalesConstant.NO_SUBMIT.equals(one.getStatus()) || !SalesConstant.SAVE.equals(one.getStatus())) {
            if (!this.removeById(id)) {
                throw new ServiceException();
            }
        } else {
            throw new ServiceException("请收回在删除");
        }
        return true;
    }

    @Override
    public BigDecimal findReceiptPriceByClienteleId(Integer clienteleId) {
        SysReceipt receipt = new SysReceipt();
        receipt.setClienteleId(clienteleId);
        receipt.setStatus(SalesConstant.AUDIT);
        return baseMapper.getReceiptPrice(receipt);
    }

    @Override
    public List<SysReceipt> getClienteleReceipt(SysReceipt receipt) {
        return baseMapper.getClienteleReceipt(receipt);
    }

    @Override
    public List<SysReceipt> getClienteleReceiptList(SysReceipt receipt) {
        return baseMapper.getClienteleReceiptList(receipt);
    }
}
