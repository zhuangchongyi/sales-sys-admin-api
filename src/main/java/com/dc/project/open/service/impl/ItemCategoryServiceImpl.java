package com.dc.project.open.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dc.common.constant.CustomConstant;
import com.dc.common.vo.TreeSelect;
import com.dc.project.basis.entity.SysCategory;
import com.dc.project.basis.service.ISysCategoryService;
import com.dc.project.open.service.ItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhuangcy
 * @date 2020/11/28
 * @description 产品类别接口实现类
 */
@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {
    @Autowired
    private ISysCategoryService categoryService;

    @Override
    public List<TreeSelect> treeSelects() {
        QueryWrapper<SysCategory> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(SysCategory::getStatus, CustomConstant.START_STATUS)
                .eq(SysCategory::getCategory, "0")
                .orderByAsc(SysCategory::getParentId);
        List<SysCategory> list = categoryService.list(wrapper);
        return categoryService.buildCategoryTreeSelect(list);
    }
}
