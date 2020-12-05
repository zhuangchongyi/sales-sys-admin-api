package com.dc.project.basis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dc.common.vo.TreeSelect;
import com.dc.project.basis.entity.SysCategory;

import java.util.List;

/**
 * 自定义类别表 服务类
 *
 * @author zhuangchongyi
 * @since 2020-09-03
 */
public interface ISysCategoryService extends IService<SysCategory> {

    List<SysCategory> list(SysCategory sysCategory);

    List<TreeSelect> treeselect(SysCategory category);

    List<TreeSelect> buildCategoryTreeSelect(List<SysCategory> categorys);
}
