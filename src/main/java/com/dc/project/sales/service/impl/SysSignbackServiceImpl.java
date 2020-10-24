package com.dc.project.sales.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.common.constant.CustomConstant;
import com.dc.common.constant.SalesConstant;
import com.dc.common.exception.ServiceException;
import com.dc.common.utils.BeanUtil;
import com.dc.common.utils.BigDecimalUtil;
import com.dc.common.utils.CodeUtil;
import com.dc.common.utils.ObjectMapperUtil;
import com.dc.project.finance.entity.SysReceivable;
import com.dc.project.finance.service.ISysReceivableService;
import com.dc.project.sales.dao.SysSignbackDao;
import com.dc.project.sales.entity.SysOrderSub;
import com.dc.project.sales.entity.SysSignback;
import com.dc.project.sales.entity.SysSignbackSub;
import com.dc.project.sales.service.ISysOrderSubService;
import com.dc.project.sales.service.ISysSignbackService;
import com.dc.project.sales.service.ISysSignbackSubService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 销售签回主表 服务实现类
 *
 * @author zhuangcy
 * @since 2020-10-10
 */
@Service
public class SysSignbackServiceImpl extends ServiceImpl<SysSignbackDao, SysSignback> implements ISysSignbackService {
    @Autowired
    private ISysSignbackSubService signbackSubService;
    @Autowired
    private ISysReceivableService receivableService;
    @Autowired
    private ISysOrderSubService orderSubService;

    @Override
    public IPage<SysSignback> page(Page<SysSignback> page, SysSignback sysSignback) {
        return baseMapper.page(page, sysSignback);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean edit(Map<String, Object> formMap) throws InvocationTargetException, IllegalAccessException {
        Object clienteleForm = formMap.get("clientele");
        Object materielListForm = formMap.get("materielList");
        if (null == clienteleForm || null == materielListForm)
            throw new ServiceException("保存失败");

        SysSignback sysSignback = new SysSignback();
        BeanUtil.register();
        BeanUtils.populate(sysSignback, ObjectMapperUtil.toObject(clienteleForm.toString(), Map.class));
        List<Map<String, Object>> subList = ObjectMapperUtil.toObject(materielListForm.toString(), List.class);
        for (Map<String, Object> map : subList) {
            SysSignbackSub sub = new SysSignbackSub();
            BeanUtils.populate(sub, map);
            sub.setTotalPrice(BigDecimalUtil.mul(sub.getPrice(), sub.getSignNum()));
            if (!signbackSubService.updateById(sub)) {
                throw new ServiceException("保存失败");
            }
        }
        sysSignback.setSignbackStatus(CustomConstant.YES_STATUS);
        return this.updateById(sysSignback);
    }

    @Override
    public SysSignback get(Integer id) {
        return baseMapper.get(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean audit(SysSignback sysSignback) {
        SysSignback signback = this.getById(sysSignback.getSignbackId());
        if (CustomConstant.NO_STATUS.equals(signback.getSignbackStatus()))
            throw new ServiceException(String.format("请先签收 %s", signback.getSignbackNum()));
        SalesConstant.verifyAuditStatus(sysSignback.getStatus(), signback.getStatus());

        List<SysSignbackSub> subList = signbackSubService.list(new QueryWrapper<SysSignbackSub>().eq("signback_id", signback.getSignbackId()));
        if (SalesConstant.AUDIT.equals(sysSignback.getStatus())) {
            insertReceivable(signback, subList);
        } else {
            delReceivable(signback.getSignbackNum());
        }
        updateOrderSign(sysSignback.getStatus(), signback, subList);

        return this.updateById(sysSignback);
    }

    private void updateOrderSign(String status, SysSignback signback, List<SysSignbackSub> subList) {
        for (SysSignbackSub sub : subList) {
            Long orderSubId = sub.getOrderSubId();
            SysOrderSub one = orderSubService.getById(orderSubId);
            int singbackNum = 0;
            if (SalesConstant.AUDIT.equals(status)) {
                singbackNum = sub.getSignNum() + one.getHasSignbackNum();
            } else {
                singbackNum = sub.getSignNum() - one.getHasSignbackNum();
                if (singbackNum < 0) {
                    throw new ServiceException(String.format("%s %s %s签收数量计算错误", one.getMaterielNum(), one.getMaterielName(), one.getModelName()));
                }
            }
            SysOrderSub orderSub = new SysOrderSub();
            orderSub.setSubId(orderSubId);
            orderSub.setHasSignbackNum(singbackNum);
            orderSubService.updateById(orderSub);
        }

    }

    private void insertReceivable(SysSignback signback, List<SysSignbackSub> subList) {
        SysReceivable receivable = new SysReceivable();
        BeanUtil.copyBeanProp(receivable, signback);
        receivable.setAuditBy(null);
        receivable.setAuditTime(null);
        receivable.setStatus(SalesConstant.SAVE);
        receivable.setFinanceTime(new Date());
        receivable.setReceivableNum(CodeUtil.getCode(SalesConstant.FINANCE_RECEIVABLE_NO));
        BigDecimal totalPrice = BigDecimal.ZERO;
        receivable.setTotalPrice(totalPrice);
        receivable.setReceivePrice(totalPrice);
        if (!receivableService.save(receivable)) {
            throw new ServiceException("生成应收款异常");
        }
    }

    private void delReceivable(String signbackNum) {
        QueryWrapper<SysReceivable> queryWrapper = new QueryWrapper<SysReceivable>().eq("signback_num", signbackNum);
        SysReceivable one = receivableService.getOne(queryWrapper);
        if (null == one)
            return;
        if (SalesConstant.AUDIT.equals(one.getStatus()) ||
                SalesConstant.NO_AUDIT.equals(one.getStatus()) ||
                SalesConstant.SUBMIT.equals(one.getStatus())) {
            throw new ServiceException(String.format("生成签收单%s已提交审核", one.getReceivableNum()));
        }

        Integer receivableId = one.getReceivableId();
        if (!receivableService.removeById(receivableId)) {
            throw new ServiceException("应收单删除失败");
        }
    }

    @Override
    public IPage<SysSignback> findOrderByClienteleId(Page page, SysSignback sysSignback) {
        return baseMapper.findOrderByClienteleId(page, sysSignback);
    }

    @Override
    public IPage<Map<String, Object>> findFinanceOrder(Page page, SysSignback sysSignback) {
        return baseMapper.findFinanceOrder(page, sysSignback);
    }
}
