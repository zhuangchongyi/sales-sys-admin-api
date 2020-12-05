package com.dc.project.purchase.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.project.purchase.dao.SysPurchaseSignDao;
import com.dc.project.purchase.entity.SysPurchaseSign;
import com.dc.project.purchase.service.ISysPurchaseSignService;
import com.dc.project.purchase.service.ISysPurchaseSignSubService;
import com.dc.project.purchase.vo.PurchaseVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 采购到货主表 服务实现类
 *
 * @author zhuangcy
 * @since 2020-12-05
 */
@Service
public class SysPurchaseSignServiceImpl extends ServiceImpl<SysPurchaseSignDao, SysPurchaseSign> implements ISysPurchaseSignService {
    @Autowired
    private ISysPurchaseSignSubService signSubService;

    @Override
    public IPage<SysPurchaseSign> page(Page<SysPurchaseSign> page, SysPurchaseSign purchaseSign) {
        QueryWrapper<SysPurchaseSign> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .like(StringUtils.isNotEmpty(purchaseSign.getSignNum()), SysPurchaseSign::getSignNum, purchaseSign.getSignNum())
                .like(StringUtils.isNotEmpty(purchaseSign.getOrderNum()), SysPurchaseSign::getOrderNum, purchaseSign.getOrderNum())
                .like(StringUtils.isNotEmpty(purchaseSign.getSupplierNum()), SysPurchaseSign::getSupplierNum, purchaseSign.getOrderNum())
                .like(StringUtils.isNotEmpty(purchaseSign.getSupplierName()), SysPurchaseSign::getSupplierName, purchaseSign.getSupplierName())
                .orderByDesc(SysPurchaseSign::getSignId);
        return this.page(page, queryWrapper);
    }

    @Override
    public SysPurchaseSign get(Integer id) {
        return this.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean add(PurchaseVo signVo) {
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean edit(PurchaseVo signVo) {
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean submit(PurchaseVo signVo) {
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean audit(PurchaseVo signVo) {
        return false;
    }
}
