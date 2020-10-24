package com.dc.common.constant;

import com.dc.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author zhuangcy
 * @Description 销售状态常量处理类
 * @Date 2020/9/18 15:04
 */
@Slf4j
public class SalesConstant {
    /**
     * 销售报价单号前缀
     */
    public static final String SALES_QUOTATION_NO = "QN";
    /**
     * 销售订单单号前缀
     */
    public static final String SALES_ORDER_NO = "ON";
    /**
     * 销售发货单号前缀
     */
    public static final String SALES_SHIPMENTS_NO = "DN";
    /**
     * 销售发货单号前缀
     */
    public static final String SALES_SIGNBACK_NO = "SN";
    /**
     * 销售发货单号前缀
     */
    public static final String SALES_RETURNS_NO = "RN";
    /**
     * 应收款单号前缀
     */
    public static final String FINANCE_RECEIVABLE_NO = "AR";
    /**
     * 应收款单号前缀
     */
    public static final String FINANCE_RECEIPT_NO = "CN";
    /**
     * 保存状态
     */
    public static final String SAVE = "0";
    /**
     * 提交状态
     */
    public static final String SUBMIT = "1";
    /**
     * 收回状态
     */
    public static final String NO_SUBMIT = "2";
    /**
     * 审核状态
     */
    public static final String AUDIT = "3";
    /**
     * 反审核状态
     */
    public static final String NO_AUDIT = "4";
    /**
     * 特批状态
     */
    public static final String RATIFY = "5";
    /**
     * 取消特批
     */
    public static final String NO_RATIFY = "6";
    /**
     * 关闭
     */
    public static final String CLOSE = "7";
    /**
     * 发货
     */
    public static final String SHIPMENT = "8";

    /**
     * 校验审核状态
     *
     * @param targetStatus 修改状态
     * @param sourceStatus 当前状态
     */
    public static void verifyAuditStatus(String targetStatus, String sourceStatus) {
        if (AUDIT.equals(targetStatus)) {//审核
            if (AUDIT.equals(sourceStatus))
                throw new ServiceException("已审核，不允许重复操作");
            else if (CLOSE.equals(sourceStatus))
                throw new ServiceException("订单已关闭");
            else if (RATIFY.equals(sourceStatus))
                throw new ServiceException("已特批无需审核");
            else if (!NO_AUDIT.equals(sourceStatus) && !NO_RATIFY.equals(sourceStatus) && !SUBMIT.equals(sourceStatus))
                throw new ServiceException("审核失败，未提交不允许审核");
        } else if (NO_AUDIT.equals(targetStatus)) {
            if (NO_AUDIT.equals(sourceStatus))
                throw new ServiceException("已取消审核，不允许重复操作");
            else if (CLOSE.equals(sourceStatus))
                throw new ServiceException("订单已关闭");
            else if (!RATIFY.equals(sourceStatus) && !AUDIT.equals(sourceStatus))
                throw new ServiceException("取消审核失败，请先审核");
        } else if (RATIFY.equals(targetStatus)) {
            if (RATIFY.equals(sourceStatus))
                throw new ServiceException("已特批，不允许重复操作");
            else if (CLOSE.equals(sourceStatus))
                throw new ServiceException("订单已关闭");
            else if (!NO_RATIFY.equals(sourceStatus) && !NO_AUDIT.equals(sourceStatus) && !AUDIT.equals(sourceStatus) && !SUBMIT.equals(sourceStatus))
                throw new ServiceException("特批失败，请先提交");
        } else if (NO_RATIFY.equals(targetStatus)) {
            if (NO_AUDIT.equals(sourceStatus))
                throw new ServiceException("已取消特批，不允许重复操作");
            else if (CLOSE.equals(sourceStatus))
                throw new ServiceException("订单已关闭");
            else if (!RATIFY.equals(sourceStatus) && !AUDIT.equals(sourceStatus))
                throw new ServiceException("取消特批失败，请先特批");
        } else if (CLOSE.equals(targetStatus)) {
            log.info("订单关闭");
        }
    }

    /**
     * 校验提交状态
     *
     * @param targetStatus 当前修改状态
     * @param number       单据号
     * @param sourceStatus 原始状态
     */
    public static void verifySubmitStatus(String targetStatus, String number, String sourceStatus) {
        if (SalesConstant.SUBMIT.equals(targetStatus)) {
            if (SalesConstant.SUBMIT.equals(sourceStatus)) {
                throw new ServiceException(String.format("%s 已提交", number));
            } else if (SalesConstant.AUDIT.equals(sourceStatus)) {
                throw new ServiceException(String.format("%s 已审核", number));
            } else if (SalesConstant.CLOSE.equals(sourceStatus)) {
                throw new ServiceException(String.format("%s 已关闭", number));
            }
        } else {
            if (SalesConstant.NO_SUBMIT.equals(sourceStatus)) {
                throw new ServiceException(String.format("%s 已收回", number));
            } else if (SalesConstant.AUDIT.equals(sourceStatus)) {
                throw new ServiceException(String.format("%s 已审核", number));
            } else if (SalesConstant.CLOSE.equals(sourceStatus)) {
                throw new ServiceException(String.format("%s 已关闭", number));
            }
        }
    }

    /**
     * 校验是否可以删除
     *
     * @param status
     */
    public static void verifyDeleteStatus(String status) {
        if (AUDIT.equals(status) || NO_AUDIT.equals(status) || SUBMIT.equals(status)) {
            throw new ServiceException("请收回再进行删除");
        }
    }
}
