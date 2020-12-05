package com.dc.project.purchase.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dc.project.purchase.entity.SysPurchaseSign;
import com.dc.project.purchase.vo.PurchaseVo;

/**
 * 采购到货主表 服务类
 *
 * @author zhuangcy
 * @since 2020-12-05
 */
public interface ISysPurchaseSignService extends IService<SysPurchaseSign> {

    IPage<SysPurchaseSign> page(Page<SysPurchaseSign> page,SysPurchaseSign purchaseSign);

    SysPurchaseSign get(Integer id);

    boolean add(PurchaseVo signVo);

    boolean edit(PurchaseVo signVo);

    boolean delete(Integer id);

    boolean submit(PurchaseVo signVo);

    boolean audit(PurchaseVo signVo);
}
