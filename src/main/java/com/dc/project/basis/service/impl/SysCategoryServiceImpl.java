package com.dc.project.basis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.common.vo.TreeSelect;
import com.dc.project.basis.dao.SysCategoryDao;
import com.dc.project.basis.entity.SysCategory;
import com.dc.project.basis.service.ISysCategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义类别表 服务实现类
 *
 * @author zhuangchongyi
 * @since 2020-09-03
 */
@Service
public class SysCategoryServiceImpl extends ServiceImpl<SysCategoryDao, SysCategory> implements ISysCategoryService {

    @Override
    public List<SysCategory> list(SysCategory SysCategory) {
        return baseMapper.list(SysCategory);
    }

    @Override
    public List<TreeSelect> treeselect(SysCategory category) {
        category.setStatus("0");
        return buildCategoryTreeSelect(this.list(category));
    }

    @Override
    public List<TreeSelect> buildCategoryTreeSelect(List<SysCategory> categorys) {
        List<SysCategory> categoryTrees = buildCategoryTree(categorys);
        return categoryTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 构建前端所需要树结构
     *
     * @param categorys 菜单列表
     * @return 树结构列表
     */
    public List<SysCategory> buildCategoryTree(List<SysCategory> categorys) {
        List<SysCategory> returnList = new ArrayList<>();
        for (Iterator<SysCategory> iterator = categorys.iterator(); iterator.hasNext(); ) {
            SysCategory t = iterator.next();
            // 根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == 0) {
                recursionFn(categorys, t);
                returnList.add(t);
            }
        }
        if (returnList.isEmpty()) {
            returnList = categorys;
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<SysCategory> list, SysCategory t) {
        // 得到子节点列表
        List<SysCategory> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysCategory tChild : childList) {
            if (hasChild(list, tChild)) {
                // 判断是否有子节点
                Iterator<SysCategory> it = childList.iterator();
                while (it.hasNext()) {
                    SysCategory n = it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysCategory> getChildList(List<SysCategory> list, SysCategory t) {
        List<SysCategory> tlist = new ArrayList<>();
        Iterator<SysCategory> it = list.iterator();
        while (it.hasNext()) {
            SysCategory n = it.next();
            if (n.getParentId().longValue() == t.getCategoryId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysCategory> list, SysCategory t) {
        return getChildList(list, t).size() > 0;
    }
}
