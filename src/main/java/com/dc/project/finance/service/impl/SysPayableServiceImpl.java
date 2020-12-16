package com.dc.project.finance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.common.constant.CustomConstant;
import com.dc.common.constant.SalesConstant;
import com.dc.common.exception.ServiceException;
import com.dc.common.utils.BigDecimalUtil;
import com.dc.common.utils.ObjectUtil;
import com.dc.common.vo.CommonVo;
import com.dc.project.finance.dao.SysPayableDao;
import com.dc.project.finance.entity.SysPayable;
import com.dc.project.finance.service.ISysPayableService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 财务应付款主表 服务实现类
 *
 * @author zhuangcy
 * @since 2020-12-11
 */
@Service
public class SysPayableServiceImpl extends ServiceImpl<SysPayableDao, SysPayable> implements ISysPayableService {

    @Override
    public IPage<SysPayable> page(Page<SysPayable> page, SysPayable payable) {
        QueryWrapper<SysPayable> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .like(StringUtils.isNotEmpty(payable.getPayableNum()), SysPayable::getPayableNum, payable.getPayableNum())
                .like(StringUtils.isNotEmpty(payable.getClienteleNum()), SysPayable::getClienteleNum, payable.getClienteleNum())
                .like(StringUtils.isNotEmpty(payable.getClienteleName()), SysPayable::getClienteleName, payable.getClienteleName())
                .apply(null != payable.getParams().get(CustomConstant.DATA_SCOPE),
                        ObjectUtil.toString(payable.getParams().get(CustomConstant.DATA_SCOPE)).replaceFirst("and", ""))
                .orderByDesc(SysPayable::getCreateTime);
        return page(page, queryWrapper);
    }

    @Override
    public SysPayable get(Integer id) {
        QueryWrapper<SysPayable> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .select(SysPayable.class, info ->
                        !"create_time".equals(info.getColumn()) && !"create_by".equals(info.getColumn()) &&
                                !"audit_time".equals(info.getColumn()) && !"audit_by".equals(info.getColumn()))
                .eq(SysPayable::getPayableId, id);
        return this.getOne(queryWrapper, false);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean add(SysPayable payable) {
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean edit(SysPayable payable) {
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(Integer id) {
        SysPayable one = this.getById(id);
        if (one == null) {
            throw new ServiceException("未找到应付单");
        }
        if (BigDecimalUtil.compareTo(one.getHasVerificaPrice(), BigDecimalUtil.ZERO) > 0) {
            throw new ServiceException("已核销不允许删除");
        }
        SalesConstant.verifyDeleteStatus(one.getStatus());
        if (!this.removeById(id)) {
            throw new ServiceException();
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean submit(CommonVo vo) {
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean audit(SysPayable payable) {
        return false;
    }
}
