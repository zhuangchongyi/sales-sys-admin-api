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
import com.dc.project.warehouse.dao.SysStorageDao;
import com.dc.project.warehouse.entity.SysRepertory;
import com.dc.project.warehouse.entity.SysStorage;
import com.dc.project.warehouse.entity.SysStorageSub;
import com.dc.project.warehouse.service.ISysRepertoryService;
import com.dc.project.warehouse.service.ISysStorageService;
import com.dc.project.warehouse.service.ISysStorageSubService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 产品入库主表 服务实现类
 *
 * @author zhuangcy
 * @since 2020-09-22
 */
@Service
public class SysStorageServiceImpl extends ServiceImpl<SysStorageDao, SysStorage> implements ISysStorageService {
    @Autowired
    private ISysStorageSubService storageSubService;
    @Autowired
    private ISysRepertoryService repertoryService;

    @DataScope(userColumn = "create_id")
    @Override
    public IPage<SysStorage> page(Page<SysStorage> page, SysStorage sysStorage) {
        QueryWrapper<SysStorage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("inout_type", sysStorage.getInoutType());
        queryWrapper.like(StringUtils.isNotEmpty(sysStorage.getWarehouseNum()), "warehouse_num", sysStorage.getWarehouseNum())
                .like(StringUtils.isNotEmpty(sysStorage.getWarehouseName()), "warehouse_name", sysStorage.getWarehouseName())
                .apply(sysStorage.getParams().get(CustomConstant.DATA_SCOPE).toString().replaceFirst("and", ""))
                .orderByDesc("create_time");
        return this.page(page, queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addAndUpdate(Map formMap) throws Exception {
        Object storageForm = formMap.get("storage");
        Object storageSubForm = formMap.get("storageSub");
        Object delSubIdsForm = formMap.get("delSubIds");
        if (null == storageForm || null == storageSubForm || null == delSubIdsForm)
            throw new ServiceException("保存失败");
        SysStorage sysStorage = new SysStorage();
        BeanUtil.register();
        BeanUtils.populate(sysStorage, ObjectMapperUtil.toObject(storageForm.toString(), Map.class));
        if (null == sysStorage.getWarehouseId()) {
            throw new ServiceException("保存失败，未选择仓库");
        }
        if (null == sysStorage.getStorageId()) { //主表不存在
            if (!this.save(sysStorage))
                throw new ServiceException("保存失败");
        } else {
            update(sysStorage);
        }
        List<Map> mapList = ObjectMapperUtil.toObject(storageSubForm.toString(), List.class);
        List<SysStorageSub> addList = new ArrayList<>();
        List<SysStorageSub> updateList = new ArrayList<>();
        for (Map map : mapList) {
            SysStorageSub storageSub = new SysStorageSub();
            BeanUtil.register();
            BeanUtils.populate(storageSub, map);
            if (null == storageSub.getSubId()) {
                storageSub.setStorageId(sysStorage.getStorageId());
                storageSub.setWarehouseId(sysStorage.getWarehouseId());
                addList.add(storageSub);
            } else {
                updateList.add(storageSub);
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

    private void update(SysStorage sysStorage) {
        UpdateWrapper<SysStorage> uw = new UpdateWrapper<>();
        uw.set(StringUtils.isNotEmpty(sysStorage.getSourceCompany()), "source_company", sysStorage.getSourceCompany());
        uw.set(StringUtils.isNotEmpty(sysStorage.getSourceType()), "source_type", sysStorage.getSourceType());
        uw.set(null != sysStorage.getStorageTime(), "storage_time", sysStorage.getStorageTime());
        uw.set(null != sysStorage.getPersonnelId(), "personnel_id", sysStorage.getPersonnelId());
        uw.set(StringUtils.isNotEmpty(sysStorage.getPersonnelName()), "personnel_name", sysStorage.getPersonnelName());
        uw.set(StringUtils.isNotEmpty(sysStorage.getRemark()), "remark", sysStorage.getRemark());
        uw.eq("storage_id", sysStorage.getStorageId());
        this.update(uw);
    }


    private void delSub(List<Long> delSubIds) {
        if (!delSubIds.isEmpty()) {
            storageSubService.removeByIds(delSubIds);
        }
    }

    private void insertAndUpdate(List<SysStorageSub> updateList) {
        if (!updateList.isEmpty()) {
            for (SysStorageSub sub : updateList) {
                if (null == sub.getSubId()) {
                    storageSubService.save(sub);
                } else {
                    updateSub(sub);
                }
            }
        }
    }

    private void updateSub(SysStorageSub sub) {
        UpdateWrapper<SysStorageSub> subUW = new UpdateWrapper<>();
        subUW.set("number", sub.getNumber());
        subUW.set("price", sub.getPrice());
        subUW.set("total_price", sub.getTotalPrice());
        subUW.eq("sub_id", sub.getSubId());
        storageSubService.update(subUW);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(Integer id) {
        storageSubService.remove(new QueryWrapper<SysStorageSub>().eq("storage_id", id));
        return this.removeById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean submit(Integer[] ids, String status) {
        if (null == ids || ids.length == 0) {
            throw new ServiceException();
        }
        QueryWrapper<SysStorage> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("storage_id,status").in("storage_id", Arrays.asList(ids));
        List<SysStorage> list = this.list(queryWrapper);
        if (list == null || list.isEmpty()) {
            throw new ServiceException();
        }
        ArrayList<Integer> idList = new ArrayList<>();
        for (SysStorage storage : list) {
            Integer storageId = storage.getStorageId();
            SalesConstant.verifySubmitStatus(status, storageId.toString(), storage.getStatus());
            idList.add(storageId);
        }
        UpdateWrapper<SysStorage> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status", status).in("storage_id", idList);
        return this.update(updateWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean audit(SysStorage storage) {
        QueryWrapper<SysStorage> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("status").eq("storage_id", storage.getStorageId());
        SysStorage one = this.getOne(queryWrapper);
        if (null == one) throw new ServiceException();
        SalesConstant.verifyAuditStatus(one.getStatus(), storage.getStatus());
        storage.setAuditBy(UserSecurityUtil.getUsername());
        storage.setAuditTime(new Date());
        // 入库时添加产品现存量
        if (SalesConstant.AUDIT.equals(storage.getStatus())) {//审核时
            repertoryService.saveAndUpdate(transformRepertory(storage, SalesConstant.AUDIT));
        } else {//反审核时
            repertoryService.saveAndUpdate(transformRepertory(storage, SalesConstant.NO_AUDIT));
        }
        return this.updateById(storage);
    }

    private List<SysRepertory> transformRepertory(SysStorage storage, String audit) {
        QueryWrapper<SysStorageSub> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("storage_id", storage.getStorageId());
        List<SysStorageSub> storageSubs = storageSubService.list(queryWrapper);
        if (storageSubs == null || storageSubs.isEmpty())
            throw new ServiceException();
        List<SysRepertory> resultList = new ArrayList<>();
        for (SysStorageSub sub : storageSubs) {
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
                    int number = one.getNumber() + repertory.getNumber();
                    BigDecimal totalPrice = BigDecimalUtil.add(one.getTotalPrice(), repertory.getTotalPrice());
                    BigDecimal price = BigDecimalUtil.div(totalPrice, number, 2);
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
                int number = one.getNumber() - repertory.getNumber();
                BigDecimal totalPrice = BigDecimalUtil.sub(one.getTotalPrice(), repertory.getTotalPrice());
                if (number < 0) {
                    throw new ServiceException(String.format("反审核失败，%s %s 现存数量(%s)小于入库数量(%s)",
                            one.getMaterielName(), one.getModelName(), one.getNumber(), repertory.getNumber()));
                }
                if (BigDecimalUtil.compareTo(totalPrice, BigDecimalUtil.ZERO) < 0) {
                    throw new ServiceException(String.format("反审核失败，%s %s 现存总金额(%s)小于入库总金额(%s)",
                            one.getMaterielName(), one.getModelName(), one.getTotalPrice(), repertory.getTotalPrice()));
                }
                BigDecimal price = number == 0 ? BigDecimalUtil.ZERO : BigDecimalUtil.div(totalPrice, number, 2);
                one.setNumber(number);
                one.setTotalPrice(totalPrice);
                one.setPrice(price);
                resultList.add(one);
            }
        }
        return resultList;
    }
}
