package com.dc.project.basis.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.project.basis.dao.SysClienteleDao;
import com.dc.project.basis.entity.SysClientele;
import com.dc.project.basis.service.ISysClienteleService;
import org.springframework.stereotype.Service;

/**
 * 部门表 服务实现类
 *
 * @author zhuangchongyi
 * @since 2020-09-03
 */
@Service
public class SysClienteleServiceImpl extends ServiceImpl<SysClienteleDao, SysClientele> implements ISysClienteleService {

    @Override
    public IPage list(Page page, SysClientele clientele) {
        return baseMapper.list(page, clientele);
    }

    @Override
    public SysClientele get(Integer clienteleId) {
        return baseMapper.get(clienteleId);
    }

}
