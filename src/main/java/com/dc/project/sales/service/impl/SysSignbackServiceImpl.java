package com.dc.project.sales.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.common.lang.annotation.DataScope;
import com.dc.common.constant.CustomConstant;
import com.dc.common.constant.SalesConstant;
import com.dc.common.exception.ServiceException;
import com.dc.common.utils.BeanUtil;
import com.dc.common.utils.BigDecimalUtil;
import com.dc.common.utils.CodeUtil;
import com.dc.common.utils.ObjectMapperUtil;
import com.dc.project.open.vo.OrderVo;
import com.dc.project.finance.entity.SysReceivable;
import com.dc.project.finance.service.ISysReceivableService;
import com.dc.project.sales.dao.SysSignbackDao;
import com.dc.project.sales.entity.SysOrder;
import com.dc.project.sales.entity.SysOrderSub;
import com.dc.project.sales.entity.SysSignback;
import com.dc.project.sales.entity.SysSignbackSub;
import com.dc.project.sales.service.ISysOrderService;
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
    @Autowired
    private ISysOrderService orderService;

    @DataScope(userAlias = "sign", userColumn = "create_id")
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
        BigDecimal totalPrice = BigDecimalUtil.ZERO;
        for (Map<String, Object> map : subList) {
            SysSignbackSub sub = new SysSignbackSub();
            BeanUtils.populate(sub, map);
            sub.setTotalPrice(BigDecimalUtil.mul(sub.getPrice(), sub.getSignNum()));
            if (sub.getSignNum() > sub.getOutboundNum()) {
                throw new ServiceException("保存失败，签收数量超过出库数量");
            }
            if (!signbackSubService.updateById(sub)) {
                throw new ServiceException("保存失败");
            }
            totalPrice = BigDecimalUtil.add(totalPrice, sub.getTotalPrice());
        }
        sysSignback.setTotalPrice(totalPrice);
        sysSignback.setSignbackStatus(CustomConstant.YES_STATUS);
        if (!this.updateById(sysSignback)) {
            throw new ServiceException("保存失败");
        }
        return true;
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
            throw new ServiceException(String.format("%s请先签收", signback.getSignbackNum()));
        String checkStatus = sysSignback.getStatus();
        SalesConstant.verifyAuditStatus(checkStatus, signback.getStatus());
        List<SysSignbackSub> subList = signbackSubService.list(new QueryWrapper<SysSignbackSub>().eq("signback_id", signback.getSignbackId()));
        if (SalesConstant.AUDIT.equals(checkStatus)) {
            insertReceivable(signback);
        } else {
            delReceivable(signback.getSignbackNum());
            sysSignback.setSignbackStatus(CustomConstant.NO_STATUS);
        }
        updateOrderSign(checkStatus, signback, subList);
        if (!this.updateById(sysSignback)) {
            throw new ServiceException();
        }
        return true;
    }

    private void updateOrderSign(String status, SysSignback signback, List<SysSignbackSub> subList) {
        Integer orderId = signback.getOrderId();
        SysOrderSub orderSub = new SysOrderSub();
        BigDecimal signbackPrice = BigDecimalUtil.ZERO;
        for (SysSignbackSub sub : subList) {
            Long orderSubId = sub.getOrderSubId();
            SysOrderSub one = orderSubService.getById(orderSubId);
            if (SalesConstant.AUDIT.equals(status)) {
                orderSub.setHasSignbackNum(one.getHasSignbackNum() + sub.getSignNum());
                orderSub.setHasOutboundNum(one.getHasOutboundNum() - sub.getOutboundNum());
            } else {
                orderSub.setHasSignbackNum(one.getHasSignbackNum() - sub.getSignNum());
                orderSub.setHasOutboundNum(one.getHasOutboundNum() + sub.getOutboundNum());
            }
            if (one.getNumber() < orderSub.getHasSignbackNum()) {
                throw new ServiceException(String.format("%s %s %s产品累计签收数量超过订购数量", one.getMaterielNum(), one.getMaterielName(), one.getModelName()));
            } else if (orderSub.getHasSignbackNum() < 0) {
                throw new ServiceException(String.format("%s %s %s签收数量计算错误", one.getMaterielNum(), one.getMaterielName(), one.getModelName()));
            }
            orderSub.setSubId(orderSubId);
            if (!orderSubService.updateById(orderSub)) {
                throw new ServiceException();
            }
            signbackPrice = BigDecimalUtil.add(signbackPrice, BigDecimalUtil.mul(one.getPrice(), orderSub.getHasSignbackNum()));
        }
        SysOrder sysOrder = new SysOrder();
        sysOrder.setSignbackPrice(signbackPrice);
        sysOrder.setOrderId(orderId);
        if (!orderService.updateById(sysOrder)) {
            throw new ServiceException();
        }
    }

    private void insertReceivable(SysSignback signback) {
        SysReceivable receivable = new SysReceivable();
        BeanUtil.copyBeanProp(receivable, signback);
        receivable.setAuditBy(null);
        receivable.setAuditTime(null);
        receivable.setStatus(SalesConstant.SUBMIT);
        receivable.setFinanceTime(new Date());
        receivable.setReceivableNum(CodeUtil.getCode(SalesConstant.FINANCE_RECEIVABLE_NO));
        if (!receivableService.save(receivable)) {
            throw new ServiceException("生成应收款异常");
        }
    }

    private void delReceivable(String signbackNum) {
        QueryWrapper<SysReceivable> queryWrapper = new QueryWrapper<SysReceivable>().eq("signback_num", signbackNum);
        SysReceivable one = receivableService.getOne(queryWrapper);
        if (null == one) {
            log.error(String.format("%s此签收单的应收款不存在"));
            return;
        }
        if (SalesConstant.AUDIT.equals(one.getStatus())) {
            throw new ServiceException(String.format("生成应收款%s已审核", one.getReceivableNum()));
        }
        if (!receivableService.removeById(one.getReceivableId())) {
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

    @Override
    public List<OrderVo> listSign(Integer clienteleId) {
        return baseMapper.listSign(clienteleId);
    }
}
