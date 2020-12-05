package com.dc.project.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dc.project.purchase.entity.SysPurchaseSignSub;

import java.util.List;

/**
 * 采购到货单子表 服务类
 *
 * @author zhuangcy
 * @since 2020-12-05
 */
public interface ISysPurchaseSignSubService extends IService<SysPurchaseSignSub> {

    List<SysPurchaseSignSub> list(SysPurchaseSignSub sub);
}
