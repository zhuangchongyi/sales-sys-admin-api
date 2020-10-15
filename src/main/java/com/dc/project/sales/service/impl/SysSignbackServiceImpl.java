package com.dc.project.sales.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.common.constant.SalesConstant;
import com.dc.common.exception.ServiceException;
import com.dc.common.utils.BeanUtil;
import com.dc.common.utils.ObjectMapperUtil;
import com.dc.project.sales.dao.SysSignbackDao;
import com.dc.project.sales.entity.SysSignback;
import com.dc.project.sales.entity.SysSignbackSub;
import com.dc.project.sales.service.ISysSignbackService;
import com.dc.project.sales.service.ISysSignbackSubService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
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
            if (!signbackSubService.updateById(sub)) {
                throw new ServiceException("保存失败");
            }
        }
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
        SalesConstant.verifyAuditStatus(sysSignback.getStatus(), signback.getStatus());
        // TODO 修改订单子表的签收数量
        return this.updateById(sysSignback);
    }
}
