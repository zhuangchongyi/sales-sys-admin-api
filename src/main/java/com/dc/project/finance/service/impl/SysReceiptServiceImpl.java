package com.dc.project.finance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.common.constant.SalesConstant;
import com.dc.common.exception.ServiceException;
import com.dc.common.utils.UserSecurityUtils;
import com.dc.project.finance.dao.SysReceiptDao;
import com.dc.project.finance.entity.SysReceipt;
import com.dc.project.finance.service.ISysReceiptService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        SalesConstant.verifyAuditStatus(receipt.getStatus(), one.getStatus());
        receipt.setAuditTime(new Date());
        receipt.setAuditBy(UserSecurityUtils.getUsername());
        return this.updateById(receipt);
    }

    @Override
    public boolean delete(Integer id) {
        SysReceipt receipt = this.getById(id);
        if (receipt == null) {
            throw new ServiceException("未找到收款单");
        }
        if (SalesConstant.SAVE.equals(receipt.getStatus()) || SalesConstant.NO_SUBMIT.equals(receipt.getStatus())) {
            if (!this.removeById(id)) {
                throw new ServiceException();
            }
        } else {
            throw new ServiceException("请收回在删除");
        }
        return true;
    }
}
