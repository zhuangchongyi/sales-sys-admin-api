package com.dc.project.open.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhuangcy
 * @date 2020/11/27
 * @description 订单信息
 */
@Data
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
}
