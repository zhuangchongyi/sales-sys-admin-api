package com.dc.project.finance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dc.common.vo.BaseEntity;

import java.math.BigDecimal;

/**
 * 应收核销明细表
 *
 * @author zhuangcy
 * @since 2020-10-26
 */
public class SysWriteoffSub extends BaseEntity {

    /**
     * 主键
     */
    @TableId(value = "writeoff_sub_id", type = IdType.AUTO)
    private Integer writeoffSubId;

    /**
     * 子表id
     */
    private Integer writeoffId;

    /**
     * 收款id
     */
    private Integer receiptId;

    /**
     * 收款单号
     */
    private String receiptNum;
    private BigDecimal receiptPrice;

    /**
     * 核销前收款金额
     */
    private BigDecimal frontReceiptPrice;

    /**
     * 核销后收款金后
     */
    private BigDecimal backReceiptPrice;

    /**
     * 应收款id
     */
    private Integer receivableId;

    /**
     * 应收款单号
     */
    private String receivableNum;

    private BigDecimal receivablePrice;
    /**
     * 核销前应收款金额
     */
    private BigDecimal frontReceivablePrice;

    /**
     * 核销后应收款金额
     */
    private BigDecimal backReceivablePrice;

    /**
     * 实际核销金额
     */
    private BigDecimal writeoffPrice;


    public BigDecimal getReceivablePrice() {
        return receivablePrice;
    }

    public void setReceivablePrice(BigDecimal receivablePrice) {
        this.receivablePrice = receivablePrice;
    }

    public BigDecimal getReceiptPrice() {
        return receiptPrice;
    }

    public void setReceiptPrice(BigDecimal receiptPrice) {
        this.receiptPrice = receiptPrice;
    }

    public Integer getWriteoffSubId() {
        return writeoffSubId;
    }

    public void setWriteoffSubId(Integer writeoffSubId) {
        this.writeoffSubId = writeoffSubId;
    }

    public Integer getWriteoffId() {
        return writeoffId;
    }

    public void setWriteoffId(Integer writeoffId) {
        this.writeoffId = writeoffId;
    }

    public Integer getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Integer receiptId) {
        this.receiptId = receiptId;
    }

    public String getReceiptNum() {
        return receiptNum;
    }

    public void setReceiptNum(String receiptNum) {
        this.receiptNum = receiptNum;
    }

    public BigDecimal getFrontReceiptPrice() {
        return frontReceiptPrice;
    }

    public void setFrontReceiptPrice(BigDecimal frontReceiptPrice) {
        this.frontReceiptPrice = frontReceiptPrice;
    }

    public BigDecimal getBackReceiptPrice() {
        return backReceiptPrice;
    }

    public void setBackReceiptPrice(BigDecimal backReceiptPrice) {
        this.backReceiptPrice = backReceiptPrice;
    }

    public Integer getReceivableId() {
        return receivableId;
    }

    public void setReceivableId(Integer receivableId) {
        this.receivableId = receivableId;
    }

    public String getReceivableNum() {
        return receivableNum;
    }

    public void setReceivableNum(String receivableNum) {
        this.receivableNum = receivableNum;
    }

    public BigDecimal getFrontReceivablePrice() {
        return frontReceivablePrice;
    }

    public void setFrontReceivablePrice(BigDecimal frontReceivablePrice) {
        this.frontReceivablePrice = frontReceivablePrice;
    }

    public BigDecimal getBackReceivablePrice() {
        return backReceivablePrice;
    }

    public void setBackReceivablePrice(BigDecimal backReceivablePrice) {
        this.backReceivablePrice = backReceivablePrice;
    }

    public BigDecimal getWriteoffPrice() {
        return writeoffPrice;
    }

    public void setWriteoffPrice(BigDecimal writeoffPrice) {
        this.writeoffPrice = writeoffPrice;
    }
}
