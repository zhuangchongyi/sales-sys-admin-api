package com.dc.project.basis.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dc.project.basis.entity.SysClientele;

/**
 * 部门表 服务类
 *
 * @author zhuangchongyi
 * @since 2020-09-03
 */
public interface ISysClienteleService extends IService<SysClientele> {

    IPage list(Page page, SysClientele clientele);

    SysClientele get(Integer clienteleId);

}
