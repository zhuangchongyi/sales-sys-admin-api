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
import com.dc.project.warehouse.dao.SysScrapDao;
import com.dc.project.warehouse.entity.SysRepertory;
import com.dc.project.warehouse.entity.SysScrap;
import com.dc.project.warehouse.entity.SysScrapSub;
import com.dc.project.warehouse.service.ISysRepertoryService;
import com.dc.project.warehouse.service.ISysScrapService;
import com.dc.project.warehouse.service.ISysScrapSubService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 报废单主表 服务实现类
 *
 * @author zhuangcy
 * @since 2020-09-25
 */
@Service
public class SysScrapServiceImpl extends ServiceImpl<SysScrapDao, SysScrap> implements ISysScrapService {
    @Autowired
    private ISysScrapSubService scrapSubService;
    @Autowired
    private ISysRepertoryService repertoryService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addAndUpdate(Map formMap) throws Exception {
        Object scrapForm = formMap.get("scrap");
        Object scrapSubForm = formMap.get("scrapSub");
        Object delSubIdsForm = formMap.get("delSubIds");
        if (null == scrapForm || null == scrapSubForm)
            throw new ServiceException("保存失败");
        SysScrap sysScrap = ObjectMapperUtil.toObject(scrapForm.toString(), SysScrap.class);
        if (null == sysScrap.getWarehouseId())
            throw new ServiceException("保存失败，未选择仓库");
        if (null == sysScrap.getScrapId()) { //主表不存在
            if (!this.save(sysScrap))
                throw new ServiceException("保存失败");
        } else {
            update(sysScrap);
        }
        List<Map> mapList = ObjectMapperUtil.toObject(scrapSubForm.toString(), List.class);
        List<SysScrapSub> addList = new ArrayList<>();
        List<SysScrapSub> updateList = new ArrayList<>();
        for (Map map : mapList) {
            SysScrapSub scrapSub = new SysScrapSub();
            BeanUtil.register();
            BeanUtils.populate(scrapSub, map);
            if (null == scrapSub.getSubId()) {
                scrapSub.setScrapId(sysScrap.getScrapId());
                scrapSub.setWarehouseId(sysScrap.getWarehouseId());
                addList.add(scrapSub);
            } else {
                updateList.add(scrapSub);
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

    private void update(SysScrap scrap) {
        UpdateWrapper<SysScrap> uw = new UpdateWrapper<>();
        uw.set(null != scrap.getScrapTime(), "scrap_time", scrap.getScrapTime());
        uw.set(null != scrap.getPersonnelId(), "personnel_id", scrap.getPersonnelId());
        uw.set(StringUtils.isNotEmpty(scrap.getPersonnelName()), "personnel_name", scrap.getPersonnelName());
        uw.set(StringUtils.isNotEmpty(scrap.getRemark()), "remark", scrap.getRemark());
        uw.eq("scrap_id", scrap.getScrapId());
        this.update(uw);
    }


    private void delSub(List<Long> delSubIds) {
        if (!delSubIds.isEmpty()) {
            scrapSubService.removeByIds(delSubIds);
        }
    }

    private void insertAndUpdate(List<SysScrapSub> subList) {
        if (!subList.isEmpty()) {
            for (SysScrapSub sub : subList) {
                if (null == sub.getSubId()) {
                    scrapSubService.save(sub);
                } else {
                    UpdateWrapper<SysScrapSub> subUW = new UpdateWrapper<>();
                    subUW.set("price", sub.getPrice());
                    subUW.set("scrap_num", sub.getScrapNum());
                    subUW.set("remark", sub.getRemark());
                    subUW.eq("sub_id", sub.getSubId());
                    scrapSubService.update(subUW);
                }
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(Integer id) {
        scrapSubService.remove(new QueryWrapper<SysScrapSub>().eq("scrap_id", id));
        return this.removeById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean submit(Integer[] ids, String status) {
        if (null == ids || ids.length == 0) {
            throw new ServiceException();
        }
        QueryWrapper<SysScrap> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("scrap_id,status").in("scrap_id", Arrays.asList(ids));
        List<SysScrap> list = this.list(queryWrapper);
        if (list == null || list.isEmpty()) {
            throw new ServiceException();
        }
        ArrayList<Integer> idList = new ArrayList<>();
        for (SysScrap scrap : list) {
            Integer scrapId = scrap.getScrapId();
            SalesConstant.verifySubmitStatus(status, scrapId.toString(), scrap.getStatus());
            idList.add(scrapId);
        }
        UpdateWrapper<SysScrap> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status", status).in("scrap_id", idList);
        return this.update(updateWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean audit(SysScrap scrap) {
        SysScrap one = this.getById(scrap.getScrapId());
        if (null == one) throw new ServiceException();
        SalesConstant.verifyAuditStatus(one.getStatus(), scrap.getStatus());
        scrap.setAuditBy(UserSecurityUtils.getUsername());
        scrap.setAuditTime(new Date());
        // 报废时时扣减产品现存量
        if (SalesConstant.AUDIT.equals(scrap.getStatus())) {//审核时
            repertoryService.saveAndUpdate(transformRepertory(scrap, SalesConstant.AUDIT));
        } else {// 反审核时
            repertoryService.saveAndUpdate(transformRepertory(scrap, SalesConstant.NO_AUDIT));
        }
        return this.updateById(scrap);
    }

    private List<SysRepertory> transformRepertory(SysScrap scrap, String audit) {
        List<SysScrapSub> scrapSubs = scrapSubService.list(new QueryWrapper<SysScrapSub>().eq("scrap_id", scrap.getScrapId()));
        if (scrapSubs == null || scrapSubs.isEmpty())
            throw new ServiceException();
        List<SysRepertory> resultList = new ArrayList<>();
        for (SysScrapSub sub : scrapSubs) {
            SysRepertory repertory = new SysRepertory();
            BeanUtil.copyBeanProp(repertory, sub);
            // 现存表的产品根据产品id，仓库id，型号确定唯一性
            if (null == repertory.getMaterielId() || null == repertory.getWarehouseId() || StringUtils.isEmpty(repertory.getModelName()))
                throw new ServiceException();
            SysRepertory one = repertoryService.getSysRepertory(repertory);
            if (SalesConstant.AUDIT.equals(audit)) {
                if (null == one) {
                    throw new ServiceException();
                } else {
                    int number = one.getNumber() - sub.getScrapNum();
                    if (number < 0) {
                        throw new ServiceException(String.format("审核失败，%s %s 现存数量(%s)小于报废数量(%s)",
                                one.getMaterielName(), one.getModelName(), one.getNumber(), sub.getScrapNum()));
                    }
                    BigDecimal totalPrice = number == 0 ? BigDecimalUtil.ZERO : BigDecimalUtil.mul(one.getPrice(), number);
                    one.setNumber(number);
                    one.setTotalPrice(totalPrice);
                    resultList.add(one);
                }
            } else {
                if (null == one) {
                    throw new ServiceException(String.format("反审核失败，%s %s 该产品不存在",
                            repertory.getMaterielName(), repertory.getModelName()));
                }
                int number = one.getNumber() + sub.getScrapNum();
                BigDecimal totalPrice = BigDecimalUtil.mul(one.getPrice(), number);
                one.setNumber(number);
                one.setTotalPrice(totalPrice);
                resultList.add(one);
            }
        }
        return resultList;
    }
}
