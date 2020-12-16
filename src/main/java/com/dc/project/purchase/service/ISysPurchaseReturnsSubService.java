package com.dc.project.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dc.project.purchase.entity.SysPurchaseReturnsSub;

import java.util.List;

/**
 * 采购退货单子表 服务类
 *
 * @author zhuangcy
 * @since 2020-12-10
 */
public interface ISysPurchaseReturnsSubService extends IService<SysPurchaseReturnsSub> {

    List<SysPurchaseReturnsSub> list(SysPurchaseReturnsSub returnsSub);

}
