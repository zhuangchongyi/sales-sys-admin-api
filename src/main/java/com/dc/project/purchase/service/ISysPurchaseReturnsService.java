package com.dc.project.purchase.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.project.purchase.entity.SysPurchaseReturns;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dc.project.purchase.entity.SysPurchaseReturnsSub;
import com.dc.common.vo.CommonVo;

/**
 * 采购到货主表 服务类
 *
 * @author zhuangcy
 * @since 2020-12-10
 */
public interface ISysPurchaseReturnsService extends IService<SysPurchaseReturns> {

    IPage<SysPurchaseReturns> page(Page<SysPurchaseReturns> page, SysPurchaseReturns returns);

    SysPurchaseReturns get(Integer id);

    String add(CommonVo<SysPurchaseReturns, SysPurchaseReturnsSub> vo);

    boolean edit(CommonVo<SysPurchaseReturns, SysPurchaseReturnsSub> vo);

    boolean delete(Integer id);

    boolean submit(CommonVo<SysPurchaseReturns, SysPurchaseReturnsSub> vo);

    boolean audit(CommonVo<SysPurchaseReturns, SysPurchaseReturnsSub> vo);

    boolean auditOutbound(CommonVo<SysPurchaseReturns, SysPurchaseReturnsSub> vo);
}
