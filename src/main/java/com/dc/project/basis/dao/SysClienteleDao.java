package com.dc.project.basis.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.project.basis.entity.SysClientele;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 部门表 Mapper 接口
 *
 * @author zhuangchongyi
 * @since 2020-09-03
 */
public interface SysClienteleDao extends BaseMapper<SysClientele> {

    IPage list(Page page, @Param("cli") SysClientele clientele);

    SysClientele get(Integer clienteleId);
}
