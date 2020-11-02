package com.dc.project.finance.service.impl;

import com.dc.project.finance.entity.SysWriteoffSub;
import com.dc.project.finance.dao.SysWriteoffSubDao;
import com.dc.project.finance.service.ISysWriteoffSubService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 应收核销明细表 服务实现类
 *
 * @author zhuangcy
 * @since 2020-10-26
 */
@Service
public class SysWriteoffSubServiceImpl extends ServiceImpl<SysWriteoffSubDao, SysWriteoffSub> implements ISysWriteoffSubService {

    @Override
    public List<SysWriteoffSub> findReceiptPrice(Integer writeoffId) {
        return baseMapper.findReceiptPrice(writeoffId);
    }

    @Override
    public List<SysWriteoffSub> findReceivablePrice(Integer writeoffId) {
        return baseMapper.findReceivablePrice(writeoffId);
    }
}
