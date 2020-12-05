package com.dc.project.open.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 购物车表
 *
 * @author zhuangcy
 * @since 2020-11-27
 */
@ApiModel(value = "购物车产品")
public class CartItem {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键id")
    @TableId(value = "cart_item_id", type = IdType.AUTO)
    private Long cartItemId;

    /**
     * 产品id
     */
    @ApiModelProperty(value = "产品id")
    @NotNull(message = "产品id不能为空")
    private Integer itemId;

    /**
     * 产品名称
     */
    @ApiModelProperty(value = "产品名称")
    @NotNull(message = "产品名称不能为空")
    private String itemName;

    /**
     * 产品类别id
     */
    @ApiModelProperty(value = "产品类别id")
    private Integer categoryId;

    /**
     * 产品类别名称
     */
    @ApiModelProperty(value = "产品类别名称")
    private String categoryName;

    /**
     * 封面图
     */
    @ApiModelProperty(value = "产品封面图")
    private String url;

    /**
     * 规格
     */
    @ApiModelProperty(value = "产品规格")
    @NotNull(message = "产品规格不能为空")
    private String specification;

    /**
     * 型号
     */
    @ApiModelProperty(value = "产品型号")
    @NotNull(message = "产品型号不能为空")
    private String modelName;

    /**
     * 价格
     */
    @ApiModelProperty(value = "产品价格")
    @NotNull(message = "产品价格不能为空")
    private BigDecimal price;

    /**
     * 数量
     */
    @ApiModelProperty(value = "产品数量")
    @NotNull(message = "产品数量不能为空")
    private Integer number;

    /**
     * 客户id
     */
    @ApiModelProperty(value = "客户id")
    @NotNull(message = "客户id不能为空")
    private Integer clienteleId;

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getClienteleId() {
        return clienteleId;
    }

    public void setClienteleId(Integer clienteleId) {
        this.clienteleId = clienteleId;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "cartItemId=" + cartItemId +
                ", itemId=" + itemId +
                ", itemName=" + itemName +
                ", categoryId=" + categoryId +
                ", categoryName=" + categoryName +
                ", url=" + url +
                ", specification=" + specification +
                ", modelName=" + modelName +
                ", price=" + price +
                ", number=" + number +
                ", clienteleId=" + clienteleId +
                "}";
    }
}
