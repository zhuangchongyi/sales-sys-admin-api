package com.dc.project.purchase.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.project.purchase.dao.SysPurchaseReturnsSubDao;
import com.dc.project.purchase.entity.SysPurchaseReturnsSub;
import com.dc.project.purchase.service.ISysPurchaseReturnsSubService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 采购退货单子表 服务实现类
 *
 * @author zhuangcy
 * @since 2020-12-10
 */
@Service
public class SysPurchaseReturnsSubServiceImpl extends ServiceImpl<SysPurchaseReturnsSubDao, SysPurchaseReturnsSub> implements ISysPurchaseReturnsSubService {

    @Override
    public List<SysPurchaseReturnsSub> list(SysPurchaseReturnsSub returnsSub) {
        QueryWrapper<SysPurchaseReturnsSub> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(SysPurchaseReturnsSub::getReturnsId, returnsSub.getReturnsId())
                .orderByAsc(SysPurchaseReturnsSub::getReturnsSubId);
        return this.list(queryWrapper);
    }
}
