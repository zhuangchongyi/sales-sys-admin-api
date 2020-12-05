package com.dc.project.purchase.vo;

import com.dc.project.purchase.entity.SysPurchaseOrder;
import com.dc.project.purchase.entity.SysPurchaseOrderSub;
import com.dc.project.purchase.entity.SysPurchaseSign;
import com.dc.project.purchase.entity.SysPurchaseSignSub;
import lombok.Data;

import java.util.List;

/**
 * @author zhuangcy
 * @date 2020/12/3
 * @description 采购订单vo
 */
@Data
public class PurchaseVo {
    /**
     * 订单主体信息
     */
    private SysPurchaseOrder order;
    /**
     * 订单子表信息
     */
    private List<SysPurchaseOrderSub> orderSubs;
    /**
     * 到货单主体信息
     */
    private SysPurchaseSign sign;
    /**
     * 到货单子表信息
     */
    private List<SysPurchaseSignSub> signSubs;

    /**
     * 删除的订单子表信息
     */
    private List<Long> delSubIds;
    /**
     * 提交id
     */
    private List<Integer> ids;
}
