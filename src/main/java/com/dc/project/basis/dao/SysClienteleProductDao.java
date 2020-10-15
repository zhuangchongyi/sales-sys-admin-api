package com.dc.project.basis.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.project.basis.entity.SysClienteleProduct;
import org.apache.ibatis.annotations.Param;

/**
 * 客户产品表 Mapper 接口
 *
 * @author zhuangchongyi
 * @since 2020-09-04
 */
public interface SysClienteleProductDao extends BaseMapper<SysClienteleProduct> {

    IPage<SysClienteleProduct> listMateriel(Page page, @Param("mat") SysClienteleProduct materiel);

}
