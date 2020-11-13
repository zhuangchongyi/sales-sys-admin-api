package com.dc.project.finance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.common.constant.CustomConstant;
import com.dc.common.constant.SalesConstant;
import com.dc.common.exception.ServiceException;
import com.dc.common.lang.annotation.DataScope;
import com.dc.common.utils.*;
import com.dc.project.finance.dao.SysReceivableDao;
import com.dc.project.finance.entity.SysReceivable;
import com.dc.project.finance.service.ISysReceivableService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;

/**
 * 财务应收款主表 服务实现类
 *
 * @author zhuangcy
 * @since 2020-10-21
 */
@Service
public class SysReceivableServiceImpl extends ServiceImpl<SysReceivableDao, SysReceivable> implements ISysReceivableService {

    @DataScope(userColumn = "create_id")
    @Override
    public IPage<SysReceivable> page(Page<SysReceivable> page, SysReceivable receivable) {
        QueryWrapper<SysReceivable> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .like(StringUtils.isNotEmpty(receivable.getReceivableNum()), SysReceivable::getReceivableNum, receivable.getReceivableNum())
                .like(StringUtils.isNotEmpty(receivable.getClienteleNum()), SysReceivable::getClienteleNum, receivable.getClienteleNum())
                .like(StringUtils.isNotEmpty(receivable.getClienteleName()), SysReceivable::getClienteleName, receivable.getClienteleName())
                .apply(null != receivable.getParams().get(CustomConstant.DATA_SCOPE),
                        ObjectUtil.toString(receivable.getParams().get(CustomConstant.DATA_SCOPE)).replaceFirst("and", ""))
                .orderByDesc(SysReceivable::getCreateTime);
        return this.page(page, queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean submit(Integer[] ids, String status) {
        if (ids.length == 0 || StringUtils.isEmpty(status))
            throw new ServiceException("未选择数据");
        List<SysReceivable> list = this.list(new QueryWrapper<SysReceivable>().select("receivable_id,receivable_num,status").in("receivable_id", Arrays.asList(ids)));
        if (list == null || list.isEmpty())
            throw new ServiceException("操作失败");
        List<Integer> idList = new ArrayList<>();
        for (SysReceivable receivable : list) {
            SalesConstant.verifySubmitStatus(status, receivable.getReceivableNum(), receivable.getStatus());
            idList.add(receivable.getReceivableId());
        }
        UpdateWrapper<SysReceivable> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status", status).in("receivable_id", idList);
        return this.update(updateWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean audit(SysReceivable receivable) {
        SysReceivable one = this.getById(receivable.getReceivableId());
        SalesConstant.verifyAuditStatus(receivable.getStatus(), one.getStatus());
        if (BigDecimalUtil.compareTo(one.getHasVerificaPrice(), BigDecimalUtil.ZERO) > 0) {
            throw new ServiceException("已核销不允许操作");
        }
        receivable.setAuditTime(new Date());
        receivable.setAuditBy(UserSecurityUtil.getUsername());
        return this.updateById(receivable);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String saveAndUpdate(Map<String, Object> formMap) throws InvocationTargetException, IllegalAccessException {
        Object clienteleForm = formMap.get("clienteleForm");
        if (null == clienteleForm) {
            throw new ServiceException();
        }
        SysReceivable sysReceivable = new SysReceivable();
        BeanUtil.register();
        BeanUtils.populate(sysReceivable, ObjectMapperUtil.toObject(clienteleForm.toString(), Map.class));
        if (null == sysReceivable.getReceivableId()) {
            sysReceivable.setReceivableNum(CodeUtil.getCode(SalesConstant.FINANCE_RECEIVABLE_NO));
            sysReceivable.setSourceType(CustomConstant.YES_STATUS);
            if (!this.save(sysReceivable)) {
                throw new ServiceException("保存失败");
            }
        } else {
            this.updateById(sysReceivable);
        }
        return sysReceivable.getReceivableNum();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(Integer id) {
        SysReceivable one = this.getById(id);
        if (one == null) {
            throw new ServiceException("未找到应收单");
        }
        if (BigDecimalUtil.compareTo(one.getHasVerificaPrice(), BigDecimalUtil.ZERO) > 0) {
            throw new ServiceException("已核销不允许删除");
        }
        if (!SalesConstant.NO_SUBMIT.equals(one.getStatus()) || !SalesConstant.SAVE.equals(one.getStatus())) {
            if (!this.removeById(id)) {
                throw new ServiceException();
            }
        } else {
            throw new ServiceException("请收回在删除");
        }
        return true;
    }

    @Override
    public BigDecimal findReceivePriceByClienteleId(Integer id) {
        SysReceivable receivable = new SysReceivable();
        receivable.setClienteleId(id);
        receivable.setStatus(SalesConstant.AUDIT);
        return baseMapper.getReceivePrice(receivable);
    }

    @Override
    public List<SysReceivable> getClienteleReceivable(SysReceivable receivable) {
        return baseMapper.getClienteleReceivable(receivable);
    }

    @Override
    public List<SysReceivable> getClienteleReceivableList(SysReceivable receivable) {
        return baseMapper.getClienteleReceivableList(receivable);
    }
}
