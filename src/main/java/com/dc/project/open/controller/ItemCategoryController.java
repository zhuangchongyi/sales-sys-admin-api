package com.dc.project.open.controller;

import com.dc.common.vo.R;
import com.dc.project.open.service.ItemCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuangcy
 * @date 2020/11/16 9:36
 * @description 商品类别请求控制层
 */
@Api(tags = "商品分类请求接口")
@RestController
@RequestMapping("/open/category")
@Validated
public class ItemCategoryController {
    @Autowired
    private ItemCategoryService categoryService;

    @ApiOperation(value = "产品类别树")
    @GetMapping
    public R treeSelects() {
        return R.success().data(categoryService.treeSelects());
    }


}
