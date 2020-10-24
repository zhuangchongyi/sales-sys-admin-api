package com.dc.project.basis.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.vo.R;
import com.dc.project.basis.entity.SysClienteleProduct;
import com.dc.project.basis.service.ISysClienteleProductService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 客户产品表 前端控制器
 *
 * @author zhuangchongyi
 * @since 2020-09-04
 */
@RestController
@RequestMapping("/basis/clienteleProduct")
public class SysClienteleProductController {
    @Autowired
    private ISysClienteleProductService clienteleProductService;

    @RequiresPermissions("basis:clienteleProduct:list")
    @GetMapping
    public R page(Page<SysClienteleProduct> page, @Validated SysClienteleProduct product) {
        QueryWrapper<SysClienteleProduct> queryWrapper = new QueryWrapper<SysClienteleProduct>()
                .eq("clientele_id", product.getClienteleId())
                .like(StringUtils.isNotEmpty(product.getMaterielNum()), "materiel_num", product.getMaterielNum())
                .or().like(StringUtils.isNotEmpty(product.getMaterielName()), "materiel_name", product.getMaterielName())
                .orderByDesc("create_time");
        return R.success().data(clienteleProductService.page(page, queryWrapper));
    }

    @GetMapping("/{id}")
    public R get(@PathVariable Long id) {
        QueryWrapper<SysClienteleProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(SysClienteleProduct.class,
                info -> !info.getColumn().equals("create_time") && !info.getColumn().equals("update_time"))
                .eq("product_id", id);
        return R.success().data(clienteleProductService.getOne(queryWrapper));
    }

    @RequiresPermissions("basis:clienteleProduct:delete")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id) {
        return R.success().data(clienteleProductService.removeById(id));
    }

    @RequiresPermissions("basis:clienteleProduct:add")
    @PostMapping
    public R add(@RequestBody List<SysClienteleProduct> products) {
        return R.success().data(clienteleProductService.insert(products));
    }

    @RequiresPermissions("basis:clienteleProduct:edit")
    @PutMapping
    public R update(@RequestBody SysClienteleProduct product) {
        return R.success().data(clienteleProductService.updateById(product));
    }

    /**
     * 查询客户产品
     *
     * @param page
     * @param materiel
     * @return
     */
    @GetMapping("/listMateriel")
    public R listMateriel(Page page, SysClienteleProduct materiel) {
        return R.success().data(clienteleProductService.listMateriel(page, materiel));
    }

}

