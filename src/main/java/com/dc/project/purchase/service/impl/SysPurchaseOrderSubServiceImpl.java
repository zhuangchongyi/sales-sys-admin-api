package com.dc.project.purchase.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dc.project.purchase.entity.SysPurchaseOrderSub;
import com.dc.project.purchase.dao.SysPurchaseOrderSubDao;
import com.dc.project.purchase.service.ISysPurchaseOrderSubService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 采购订单子表 服务实现类
 *
 * @author zhuangcy
 * @since 2020-12-02
 */
@Service
public class SysPurchaseOrderSubServiceImpl extends ServiceImpl<SysPurchaseOrderSubDao, SysPurchaseOrderSub> implements ISysPurchaseOrderSubService {

    @Override
    public List<SysPurchaseOrderSub> list(SysPurchaseOrderSub orderSub) {
        QueryWrapper<SysPurchaseOrderSub> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(SysPurchaseOrderSub::getOrderId, orderSub.getOrderId())
                .orderByAsc(SysPurchaseOrderSub::getOrderSubId);
        return this.list(queryWrapper);
    }

    @Override
    public List<SysPurchaseOrderSub> listOrderSubs(SysPurchaseOrderSub orderSub) {
        return baseMapper.listOrderSubs(orderSub);
    }

    @Override
    public SysPurchaseOrderSub getOrderSub(Long orderSubId) {
        return this.getOne(new QueryWrapper<SysPurchaseOrderSub>().lambda()
                .select(SysPurchaseOrderSub::getOrderSubId, SysPurchaseOrderSub::getHasReturnNum, SysPurchaseOrderSub::getHasStorageNum, SysPurchaseOrderSub::getHasSignNum, SysPurchaseOrderSub::getNumber)
                .eq(SysPurchaseOrderSub::getOrderSubId, orderSubId));
    }
}
