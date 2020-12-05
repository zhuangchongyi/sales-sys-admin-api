package com.dc.project.open.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.common.exception.ServiceException;
import com.dc.project.open.dao.ClienteleAddressDao;
import com.dc.project.open.entity.ClienteleAddress;
import com.dc.project.open.service.IClienteleAddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 客户收货地址表 服务实现类
 *
 * @author zhuangcy
 * @since 2020-11-28
 */
@Service
public class ClienteleAddressServiceImpl extends ServiceImpl<ClienteleAddressDao, ClienteleAddress> implements IClienteleAddressService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean update(ClienteleAddress address) {
        ClienteleAddress one = this.getById(address.getAddrId());
        if (null == one) {
            throw new ServiceException("地址不存在");
        }
        if (one.getDefAddress() == 0 && address.getDefAddress() == 1) {
            UpdateWrapper<ClienteleAddress> wrapper = new UpdateWrapper<>();
            wrapper.lambda().set(ClienteleAddress::getDefAddress, 0)
                    .eq(ClienteleAddress::getClienteleId, address.getClienteleId());
            if (!this.update(wrapper)) {
                throw new ServiceException("修改地址失败");
            }
        }
        if (!this.updateById(address)) {
            throw new ServiceException("修改地址失败");
        }
        return true;
    }
}
