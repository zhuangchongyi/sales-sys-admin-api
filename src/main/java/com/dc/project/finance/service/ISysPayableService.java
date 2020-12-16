package com.dc.project.finance.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.vo.CommonVo;
import com.dc.project.finance.entity.SysPayable;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 财务应付款主表 服务类
 *
 * @author zhuangcy
 * @since 2020-12-11
 */
public interface ISysPayableService extends IService<SysPayable> {

    IPage<SysPayable> page(Page<SysPayable> page, SysPayable payable);


    SysPayable get(Integer id);

    boolean add(SysPayable payable);

    boolean edit(SysPayable payable);

    boolean delete(Integer id);

    boolean submit(CommonVo vo);

    boolean audit(SysPayable payable);
}
