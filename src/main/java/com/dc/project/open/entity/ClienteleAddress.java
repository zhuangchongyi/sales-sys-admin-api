package com.dc.project.open.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 客户收货地址表
 *
 * @author zhuangcy
 * @since 2020-11-28
 */
@ApiModel("客户收货地址表")
public class ClienteleAddress {

    /**
     * 主键
     */
    @TableId(value = "addr_id", type = IdType.AUTO)
    private Integer addrId;

    /**
     * 客户id
     */
    @ApiModelProperty(value = "客户id")
    private Integer clienteleId;

    /**
     * 是否默认地址（1表示默认）
     */
    @ApiModelProperty(value = "是否默认地址")
    private Integer defAddress;

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

    public Integer getAddrId() {
        return addrId;
    }

    public void setAddrId(Integer addrId) {
        this.addrId = addrId;
    }

    public Integer getClienteleId() {
        return clienteleId;
    }

    public void setClienteleId(Integer clienteleId) {
        this.clienteleId = clienteleId;
    }

    public Integer getDefAddress() {
        return defAddress;
    }

    public void setDefAddress(Integer defAddress) {
        this.defAddress = defAddress;
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
        return "ClienteleAddress{" +
                "addrId=" + addrId +
                ", clienteleId=" + clienteleId +
                ", defAddress=" + defAddress +
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
