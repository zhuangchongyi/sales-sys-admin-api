package com.dc.project.basis.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dc.project.open.vo.ItemVo;
import com.dc.common.vo.MaterielVo;
import com.dc.project.basis.entity.SysMateriel;

import java.util.List;

/**
 * 产品档案表 服务类
 *
 * @author zhuangchongyi
 * @since 2020-09-03
 */
public interface ISysMaterielService extends IService<SysMateriel> {

    IPage<SysMateriel> page(Page page, SysMateriel materiel);

    boolean insert(SysMateriel materiel);

    boolean update(SysMateriel materiel);

    SysMateriel get(Integer materielId);

    IPage list(Page page, SysMateriel materiel);

    MaterielVo findMateriel(MaterielVo materiel);

    boolean verifyStatus(Integer id);

    List<ItemVo> listItem(Page page);

    ItemVo detailItem(Integer id);

}
