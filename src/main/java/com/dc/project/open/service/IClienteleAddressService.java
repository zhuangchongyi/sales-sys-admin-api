package com.dc.project.open.service;

import com.dc.project.open.entity.ClienteleAddress;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 客户收货地址表 服务类
 *
 * @author zhuangcy
 * @since 2020-11-28
 */
public interface IClienteleAddressService extends IService<ClienteleAddress> {

    boolean update(ClienteleAddress address);
}
