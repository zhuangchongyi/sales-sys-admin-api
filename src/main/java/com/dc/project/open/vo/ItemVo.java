package com.dc.project.open.vo;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@ApiModel("商品信息")
public class ItemVo implements Serializable {
    private static final long serialVersionUID = 81325056113375072L;
    private Integer itemId;
    private String itemNum;
    private String itemName;
    private String specification;
    private String needTorque;
    private String outTorque;
    private BigDecimal price;
    private BigDecimal maxPrice;
    private BigDecimal minPrice;
    private List<String> modelNames;
    private String url;
    private Integer categoryId;
    private String categoryNum;
    private String categoryName;
    private List<String> urls;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryNum() {
        return categoryNum;
    }

    public void setCategoryNum(String categoryNum) {
        this.categoryNum = categoryNum;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemNum() {
        return itemNum;
    }

    public void setItemNum(String itemNum) {
        this.itemNum = itemNum;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getNeedTorque() {
        return needTorque;
    }

    public void setNeedTorque(String needTorque) {
        this.needTorque = needTorque;
    }

    public String getOutTorque() {
        return outTorque;
    }

    public void setOutTorque(String outTorque) {
        this.outTorque = outTorque;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public List<String> getModelNames() {
        return modelNames;
    }

    public void setModelNames(List<String> modelNames) {
        this.modelNames = modelNames;
    }
}
