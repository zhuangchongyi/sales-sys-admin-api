package com.dc.project.basis.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.project.open.vo.ItemVo;
import com.dc.common.vo.MaterielVo;
import com.dc.project.basis.entity.SysMateriel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 产品档案表 Mapper 接口
 *
 * @author zhuangchongyi
 * @since 2020-09-03
 */
public interface SysMaterielDao extends BaseMapper<SysMateriel> {

    IPage<SysMateriel> page(Page page, @Param("mat") SysMateriel materiel);

    SysMateriel get(Integer materielId);

    IPage<MaterielVo> list(Page page, @Param("mat") SysMateriel materiel);

    MaterielVo findMateriel(MaterielVo materiel);

    List<ItemVo> listItem(Page page);

    ItemVo detailItem(Integer id);

}
