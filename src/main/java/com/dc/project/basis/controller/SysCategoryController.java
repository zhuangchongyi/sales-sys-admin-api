package com.dc.project.basis.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dc.common.lang.annotation.RepeatSubmit;
import com.dc.common.constant.CustomConstant;
import com.dc.common.exception.ServiceException;
import com.dc.common.vo.R;
import com.dc.project.basis.entity.SysCategory;
import com.dc.project.basis.entity.SysMateriel;
import com.dc.project.basis.service.ISysCategoryService;
import com.dc.project.basis.service.ISysMaterielService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 自定义类别表 前端控制器
 *
 * @author zhuangchongyi
 * @since 2020-09-03
 */
@RestController
@RequestMapping("/basis/category")
public class SysCategoryController {
    @Autowired
    private ISysCategoryService categoryService;
    @Autowired
    private ISysMaterielService materielService;


    @RequiresPermissions(value = {"basis:materielType:list", "basis:clienteleType:list"}, logical = Logical.OR)
    @GetMapping
    public R list(SysCategory category) {
        return R.success().data(categoryService.list(category));
    }

    @GetMapping("/{categoryId}")
    public R get(@PathVariable Integer categoryId) {
        QueryWrapper<SysCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(SysCategory.class,
                info -> !info.getColumn().equals("create_time") && !info.getColumn().equals("create_by"))
                .eq("category_id", categoryId);
        return R.success().data(categoryService.getOne(queryWrapper, false));
    }

    @RepeatSubmit
    @RequiresPermissions(value = {"basis:materielType:add", "basis:clienteleType:add"}, logical = Logical.OR)
    @PostMapping
    public R add(@RequestBody @Validated SysCategory category) {
        SysCategory count = categoryService.getOne(new QueryWrapper<SysCategory>()
                .select("category_id").eq("category", category.getCategory())
                .eq("category_num", category.getCategoryNum()), false);
        if (count != null)
            throw new ServiceException("编码不允许重复");
        SysCategory info = categoryService.getById(category.getParentId());
        if (info != null && CustomConstant.STOP_STATUS.equals(info.getStatus())) {
            throw new ServiceException(String.format("%s类别已停用，不允许新增", info.getCategoryNum()));
        }
        return R.success().data(categoryService.save(category));
    }

    @RepeatSubmit
    @RequiresPermissions(value = {"basis:materielType:edit", "basis:clienteleType:edit"}, logical = Logical.OR)
    @PutMapping
    public R update(@RequestBody @Validated SysCategory category) {
        SysCategory res = categoryService.getOne(new QueryWrapper<SysCategory>()
                .select("category_id").eq("category", category.getCategory())
                .eq("category_num", category.getCategoryNum()), false);
        if (null != res && !res.getCategoryId().equals(category.getCategoryId()))
            throw new ServiceException("编码不允许重复");
        SysCategory info = categoryService.getById(category.getParentId());
        if (info != null && CustomConstant.STOP_STATUS.equals(info.getStatus())) {
            throw new ServiceException(String.format("%s类别已停用，不允许修改", info.getCategoryNum()));
        }
        return R.success().data(categoryService.updateById(category));
    }

    @RepeatSubmit
    @RequiresPermissions(value = {"basis:materielType:delete", "basis:clienteleType:delete"}, logical = Logical.OR)
    @DeleteMapping("/{categoryId}")
    public R delete(@PathVariable Integer categoryId) {
        // 类别有下级不能删除
        SysCategory category = categoryService.getOne(new QueryWrapper<SysCategory>()
                .select("category_id").eq("parent_id", categoryId), false);
        if (null != category) throw new ServiceException("该类别存在下级，不允许删除");
        //类别被应用不能删除
        SysMateriel materiel = materielService.getOne(new QueryWrapper<SysMateriel>()
                .eq("category_id", categoryId), false);
        if (null != materiel) throw new ServiceException("该类别已有产品，不允许删除");
        return R.success().data(categoryService.removeById(categoryId));
    }

    @GetMapping("/selectTree")
    public R selectTree(SysCategory category) {
        return R.success().data(categoryService.treeselect(category));
    }


}

