package com.dc.project.open.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderVo implements Serializable {
    private static final long serialVersionUID = 3114544060701711268L;
    private Integer orderId;
    private String orderNum;
    private String signNum;
    private String shipmentsNum;
    private Date signTime;
    private Date shipmentsTime;
    private Date orderTime;
    private Integer clienteleId;
    private String clienteleNum;
    private String clienteleName;
    private BigDecimal totalPrice;
    private String address;
    private String mobilephone;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSignNum() {
        return signNum;
    }

    public void setSignNum(String signNum) {
        this.signNum = signNum;
    }

    public String getShipmentsNum() {
        return shipmentsNum;
    }

    public void setShipmentsNum(String shipmentsNum) {
        this.shipmentsNum = shipmentsNum;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public Date getShipmentsTime() {
        return shipmentsTime;
    }

    public void setShipmentsTime(Date shipmentsTime) {
        this.shipmentsTime = shipmentsTime;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getClienteleId() {
        return clienteleId;
    }

    public void setClienteleId(Integer clienteleId) {
        this.clienteleId = clienteleId;
    }

    public String getClienteleNum() {
        return clienteleNum;
    }

    public void setClienteleNum(String clienteleNum) {
        this.clienteleNum = clienteleNum;
    }

    public String getClienteleName() {
        return clienteleName;
    }

    public void setClienteleName(String clienteleName) {
        this.clienteleName = clienteleName;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
