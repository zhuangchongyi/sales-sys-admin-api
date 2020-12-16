package com.dc.project.purchase.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dc.project.purchase.entity.SysPurchaseSign;
import com.dc.project.purchase.entity.SysPurchaseSignSub;
import com.dc.common.vo.CommonVo;

/**
 * 采购到货主表 服务类
 *
 * @author zhuangcy
 * @since 2020-12-05
 */
public interface ISysPurchaseSignService extends IService<SysPurchaseSign> {

    IPage<SysPurchaseSign> page(Page<SysPurchaseSign> page, SysPurchaseSign purchaseSign);

    SysPurchaseSign get(Integer id);

    String add(CommonVo<SysPurchaseSign, SysPurchaseSignSub> vo);

    boolean edit(CommonVo<SysPurchaseSign, SysPurchaseSignSub> vo);

    boolean delete(Integer id);

    boolean submit(CommonVo<SysPurchaseSign, SysPurchaseSignSub> vo);

    boolean audit(CommonVo<SysPurchaseSign, SysPurchaseSignSub> vo);

    boolean auditStorage(CommonVo<SysPurchaseSign, SysPurchaseSignSub> vo);
}
