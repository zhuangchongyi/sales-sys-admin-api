package com.dc.project.basis.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dc.project.basis.entity.SysClienteleProduct;

import java.util.List;

/**
 * 客户产品表 服务类
 *
 * @author zhuangchongyi
 * @since 2020-09-04
 */
public interface ISysClienteleProductService extends IService<SysClienteleProduct> {

    IPage listMateriel(Page page, SysClienteleProduct materiel);

    boolean insert(List<SysClienteleProduct> products);

}
