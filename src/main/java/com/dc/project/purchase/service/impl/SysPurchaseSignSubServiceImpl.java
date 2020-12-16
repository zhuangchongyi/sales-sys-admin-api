package com.dc.project.purchase.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.project.purchase.dao.SysPurchaseSignSubDao;
import com.dc.project.purchase.entity.SysPurchaseSignSub;
import com.dc.project.purchase.service.ISysPurchaseSignSubService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 采购到货单子表 服务实现类
 *
 * @author zhuangcy
 * @since 2020-12-05
 */
@Service
public class SysPurchaseSignSubServiceImpl extends ServiceImpl<SysPurchaseSignSubDao, SysPurchaseSignSub> implements ISysPurchaseSignSubService {

    @Override
    public List<SysPurchaseSignSub> list(SysPurchaseSignSub sub) {
        QueryWrapper<SysPurchaseSignSub> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(SysPurchaseSignSub::getSignId, sub.getSignId())
                .orderByAsc(SysPurchaseSignSub::getSignSubId);
        return this.list(queryWrapper);
    }
}
