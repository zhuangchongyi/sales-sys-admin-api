package com.dc.project.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dc.common.vo.TreeSelect;
import com.dc.project.system.entity.SysDept;

import java.util.List;

/**
 * 部门档案表 服务类
 *
 * @author zhuangchongyi
 * @since 2020-08-29
 */
public interface ISysDeptService extends IService<SysDept> {

    List<SysDept> list(SysDept sysDept);

    List<TreeSelect> treeselect();
}
