package com.dc.project.open.vo;

import com.dc.project.open.entity.CartItem;
import com.dc.project.open.entity.OrderAddress;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author zhuangcy
 * @date 2020/12/1
 * @description 购物车订单
 */
@Data
@ApiModel("购物车订单")
public class CartOrderVo {
    /**
     * 产品提交列表
     */
    @NotEmpty(message = "产品提交列表不能为空")
    @ApiModelProperty("产品提交列表")
    private List<CartItem> items;
    /**
     * 订单地址
     */
    @NotNull(message = "订单地址不能为空")
    @ApiModelProperty("订单地址")
    private OrderAddress address;

}
