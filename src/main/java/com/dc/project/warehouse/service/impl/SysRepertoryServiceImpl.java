package com.dc.project.warehouse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.common.exception.ServiceException;
import com.dc.common.utils.BigDecimalUtil;
import com.dc.project.warehouse.dao.SysRepertoryDao;
import com.dc.project.warehouse.entity.SysRepertory;
import com.dc.project.warehouse.entity.SysWarehouseInitSub;
import com.dc.project.warehouse.service.ISysRepertoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产品现存表 服务实现类
 *
 * @author zhuangcy
 * @since 2020-09-21
 */
@Service
public class SysRepertoryServiceImpl extends ServiceImpl<SysRepertoryDao, SysRepertory> implements ISysRepertoryService {

    @Override
    public IPage<SysRepertory> page(Page page, SysRepertory sysRepertory) {
        return baseMapper.page(page, sysRepertory);
    }

    @Override
    public List<SysRepertory> list(SysRepertory sysRepertory) {
        return baseMapper.list(sysRepertory);
    }

    @Override
    public boolean saveAndUpdate(List<SysRepertory> list) {
        if (null == list || list.isEmpty())
            throw new SecurityException();
        for (SysRepertory repertory : list) {
            if (null == repertory.getPkId()) { //新增
                if (!this.save(repertory)) throw new ServiceException("修改库存失败");
            } else { //修改
                update(repertory);
            }
        }
        return true;
    }

    private void update(SysRepertory one) {
        if (one.getNumber() == 0 || one.getTotalPrice().compareTo(BigDecimalUtil.ZERO) <= 0) {
            if (!this.removeById(one.getPkId())) throw new ServiceException("修改库存失败");
        }
        UpdateWrapper<SysRepertory> uw = new UpdateWrapper<>();
        uw.set("price", one.getPrice());
        uw.set("total_price", one.getTotalPrice());
        uw.set("number", one.getNumber());
        uw.eq("pk_id", one.getPkId());
        if (!this.update(uw)) throw new ServiceException("修改库存失败");
    }

    @Override
    public SysRepertory getSysRepertory(SysRepertory repertory) {
        if (null == repertory || null == repertory.getMaterielId() || null == repertory.getWarehouseId() || StringUtils.isEmpty(repertory.getModelName()))
            throw new ServiceException();
        QueryWrapper<SysRepertory> qw = new QueryWrapper<>();
        qw.eq("warehouse_id", repertory.getWarehouseId());
        qw.eq("materiel_id", repertory.getMaterielId());
        qw.eq("model_name", repertory.getModelName());
        return this.getOne(qw);
    }

    @Override
    public SysRepertory getRepertory(SysWarehouseInitSub sub) {
        QueryWrapper<SysRepertory> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("pk_id");
        queryWrapper.eq("materiel_id", sub.getMaterielId());
        queryWrapper.eq("model_name", sub.getModelName());
        queryWrapper.eq("warehouse_id", sub.getWarehouseId());
        return this.getOne(queryWrapper);
    }
}
