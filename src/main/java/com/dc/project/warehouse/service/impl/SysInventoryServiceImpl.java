package com.dc.project.warehouse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.common.constant.SalesConstant;
import com.dc.common.exception.ServiceException;
import com.dc.common.utils.BeanUtil;
import com.dc.common.utils.BigDecimalUtil;
import com.dc.common.utils.ObjectMapperUtil;
import com.dc.common.utils.UserSecurityUtils;
import com.dc.project.warehouse.dao.SysInventoryDao;
import com.dc.project.warehouse.entity.*;
import com.dc.project.warehouse.service.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 盘点单主表 服务实现类
 *
 * @author zhuangcy
 * @since 2020-09-23
 */
@Service
public class SysInventoryServiceImpl extends ServiceImpl<SysInventoryDao, SysInventory> implements ISysInventoryService {
    @Autowired
    private ISysInventorySubService inventorySubService;
    @Autowired
    private ISysRepertoryService repertoryService;
    @Autowired
    private ISysAdjustmentService adjustmentService;
    @Autowired
    private ISysAdjustmentSubService adjustmentSubService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addAndUpdate(Map formMap) throws Exception {
        Object inventoryForm = formMap.get("inventory");
        Object inventorySubForm = formMap.get("inventorySub");
        Object delSubIdsForm = formMap.get("delSubIds");
        if (null == inventoryForm || null == inventorySubForm || null == delSubIdsForm)
            throw new ServiceException("保存失败");
        SysInventory sysInventory = ObjectMapperUtil.toObject(inventoryForm.toString(), SysInventory.class);
        if (null == sysInventory.getWarehouseId())
            throw new ServiceException("保存失败，未选择仓库");
        if (null == sysInventory.getInventoryId()) { //主表不存在
            if (!this.save(sysInventory))
                throw new ServiceException("保存失败");
        } else {
            update(sysInventory);
        }
        List<Map> mapList = ObjectMapperUtil.toObject(inventorySubForm.toString(), List.class);
        List<SysInventorySub> addList = new ArrayList<>();
        List<SysInventorySub> updateList = new ArrayList<>();
        for (Map map : mapList) {
            SysInventorySub inventorySub = new SysInventorySub();
            BeanUtil.register();
            BeanUtils.populate(inventorySub, map);
            if (null == inventorySub.getSubId()) {
                inventorySub.setInventoryId(sysInventory.getInventoryId());
                inventorySub.setWarehouseId(sysInventory.getWarehouseId());
                addList.add(inventorySub);
            } else {
                updateList.add(inventorySub);
            }
        }
        // 新增产品处理
        insertAndUpdate(addList);
        // 修改产品处理
        insertAndUpdate(updateList);
        // 删除产品处理
        List<Long> delSubIds = ObjectMapperUtil.toObject(delSubIdsForm.toString(), List.class);
        delSub(delSubIds);
        if (addList.isEmpty() && updateList.isEmpty())
            throw new ServiceException("未找到产品信息");
        return true;
    }

    private void update(SysInventory sysInventory) {
        UpdateWrapper<SysInventory> uw = new UpdateWrapper<>();
        uw.set(null != sysInventory.getInventoryTime(), "inventory_time", sysInventory.getInventoryTime());
        uw.set(null != sysInventory.getPersonnelId(), "personnel_id", sysInventory.getPersonnelId());
        uw.set(StringUtils.isNotEmpty(sysInventory.getPersonnelName()), "personnel_name", sysInventory.getPersonnelName());
        uw.set(StringUtils.isNotEmpty(sysInventory.getRemark()), "remark", sysInventory.getRemark());
        uw.eq("inventory_id", sysInventory.getInventoryId());
        this.update(uw);
    }


    private void delSub(List<Long> delSubIds) {
        if (!delSubIds.isEmpty()) {
            inventorySubService.removeByIds(delSubIds);
        }
    }

    private void insertAndUpdate(List<SysInventorySub> updateList) {
        if (!updateList.isEmpty()) {
            for (SysInventorySub sub : updateList) {
                if (null == sub.getSubId()) {
                    inventorySubService.save(sub);
                } else {
                    updateSub(sub);
                }
            }
        }
    }

    private void updateSub(SysInventorySub sub) {
        UpdateWrapper<SysInventorySub> subUW = new UpdateWrapper<>();
        subUW.set("reality_num", sub.getRealityNum());
        subUW.eq("sub_id", sub.getSubId());
        inventorySubService.update(subUW);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(Integer id) {
        inventorySubService.remove(new QueryWrapper<SysInventorySub>().eq("inventory_id", id));
        return this.removeById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean submit(Integer[] ids, String status) {
        if (null == ids || ids.length == 0) {
            throw new ServiceException();
        }
        QueryWrapper<SysInventory> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("inventory_id,status").in("inventory_id", Arrays.asList(ids));
        List<SysInventory> list = this.list(queryWrapper);
        if (list == null || list.isEmpty()) {
            throw new ServiceException();
        }
        ArrayList<Integer> idList = new ArrayList<>();
        for (SysInventory inventory : list) {
            Integer inventoryId = inventory.getInventoryId();
            SalesConstant.verifySubmitStatus(status, inventoryId.toString(), inventory.getStatus());
            idList.add(inventoryId);
        }
        UpdateWrapper<SysInventory> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status", status).in("inventory_id", idList);
        return this.update(updateWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean audit(SysInventory inventory) {
        SysInventory one = this.getById(inventory.getInventoryId());
        if (null == one)
            throw new ServiceException();
        SalesConstant.verifyAuditStatus(one.getStatus(), inventory.getStatus());
        inventory.setAuditBy(UserSecurityUtils.getUsername());
        inventory.setAuditTime(new Date());
        QueryWrapper<SysInventorySub> qw = new QueryWrapper<>();
        qw.eq("inventory_id", inventory.getInventoryId());
        qw.ne("reality_num", 0);
        List<SysInventorySub> inventorySubs = inventorySubService.list(qw);
        if (inventorySubs.isEmpty()) {
            throw new SecurityException();
        }
        if (SalesConstant.AUDIT.equals(inventory.getStatus())) {//审核时
            // 生成已审核的调整单
            one.setStatus(inventory.getStatus());
            one.setAuditBy(inventory.getAuditBy());
            one.setAuditTime(inventory.getAuditTime());
            Integer adjustmentId = this.transformAdjustment(one, inventorySubs);
            inventory.setAdjustmentId(adjustmentId);
            // 盘点时增加或减少产品现存量
            repertoryService.saveAndUpdate(this.transformRepertory(inventorySubs, SalesConstant.AUDIT));
        } else {//反审核时
            adjustmentService.removeById(one.getAdjustmentId());
            adjustmentSubService.remove(new QueryWrapper<SysAdjustmentSub>().eq("adjustment_id", one.getAdjustmentId()));
            repertoryService.saveAndUpdate(this.transformRepertory(inventorySubs, SalesConstant.NO_AUDIT));
        }
        return this.updateById(inventory);
    }

    public Integer transformAdjustment(SysInventory inventory, List<SysInventorySub> inventorySubs) {
        SysAdjustment adjustment = new SysAdjustment();
        BeanUtil.copyBeanProp(adjustment, inventory);
        adjustment.setAdjustmentTime(inventory.getInventoryTime());
        adjustment.setAdjustmentType("1");
        adjustment.setRemark("盘点调整");
        boolean save = adjustmentService.save(adjustment);
        if (save) {
            for (SysInventorySub sub : inventorySubs) {
                SysAdjustmentSub adjustmentSub = new SysAdjustmentSub();
                BeanUtil.copyBeanProp(adjustmentSub, sub);
                adjustmentSub.setAdjustmentId(adjustment.getAdjustmentId());
                adjustmentSub.setAdjustPrice(BigDecimalUtil.div(sub.getTotalPrice(), sub.getRealityNum(), 2));
                adjustmentSub.setAdjustNum(sub.getRealityNum() - sub.getNumber());
                adjustmentSub.setNumber(sub.getRealityNum());
                adjustmentSub.setRemark("盘点调整");
                if (!adjustmentSubService.save(adjustmentSub)) {
                    throw new SecurityException("生成调整单失败");
                }
            }
        } else {
            throw new SecurityException("生成调整单失败");
        }
        return adjustment.getAdjustmentId();
    }

    private List<SysRepertory> transformRepertory(List<SysInventorySub> inventorySubs, String audit) {
        if (inventorySubs == null || inventorySubs.isEmpty())
            throw new ServiceException();
        List<SysRepertory> resultList = new ArrayList<>();
        for (SysInventorySub sub : inventorySubs) {
            SysRepertory repertory = new SysRepertory();
            BeanUtil.copyBeanProp(repertory, sub);
            // 现存表的产品根据产品id，仓库id，型号确定唯一性
            if (null == repertory.getMaterielId() || null == repertory.getWarehouseId() || StringUtils.isEmpty(repertory.getModelName()))
                throw new ServiceException("系统异常,缺少参数");
            SysRepertory one = repertoryService.getSysRepertory(repertory);
            if (SalesConstant.AUDIT.equals(audit)) {
                if (one == null) {
                    resultList.add(repertory);
                } else {
                    int number = one.getNumber() + (sub.getRealityNum() - sub.getNumber());
                    one.setNumber(number);
                    one.setTotalPrice(BigDecimalUtil.mul(one.getPrice(), number));
                    resultList.add(one);
                }
            } else {
                if (null == one) {
                    throw new ServiceException(String.format("反审核失败，%s %s 该产品不存在",
                            repertory.getMaterielName(), repertory.getModelName()));
                }
                int number = one.getNumber() - (sub.getRealityNum() - sub.getNumber());
                one.setNumber(number);
                one.setTotalPrice(BigDecimalUtil.mul(one.getPrice(), number));
                resultList.add(one);
            }
        }
        return resultList;
    }
}
