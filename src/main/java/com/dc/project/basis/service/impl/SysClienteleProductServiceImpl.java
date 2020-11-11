package com.dc.project.basis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.common.exception.ServiceException;
import com.dc.common.utils.BeanUtil;
import com.dc.project.basis.dao.SysClienteleProductDao;
import com.dc.project.basis.entity.SysClienteleProduct;
import com.dc.project.basis.entity.SysMateriel;
import com.dc.project.basis.service.ISysClienteleProductService;
import com.dc.project.basis.service.ISysMaterielService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户产品表 服务实现类
 *
 * @author zhuangchongyi
 * @since 2020-09-04
 */
@Slf4j
@Service
public class SysClienteleProductServiceImpl extends ServiceImpl<SysClienteleProductDao, SysClienteleProduct> implements ISysClienteleProductService {
    @Autowired
    private ISysMaterielService materielService;


    @Override
    public IPage listMateriel(Page page, SysClienteleProduct materiel) {
        if (null == materiel.getClienteleId()) {
            SysMateriel sysMateriel = new SysMateriel();
            BeanUtil.copyBeanProp(sysMateriel, materiel);
            return materielService.list(page, sysMateriel);
        } else {
            return baseMapper.listMateriel(page, materiel);
        }
    }

    @Override
    public boolean insert(List<SysClienteleProduct> products) {
        if (null != products && !products.isEmpty()) {
            for (SysClienteleProduct product : products) {
                SysClienteleProduct one = this.getOne(new QueryWrapper<SysClienteleProduct>()
                        .select("pk_id")
                        .eq("materiel_id", product.getMaterielId())
                        .eq("clientele_id", product.getClienteleId())
                        .eq("model_name", product.getModelName()), false);
                if (one == null) {
                    if (!this.save(product)) throw new ServiceException("添加产品失败");
                } else {
                    log.info(String.format("产品 %s / %s 重复添加", product.getMaterielId(), product.getModelName()));
                }
            }
            return true;
        }
        throw new ServiceException("添加产品失败 0");
    }

}
