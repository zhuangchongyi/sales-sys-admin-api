package com.dc.project.finance.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.lang.annotation.RepeatSubmit;
import com.dc.common.constant.SalesConstant;
import com.dc.common.exception.ServiceException;
import com.dc.common.utils.CodeUtil;
import com.dc.common.vo.R;
import com.dc.project.finance.entity.SysReceipt;
import com.dc.project.finance.service.ISysReceiptService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 财务收款表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-10-23
 */
@RestController
@RequestMapping("/finance/receipt")
public class SysReceiptController {
    @Autowired
    private ISysReceiptService receiptService;

    @RequiresPermissions("finance:receipt:list")
    @GetMapping
    public R page(Page page, SysReceipt receipt) {
        QueryWrapper<SysReceipt> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(receipt.getReceiptNum()), "receipt_num", receipt.getReceiptNum())
                .or().like(StringUtils.isNotEmpty(receipt.getClienteleNum()), "clientele_num", receipt.getClienteleNum())
                .or().like(StringUtils.isNotEmpty(receipt.getClienteleName()), "clientele_name", receipt.getClienteleName());
        queryWrapper.orderByDesc("create_time");
        return R.success().data(receiptService.page(page, queryWrapper));
    }

    @GetMapping("/{id}")
    public R get(@PathVariable Integer id) {
        QueryWrapper<SysReceipt> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(SysReceipt.class,
                info -> !info.getColumn().equals("create_time") && !info.getColumn().equals("create_by") &&
                        !info.getColumn().equals("audit_time") && !info.getColumn().equals("audit_by"));
        queryWrapper.eq("receipt_id", id);
        return R.success().data(receiptService.getOne(queryWrapper));
    }

    @RepeatSubmit
    @RequiresPermissions("finance:receipt:add")
    @PostMapping
    public R add(@RequestBody SysReceipt receipt) {
        receipt.setReceiptNum(CodeUtil.getCode(SalesConstant.FINANCE_RECEIPT_NO));
        receipt.setStatus(SalesConstant.SAVE);
        if (!receiptService.save(receipt)) {
            throw new SecurityException();
        }
        return R.success().data(receipt.getReceiptNum());
    }

    @RepeatSubmit
    @RequiresPermissions("finance:receipt:edit")
    @PutMapping
    public R edit(@RequestBody SysReceipt receipt) {
        if (SalesConstant.SAVE.equals(receipt.getStatus()) || SalesConstant.NO_SUBMIT.equals(receipt.getStatus())) {
            if (!receiptService.updateById(receipt)) {
                throw new ServiceException();
            }
        }
        return R.success();
    }

    @RepeatSubmit
    @RequiresPermissions("finance:receipt:delete")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        return R.success().data(receiptService.delete(id));
    }

    @RepeatSubmit
    @RequiresPermissions("finance:receipt:submit")
    @PutMapping("/submit/{status}")
    public R submit(@RequestBody Integer[] ids, @PathVariable String status) {
        return R.success().data(receiptService.submit(ids, status));
    }

    @RepeatSubmit
    @RequiresPermissions("finance:receipt:audit")
    @PutMapping("/audit")
    public R audit(@RequestBody SysReceipt receipt) {
        return R.success().data(receiptService.audit(receipt));
    }

    /**
     * 查询客户已审核的收款单
     *
     * @param receipt
     * @return
     */
    @GetMapping("/clientele")
    public R getClienteleReceipt(SysReceipt receipt) {
        return R.success().data(receiptService.getClienteleReceipt(receipt));
    }

    /**
     * 查询订单收款明细
     *
     * @param receipt
     * @return
     */
    @GetMapping("/clienteleList")
    public R getClienteleReceiptList(SysReceipt receipt) {
        return R.success().data(receiptService.getClienteleReceiptList(receipt));
    }
}

