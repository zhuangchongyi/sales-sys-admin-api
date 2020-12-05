package com.dc.project.open.service;

import com.dc.common.vo.TreeSelect;

import java.util.List;

/**
 * @author zhuangcy
 * @date 2020/11/28
 * @description 产品类别接口
 */
public interface ItemCategoryService {

    /**
     * 产品类别树
     *
     * @return
     */
    List<TreeSelect> treeSelects();
}
