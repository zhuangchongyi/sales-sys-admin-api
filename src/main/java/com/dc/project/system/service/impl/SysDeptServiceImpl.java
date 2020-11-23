package com.dc.project.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.common.constant.CustomConstant;
import com.dc.common.vo.TreeSelect;
import com.dc.project.system.dao.SysDeptDao;
import com.dc.project.system.entity.SysDept;
import com.dc.project.system.service.ISysDeptService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 部门档案表 服务实现类
 *
 * @author zhuangchongyi
 * @since 2020-08-29
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptDao, SysDept> implements ISysDeptService {

    @Override
    public List<SysDept> list(SysDept sysDept) {
        // 查询时以树展示
        return baseMapper.list(sysDept);
    }

    @Override
    public List<TreeSelect> treeselect() {
        QueryWrapper<SysDept> queryWrapper = new QueryWrapper<SysDept>()
                .select(" dept_id, dept_name, parent_id")
                .eq("status", CustomConstant.START_STATUS)
                .orderByAsc("dept_id")
                .orderByAsc("parent_id");
        return buildDeptTreeSelect(this.list(queryWrapper));
    }

    public List<TreeSelect> buildDeptTreeSelect(List<SysDept> depts) {
        List<SysDept> DeptTrees = buildDeptTree(depts);
        return DeptTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 构建前端所需要树结构
     *
     * @param Depts 菜单列表
     * @return 树结构列表
     */
    public List<SysDept> buildDeptTree(List<SysDept> Depts) {
        List<SysDept> returnList = new ArrayList<>();
        for (Iterator<SysDept> iterator = Depts.iterator(); iterator.hasNext(); ) {
            SysDept t = iterator.next();
            // 根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == 0) {
                recursionFn(Depts, t);
                returnList.add(t);
            }
        }
        if (returnList.isEmpty()) {
            returnList = Depts;
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<SysDept> list, SysDept t) {
        // 得到子节点列表
        List<SysDept> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysDept tChild : childList) {
            if (hasChild(list, tChild)) {
                // 判断是否有子节点
                Iterator<SysDept> it = childList.iterator();
                while (it.hasNext()) {
                    SysDept n = it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysDept> getChildList(List<SysDept> list, SysDept t) {
        List<SysDept> tlist = new ArrayList<>();
        Iterator<SysDept> it = list.iterator();
        while (it.hasNext()) {
            SysDept n = it.next();
            if (n.getParentId().longValue() == t.getDeptId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysDept> list, SysDept t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }
}
