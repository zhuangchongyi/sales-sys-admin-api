package com.dc.project.purchase.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.exception.ServiceException;
import com.dc.project.purchase.entity.SysSupplier;
import com.dc.project.purchase.dao.SysSupplierDao;
import com.dc.project.purchase.service.ISysSupplierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * 供应商表 服务实现类
 *
 * @author zhuangcy
 * @since 2020-11-26
 */
@Service
public class SysSupplierServiceImpl extends ServiceImpl<SysSupplierDao, SysSupplier> implements ISysSupplierService {

    @Override
    public IPage<SysSupplier> page(Page<SysSupplier> page, SysSupplier supplier) {
        QueryWrapper<SysSupplier> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .like(StringUtils.isNotEmpty(supplier.getSupplierName()), SysSupplier::getSupplierName, supplier.getSupplierName())
                .like(StringUtils.isNotEmpty(supplier.getSupplierNum()), SysSupplier::getSupplierNum, supplier.getSupplierNum())
                .orderByAsc(SysSupplier::getSupplierId);
        return this.page(page, queryWrapper);
    }

    @Override
    public SysSupplier get(Integer id) {
        return this.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean add(SysSupplier supplier) {
        QueryWrapper<SysSupplier> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysSupplier::getSupplierNum, supplier.getSupplierNum());
        SysSupplier one = this.getOne(queryWrapper, false);
        if (null != one) {
            throw new ServiceException(String.format("%s该编码已存在", supplier.getSupplierNum()));
        }
        if (!this.save(supplier)) {
            throw new ServiceException("新增失败");
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean edit(SysSupplier supplier) {
        QueryWrapper<SysSupplier> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysSupplier::getSupplierNum, supplier.getSupplierNum());
        SysSupplier one = this.getOne(queryWrapper, false);
        if (null != one && !Objects.equals(one.getSupplierId(), supplier.getSupplierId())) {
            throw new ServiceException(String.format("%s该编码已存在", supplier.getSupplierNum()));
        }
        if (!this.updateById(supplier)) {
            throw new ServiceException("修改失败");
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(Integer id) {
        if (null == this.getById(id)) {
            throw new ServiceException("已删除");
        }
        if (!this.removeById(id)) {
            throw new ServiceException("删除失败");
        }
        return true;
    }
}
