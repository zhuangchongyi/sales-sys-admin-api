package com.dc.project.open.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 客户订单收货地址表
 *
 * @author zhuangcy
 * @since 2020-11-28
 */
@ApiModel("客户订单收货地址表")
public class OrderAddress {

    /**
     * 主键
     */
    @TableId(value = "pk_id", type = IdType.AUTO)
    private Long pkId;

    /**
     * 客户id
     */
    @ApiModelProperty("客户id")
    private Integer clienteleId;

    /**
     * 订单id
     */
    @ApiModelProperty("订单id")
    private Integer orderId;

    /**
     * 订单号
     */
    @ApiModelProperty("订单号")
    private String orderNum;

    /**
     * 订单日期
     */
    @ApiModelProperty("订单日期")
    private Date orderTime;

    /**
     * 省份
     */
    @ApiModelProperty("省份")
    private String province;

    /**
     * 市
     */
    @ApiModelProperty("市")
    private String city;

    /**
     * 区、县
     */
    @ApiModelProperty("区、县")
    private String district;

    /**
     * 详细地址
     */
    @ApiModelProperty("详细地址")
    private String detail;

    /**
     * 地址
     */
    @ApiModelProperty("地址")
    private String address;

    /**
     * 收件手机号
     */
    @ApiModelProperty("收件手机号")
    private String phone;

    /**
     * 收件人
     */
    @ApiModelProperty("收件人")
    private String recipient;

    /**
     * 邮政编码
     */
    @ApiModelProperty("邮政编码")
    private String zipCode;

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public Integer getClienteleId() {
        return clienteleId;
    }

    public void setClienteleId(Integer clienteleId) {
        this.clienteleId = clienteleId;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @Override
    public String toString() {
        return "OrderAddress{" +
                "pkId=" + pkId +
                ", clienteleId=" + clienteleId +
                ", orderId=" + orderId +
                ", orderNum=" + orderNum +
                ", orderTime=" + orderTime +
                ", province=" + province +
                ", city=" + city +
                ", district=" + district +
                ", detail=" + detail +
                ", address=" + address +
                ", phone=" + phone +
                ", recipient=" + recipient +
                "}";
    }
}
