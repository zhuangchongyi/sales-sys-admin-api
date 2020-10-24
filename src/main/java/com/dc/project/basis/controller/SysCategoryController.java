package com.dc.project.basis.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dc.common.vo.R;
import com.dc.common.exception.ServiceException;
import com.dc.project.basis.entity.SysCategory;
import com.dc.project.basis.service.ISysCategoryService;
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


    @RequiresPermissions(value = {"basis:materielType:list", "basis:clienteleType:list"}, logical = Logical.OR)
    @GetMapping
    public R list(SysCategory category) {
        return R.success().data(categoryService.list(category));
    }

    @GetMapping("/{categoryId}")
    public R get(@PathVariable Integer categoryId) {
        QueryWrapper<SysCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("category_id, category_num, category_name, parent_id, category, status, remark").eq("category_id", categoryId);
        return R.success().data(categoryService.getOne(queryWrapper));
    }

    @RequiresPermissions(value = {"basis:materielType:add", "basis:clienteleType:add"}, logical = Logical.OR)
    @PostMapping
    public R add(@RequestBody @Validated SysCategory category) {
        int count = categoryService.count(new QueryWrapper<SysCategory>()
                .select("category_id").eq("category", category.getCategory()).eq("category_num", category.getCategoryNum()));
        if (count > 0)
            throw new ServiceException("编码不允许重复");
        return R.success().data(categoryService.save(category));
    }

    @RequiresPermissions(value = {"basis:materielType:edit", "basis:clienteleType:edit"}, logical = Logical.OR)
    @PutMapping
    public R update(@RequestBody @Validated SysCategory category) {
        SysCategory res = categoryService.getOne(new QueryWrapper<SysCategory>()
                .select("category_id").eq("category", category.getCategory()).eq("category_num", category.getCategoryNum()));
        if (null != res && !res.getCategoryId().equals(category.getCategoryId()))
            throw new ServiceException("编码不允许重复");
        return R.success().data(categoryService.updateById(category));
    }

    @RequiresPermissions(value = {"basis:materielType:delete", "basis:clienteleType:delete"}, logical = Logical.OR)
    @DeleteMapping("/{categoryId}")
    public R delete(@PathVariable Integer categoryId) {
        // 类别有下级不能删除
        int count = categoryService.count(new QueryWrapper<SysCategory>().select("category_id").eq("parent_id", categoryId));
        if (count > 0) {
            throw new ServiceException("该类别存在下级不允许删除");
        }
        //类别被应用不能删除
        return R.success().data(categoryService.removeById(categoryId));
    }

    @GetMapping("/selectTree")
    public R selectTree(SysCategory category) {
        return R.success().data(categoryService.treeselect(category));
    }


}

