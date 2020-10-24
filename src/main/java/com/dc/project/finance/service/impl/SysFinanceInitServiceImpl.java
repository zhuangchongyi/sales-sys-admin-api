package com.dc.project.finance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.common.exception.ServiceException;
import com.dc.common.utils.BeanUtil;
import com.dc.common.utils.ObjectMapperUtil;
import com.dc.project.finance.dao.SysFinanceInitDao;
import com.dc.project.finance.entity.SysFinanceInit;
import com.dc.project.finance.service.ISysFinanceInitService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 财务初始化表 服务实现类
 *
 * @author zhuangcy
 * @since 2020-10-19
 */
@Service
public class SysFinanceInitServiceImpl extends ServiceImpl<SysFinanceInitDao, SysFinanceInit> implements ISysFinanceInitService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveAndUpdate(Map<String, Object> formMap) throws InvocationTargetException, IllegalAccessException {
        Object clienteleForm = formMap.get("clienteleForm");
        Object orderList = formMap.get("orderList");
        Object delSubIds = formMap.get("delSubIds");
        if (null == clienteleForm || null == orderList) {
            throw new ServiceException();
        }
        SysFinanceInit financeInit = new SysFinanceInit();
        BeanUtil.register();
        BeanUtils.populate(financeInit, ObjectMapperUtil.toObject(clienteleForm.toString(), Map.class));
        if (null == financeInit.getInitId()) {
            if (!this.save(financeInit)) {
                throw new ServiceException("保存失败");
            }
        } else {
            if (!this.updateById(financeInit)) {
                throw new ServiceException("保存失败");
            }
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(Integer id) {
        return this.removeById(id);
    }
}
