package com.dc.project.warehouse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.common.constant.CustomConstant;
import com.dc.common.constant.SalesConstant;
import com.dc.common.exception.ServiceException;
import com.dc.common.lang.annotation.DataScope;
import com.dc.common.utils.BeanUtil;
import com.dc.common.utils.BigDecimalUtil;
import com.dc.common.utils.ObjectMapperUtil;
import com.dc.common.utils.UserSecurityUtil;
import com.dc.project.warehouse.dao.SysAdjustmentDao;
import com.dc.project.warehouse.entity.SysAdjustment;
import com.dc.project.warehouse.entity.SysAdjustmentSub;
import com.dc.project.warehouse.entity.SysRepertory;
import com.dc.project.warehouse.service.ISysAdjustmentService;
import com.dc.project.warehouse.service.ISysAdjustmentSubService;
import com.dc.project.warehouse.service.ISysRepertoryService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 调整单主表 服务实现类
 *
 * @author zhuangcy
 * @since 2020-09-23
 */
@Service
public class SysAdjustmentServiceImpl extends ServiceImpl<SysAdjustmentDao, SysAdjustment> implements ISysAdjustmentService {
    @Autowired
    private ISysAdjustmentSubService adjustmentSubService;
    @Autowired
    private ISysRepertoryService repertoryService;

    @DataScope(userColumn = "create_id")
    @Override
    public IPage<SysAdjustment> page(Page<SysAdjustment> page, SysAdjustment adjustment) {
        QueryWrapper<SysAdjustment> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(adjustment.getWarehouseName()), "warehouse_name", adjustment.getWarehouseName())
                .like(StringUtils.isNotEmpty(adjustment.getWarehouseNum()), "warehouse_num", adjustment.getWarehouseNum())
                .apply(adjustment.getParams().get(CustomConstant.DATA_SCOPE).toString().replaceFirst("and", ""))
                .orderByDesc("create_time");
        return this.page(page, queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addAndUpdate(Map formMap) throws Exception {
        Object adjustmentForm = formMap.get("adjustment");
        Object adjustmentSubForm = formMap.get("adjustmentSub");
        Object delSubIdsForm = formMap.get("delSubIds");
        if (null == adjustmentForm || null == adjustmentSubForm || null == delSubIdsForm)
            throw new ServiceException("保存失败");
        SysAdjustment sysAdjustment = ObjectMapperUtil.toObject(adjustmentForm.toString(), SysAdjustment.class);
        if (null == sysAdjustment.getWarehouseId())
            throw new ServiceException("保存失败，未选择仓库");
        if (null == sysAdjustment.getAdjustmentId()) { //主表不存在
            if (!this.save(sysAdjustment))
                throw new ServiceException("保存失败");
        } else {
            update(sysAdjustment);
        }
        List<Map> mapList = ObjectMapperUtil.toObject(adjustmentSubForm.toString(), List.class);
        List<SysAdjustmentSub> addList = new ArrayList<>();
        List<SysAdjustmentSub> updateList = new ArrayList<>();
        for (Map map : mapList) {
            SysAdjustmentSub adjustmentSub = new SysAdjustmentSub();
            BeanUtil.register();
            BeanUtils.populate(adjustmentSub, map);
            if (null == adjustmentSub.getSubId()) {
                adjustmentSub.setAdjustmentId(sysAdjustment.getAdjustmentId());
                adjustmentSub.setWarehouseId(sysAdjustment.getWarehouseId());
                addList.add(adjustmentSub);
            } else {
                updateList.add(adjustmentSub);
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

    private void update(SysAdjustment sysAdjustment) {
        UpdateWrapper<SysAdjustment> uw = new UpdateWrapper<>();
        uw.set(null != sysAdjustment.getAdjustmentTime(), "adjustment_time", sysAdjustment.getAdjustmentTime());
        uw.set(null != sysAdjustment.getPersonnelId(), "personnel_id", sysAdjustment.getPersonnelId());
        uw.set(StringUtils.isNotEmpty(sysAdjustment.getPersonnelName()), "personnel_name", sysAdjustment.getPersonnelName());
        uw.set(StringUtils.isNotEmpty(sysAdjustment.getRemark()), "remark", sysAdjustment.getRemark());
        uw.eq("adjustment_id", sysAdjustment.getAdjustmentId());
        this.update(uw);
    }


    private void delSub(List<Long> delSubIds) {
        if (!delSubIds.isEmpty()) {
            adjustmentSubService.removeByIds(delSubIds);
        }
    }

    private void insertAndUpdate(List<SysAdjustmentSub> updateList) {
        if (!updateList.isEmpty()) {
            for (SysAdjustmentSub sub : updateList) {
                if (null == sub.getSubId()) {
                    sub.setPrice(sub.getAdjustPrice());
                    adjustmentSubService.save(sub);
                } else {
                    updateSub(sub);
                }
            }
        }
    }

    private void updateSub(SysAdjustmentSub sub) {
        UpdateWrapper<SysAdjustmentSub> subUW = new UpdateWrapper<>();
        subUW.set(null != sub.getTotalPrice(), "total_price", sub.getTotalPrice());
        subUW.set(null != sub.getNumber(), "number", sub.getNumber());
        subUW.set(null != sub.getAdjustPrice(), "adjust_price", sub.getAdjustPrice());
        subUW.set(null != sub.getAdjustPrice(), "adjust_price", sub.getAdjustNum());
        subUW.set("remark", sub.getRemark());
        subUW.eq("sub_id", sub.getSubId());
        adjustmentSubService.update(subUW);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(Integer id) {
        adjustmentSubService.remove(new QueryWrapper<SysAdjustmentSub>().eq("adjustment_id", id));
        return this.removeById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean submit(Integer[] ids, String status) {
        if (null == ids || ids.length == 0) {
            throw new ServiceException();
        }
        QueryWrapper<SysAdjustment> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("adjustment_id,status").in("adjustment_id", Arrays.asList(ids));
        List<SysAdjustment> list = this.list(queryWrapper);
        if (list == null || list.isEmpty()) {
            throw new ServiceException();
        }
        ArrayList<Integer> idList = new ArrayList<>();
        for (SysAdjustment adjustment : list) {
            Integer adjustmentId = adjustment.getAdjustmentId();
            SalesConstant.verifySubmitStatus(status, adjustmentId.toString(), adjustment.getStatus());
            idList.add(adjustmentId);
        }
        UpdateWrapper<SysAdjustment> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status", status).in("adjustment_id", idList);
        return this.update(updateWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean audit(SysAdjustment adjustment) {
        QueryWrapper<SysAdjustment> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("status").eq("adjustment_id", adjustment.getAdjustmentId());
        SysAdjustment one = this.getOne(queryWrapper);
        if (null == one) throw new ServiceException();
        SalesConstant.verifyAuditStatus(one.getStatus(), adjustment.getStatus());
        adjustment.setAuditBy(UserSecurityUtil.getUsername());
        adjustment.setAuditTime(new Date());
        if (SalesConstant.AUDIT.equals(adjustment.getStatus())) {//审核时
            // 调整时时添加或减少产品现存量
            repertoryService.saveAndUpdate(transformRepertory(adjustment, SalesConstant.AUDIT));
        } else {//反审核时
            repertoryService.saveAndUpdate(transformRepertory(adjustment, SalesConstant.NO_AUDIT));
        }
        return this.updateById(adjustment);
    }

    private List<SysRepertory> transformRepertory(SysAdjustment adjustment, String audit) {
        QueryWrapper<SysAdjustmentSub> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("adjustment_id", adjustment.getAdjustmentId());
        List<SysAdjustmentSub> adjustmentSubs = adjustmentSubService.list(queryWrapper);
        if (adjustmentSubs == null || adjustmentSubs.isEmpty())
            throw new ServiceException();
        List<SysRepertory> resultList = new ArrayList<>();
        for (SysAdjustmentSub sub : adjustmentSubs) {
            SysRepertory repertory = new SysRepertory();
            BeanUtil.copyBeanProp(repertory, sub);
            // 现存表的产品根据产品id，仓库id，型号确定唯一性
            if (null == repertory.getMaterielId() || null == repertory.getWarehouseId() || StringUtils.isEmpty(repertory.getModelName()))
                throw new ServiceException();
            SysRepertory one = repertoryService.getSysRepertory(repertory);
            if (SalesConstant.AUDIT.equals(audit)) {
                if (null == one) {
                    resultList.add(repertory);
                } else {
                    int number = one.getNumber() + sub.getAdjustNum();
                    if (number < 0) {
                        throw new ServiceException(String.format("审核失败，%s %s 现存数量(%s)小于调整数量(%s)",
                                one.getMaterielName(), one.getModelName(), one.getNumber(), repertory.getNumber()));
                    }
                    BigDecimal decimal = BigDecimalUtil.sub(sub.getTotalPrice(), sub.getAdjustTotalPrice());//变化值
                    BigDecimal totalPrice = BigDecimalUtil.add(one.getTotalPrice(), decimal);
                    BigDecimal price = number == 0 ? BigDecimalUtil.ZERO : BigDecimalUtil.div(totalPrice, number, 2);
                    one.setNumber(number);
                    one.setTotalPrice(totalPrice);
                    one.setPrice(price);
                    resultList.add(one);
                }
            } else {
                if (null == one) {
                    throw new ServiceException(String.format("反审核失败，%s %s 该产品不存在",
                            repertory.getMaterielName(), repertory.getModelName()));
                }
                int number = one.getNumber() - sub.getAdjustNum();
                BigDecimal decimal = BigDecimalUtil.sub(sub.getTotalPrice(), sub.getAdjustTotalPrice());
                BigDecimal totalPrice = BigDecimalUtil.sub(one.getTotalPrice(), decimal);
                if (number < 0) {
                    throw new ServiceException(String.format("反审核失败，%s %s 现存数量(%s)小于调整数量(%s)",
                            one.getMaterielName(), one.getModelName(), one.getNumber(), repertory.getNumber()));
                }
                if (BigDecimalUtil.compareTo(totalPrice, BigDecimalUtil.ZERO) < 0) {
                    throw new ServiceException(String.format("反审核失败，%s %s 现存总金额(%s)小于调整总金额(%s)",
                            one.getMaterielName(), one.getModelName(), one.getTotalPrice(), repertory.getTotalPrice()));
                }
                BigDecimal price = (number == 0 && BigDecimalUtil.compareTo(totalPrice, BigDecimalUtil.ZERO) == 0) ?
                        BigDecimalUtil.ZERO : BigDecimalUtil.div(totalPrice, number, 2);
                one.setNumber(number);
                one.setTotalPrice(totalPrice);
                one.setPrice(price);
                resultList.add(one);
            }
        }
        return resultList;
    }

}
