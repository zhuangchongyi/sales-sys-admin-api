package com.dc.project.warehouse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.common.exception.ServiceException;
import com.dc.common.utils.BeanUtil;
import com.dc.common.utils.ObjectMapperUtil;
import com.dc.project.warehouse.dao.SysWarehouseInitDao;
import com.dc.project.warehouse.entity.SysRepertory;
import com.dc.project.warehouse.entity.SysWarehouseInit;
import com.dc.project.warehouse.entity.SysWarehouseInitSub;
import com.dc.project.warehouse.service.ISysRepertoryService;
import com.dc.project.warehouse.service.ISysWarehouseInitService;
import com.dc.project.warehouse.service.ISysWarehouseInitSubService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 仓库初始化主表 服务实现类
 *
 * @author zhuangcy
 * @since 2020-09-21
 */
@Service
public class SysWarehouseInitServiceImpl extends ServiceImpl<SysWarehouseInitDao, SysWarehouseInit> implements ISysWarehouseInitService {
    @Autowired
    private ISysWarehouseInitSubService warehouseInitSubService;
    @Autowired
    private ISysRepertoryService repertoryService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addAndUpdate(Map formMap) throws Exception {
        Object initForm = formMap.get("init");
        Object initSubForm = formMap.get("initSub");
        Object delSubIdsForm = formMap.get("delSubIds");
        if (null == initForm || null == initSubForm || null == delSubIdsForm)
            throw new ServiceException("保存失败");
        SysWarehouseInit sysWarehouseInit = ObjectMapperUtil.toObject(initForm.toString(), SysWarehouseInit.class);
        if (null == sysWarehouseInit.getWarehouseId())
            throw new ServiceException("保存失败，未选择仓库");
        int count = this.count(new QueryWrapper<SysWarehouseInit>().eq("warehouse_id", sysWarehouseInit.getWarehouseId()));
        if (count <= 0 && !this.save(sysWarehouseInit)) { //主表不存在
            throw new ServiceException("保存失败");
        }
        List<Map> initSubs = ObjectMapperUtil.toObject(initSubForm.toString(), List.class);
        List<SysWarehouseInitSub> addList = new ArrayList<>();
        List<SysWarehouseInitSub> updateList = new ArrayList<>();
        for (Map map : initSubs) {
            SysWarehouseInitSub initSub = new SysWarehouseInitSub();
            BeanUtil.register();
            BeanUtils.populate(initSub, map);
            if (null == initSub.getSubId()) {
                initSub.setWarehouseId(sysWarehouseInit.getWarehouseId());
                addList.add(initSub);
            } else {
                updateList.add(initSub);
            }
        }
        // 新增产品处理
        insertAndUpdate(sysWarehouseInit, addList);
        // 修改产品处理
        insertAndUpdate(sysWarehouseInit, updateList);
        // 删除产品处理
        List<Long> delSubIds = ObjectMapperUtil.toObject(delSubIdsForm.toString(), List.class);
        delSub(delSubIds);
        if (addList.isEmpty() && updateList.isEmpty() && delSubIds.isEmpty())
            throw new ServiceException("未找到产品信息");
        return true;
    }


    private void delSub(List<Long> delSubIds) {
        if (!delSubIds.isEmpty()) {
            QueryWrapper<SysWarehouseInitSub> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("warehouse_id,materiel_id,model_name").in("sub_id", delSubIds);
            // 删除现存量
//            List<SysWarehouseInitSub> list = warehouseInitSubService.list(queryWrapper);
//            deleteRepertory(list);
            warehouseInitSubService.removeByIds(delSubIds);
        }
    }

    private void insertAndUpdate(SysWarehouseInit sysWarehouseInit, List<SysWarehouseInitSub> updateList) {
        if (!updateList.isEmpty()) {
            for (SysWarehouseInitSub sub : updateList) {
                if (null == sub.getSubId()) {
                    warehouseInitSubService.save(sub);
                } else {
                    updateSub(sub);
                }
                // 修改现存量
                SysRepertory one = repertoryService.getRepertory(sub);
                if (null == one) {
                    insertRepertory(sysWarehouseInit, sub);
                } else {
                    updateRepertory(sysWarehouseInit, sub);
                }
            }
        }
    }

    private void updateSub(SysWarehouseInitSub sub) {
        UpdateWrapper<SysWarehouseInitSub> subUW = new UpdateWrapper<>();
        subUW.set("number", sub.getNumber());
        subUW.set("price", sub.getPrice());
        subUW.set("total_price", sub.getTotalPrice());
        subUW.eq("sub_id", sub.getSubId());
        warehouseInitSubService.update(subUW);
    }


    private void insertRepertory(SysWarehouseInit sysWarehouseInit, SysWarehouseInitSub sub) {
        SysRepertory repertory = new SysRepertory();
        BeanUtil.copyBeanProp(repertory, sub);
        repertory.setWarehouseId(sysWarehouseInit.getWarehouseId());
        repertory.setWarehouseNum(sysWarehouseInit.getWarehouseNum());
        repertory.setWarehouseName(sysWarehouseInit.getWarehouseName());
        repertoryService.save(repertory);
    }

    private void updateRepertory(SysWarehouseInit sysWarehouseInit, SysWarehouseInitSub sub) {
        UpdateWrapper<SysRepertory> repertoryUW = new UpdateWrapper<>();
        repertoryUW.set("total_price", sub.getTotalPrice());
        repertoryUW.set("price", sub.getPrice());
        repertoryUW.set("number", sub.getNumber());
        repertoryUW.eq("materiel_id", sub.getMaterielId());
        repertoryUW.eq("model_name", sub.getModelName());
        repertoryUW.eq("warehouse_id", sysWarehouseInit.getWarehouseId());
        repertoryService.update(repertoryUW);
    }

    private void deleteRepertory(List<SysWarehouseInitSub> list) {
        for (SysWarehouseInitSub sub : list) {
            QueryWrapper<SysRepertory> wrapper = new QueryWrapper<>();
            wrapper.eq("warehouse_id", sub.getWarehouseId());
            wrapper.eq("materiel_id", sub.getMaterielId());
            wrapper.eq("model_name", sub.getModelName());
            repertoryService.remove(wrapper);
        }
    }

    @Override
    public boolean delete(Integer id) {
        SysWarehouseInit init = this.getById(id);
        QueryWrapper<SysWarehouseInitSub> wrapper = new QueryWrapper<SysWarehouseInitSub>()
                .select("warehouse_id,materiel_id,model_name")
                .eq("warehouse_id", init.getWarehouseId());
        List<SysWarehouseInitSub> subList = warehouseInitSubService.list(wrapper);
        warehouseInitSubService.remove(new QueryWrapper<SysWarehouseInitSub>().eq("warehouse_id", id));
        // 删除现存量
        deleteRepertory(subList);
        return this.removeById(id);
    }
}
