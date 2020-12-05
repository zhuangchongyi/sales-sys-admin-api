package com.dc.project.basis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.common.exception.ServiceException;
import com.dc.common.utils.BeanUtil;
import com.dc.common.vo.OpenUser;
import com.dc.project.basis.dao.SysClienteleDao;
import com.dc.project.basis.entity.SysClientele;
import com.dc.project.basis.service.ISysClienteleService;
import com.dc.project.purchase.entity.SysSupplier;
import com.dc.project.purchase.service.ISysSupplierService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门表 服务实现类
 *
 * @author zhuangchongyi
 * @since 2020-09-03
 */
@Service
public class SysClienteleServiceImpl extends ServiceImpl<SysClienteleDao, SysClientele> implements ISysClienteleService {
    @Autowired
    private ISysSupplierService supplierService;

    @Override
    public IPage list(Page page, SysClientele clientele) {
        return baseMapper.list(page, clientele);
    }

    @Override
    public SysClientele get(Integer clienteleId) {
        return baseMapper.get(clienteleId);
    }

    @Override
    public SysClientele getClientele() {
        OpenUser user = (OpenUser) SecurityUtils.getSubject().getPrincipal();
        if (null == user) {
            throw new ServiceException("获取用户账户失败");
        }
        SysClientele clientele = user.getClientele();
        return clientele;
    }

    @Override
    public boolean addSupplier(List<Integer> clienteles) {
        if (!clienteles.isEmpty()) {
            List<SysClientele> list = this.list(new QueryWrapper<SysClientele>().lambda().in(SysClientele::getClienteleId, clienteles));
            for (SysClientele clientele : list) {
                SysSupplier one = supplierService.getOne(new QueryWrapper<SysSupplier>().lambda()
                        .eq(SysSupplier::getClienteleId, clientele.getClienteleId()), false);
                if (null != one) {
                    one.setClienteleId(clientele.getClienteleId());
                    one.setSupplierName(clientele.getClienteleName());
                    one.setSupplierNum(clientele.getClienteleNum());
                    if (!supplierService.updateById(one)) {
                        throw new ServiceException();
                    }
                } else {
                    one = new SysSupplier();
                    BeanUtil.copyBeanProp(one, clientele);
                    one.setSupplierName(clientele.getClienteleName());
                    one.setSupplierNum(clientele.getClienteleNum());
                    one.setUpdateBy(null);
                    one.setUpdateTime(null);
                    if (!supplierService.save(one)) {
                        throw new ServiceException();
                    }
                }

            }
            return true;
        }
        throw new ServiceException();
    }

}
