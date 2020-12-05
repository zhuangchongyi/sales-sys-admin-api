package com.dc.project.open.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.vo.R;
import com.dc.project.open.service.IItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @author zhuangcy
 * @date 2020/11/16 9:36
 * @description 商品请求控制层
 */
@Api(tags = "商品请求接口")
@RestController
@RequestMapping("/open/item")
@Validated
public class ItemController {
    @Autowired
    private IItemService itemService;

    @ApiOperation(value = "商品分页列表")
    @GetMapping("/list")
    public R list(
            @ApiParam(name = "current", value = "页码", required = true)
            @NotNull(message = "页码不能为空") Integer current,
            @ApiParam(name = "size", value = "条数", required = true)
            @NotNull(message = "页数不能为空") Integer size) {
        return R.success().data(itemService.list(new Page<>(current, size)));
    }

    @ApiOperation(value = "商品详情信息")
    @GetMapping("/detail/{id}")
    public R detail(
            @ApiParam(name = "id", value = "商品id", required = true)
            @NotNull(message = "商品不能为空")
            @PathVariable Integer id) {
        return R.success().data(itemService.detail(id));
    }

    @ApiOperation(value = "分类商品分页列表")
    @GetMapping("/category")
    public R itemByCategory(
            @ApiParam(name = "id", value = "分类id", required = true)
            @NotNull(message = "分类id") Integer id,
            @ApiParam(name = "current", value = "页码", required = true)
            @NotNull(message = "页码不能为空") Integer current,
            @ApiParam(name = "size", value = "条数", required = true)
            @NotNull(message = "页数不能为空") Integer size) {
        return R.success().data(itemService.list(new Page<>(current, size), id));
    }

}
