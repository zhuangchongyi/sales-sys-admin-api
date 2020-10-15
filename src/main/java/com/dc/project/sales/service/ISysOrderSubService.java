package com.dc.project.sales.service;

import com.dc.project.sales.entity.SysOrderSub;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 销售订单子表 服务类
 *
 * @author zhuangcy
 * @since 2020-09-17
 */
public interface ISysOrderSubService extends IService<SysOrderSub> {
    boolean delete(Long subId);
}
