package com.dc.project.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dc.common.lang.annotation.RepeatSubmit;
import com.dc.common.constant.CustomConstant;
import com.dc.common.exception.ServiceException;
import com.dc.common.vo.R;
import com.dc.project.system.entity.SysDept;
import com.dc.project.system.entity.SysUser;
import com.dc.project.system.service.ISysDeptService;
import com.dc.project.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 部门档案表 前端控制器
 *
 * @author zhuangchongyi
 * @since 2020-08-29
 */
@RestController
@RequestMapping("/system/dept")
public class SysDeptController {
    @Autowired
    private ISysDeptService deptService;
    @Autowired
    private ISysUserService userService;

    @RequiresPermissions("system:dept:list")
    @GetMapping
    public R list(SysDept sysDept) {
        return R.success().data(deptService.list(sysDept));
    }

    @GetMapping("/{deptId}")
    public R get(@PathVariable Integer deptId) {
        QueryWrapper<SysDept> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(SysDept.class,
                info -> !info.getColumn().equals("create_time") && !info.getColumn().equals("create_by"))
                .eq("dept_id", deptId);
        return R.success().data(deptService.getOne(queryWrapper));
    }

    @RepeatSubmit
    @RequiresPermissions("system:dept:add")
    @PostMapping
    public R add(@RequestBody SysDept dept) {
        SysDept one = deptService.getOne(new QueryWrapper<SysDept>().select("dept_id")
                .eq("parent_id", dept.getParentId()).eq("dept_num", dept.getDeptNum()), false);
        if (null != one)
            throw new ServiceException(String.format("%s部门编码已使用", dept.getDeptNum()));

        setDepeAncestors(dept);
        return R.success().data(deptService.save(dept));
    }

    @RepeatSubmit
    @RequiresPermissions("system:dept:edit")
    @PutMapping
    public R update(@RequestBody SysDept dept) {
        SysDept res = deptService.getOne(new QueryWrapper<SysDept>().select("dept_id")
                .eq("parent_id", dept.getParentId()).eq("dept_num", dept.getDeptNum()), false);
        if (null != res && !res.getDeptId().equals(dept.getDeptId()))
            throw new ServiceException(String.format("%s部门编码已使用", dept.getDeptNum()));
        setDepeAncestors(dept);
        return R.success().data(deptService.updateById(dept));
    }

    private void setDepeAncestors(SysDept dept) {
        Integer parentId = dept.getParentId();
        SysDept info = deptService.getById(parentId);
        if (null != info) {
            if (CustomConstant.STOP_STATUS.equals(info.getStatus())) {
                throw new ServiceException(String.format("%s部门已停用，不允许修改", info.getDeptNum()));
            } else {
                dept.setAncestors(info.getAncestors() + "," + parentId);
            }
        }
    }

    @RepeatSubmit
    @RequiresPermissions("system:dept:delete")
    @DeleteMapping("/{deptId}")
    public R delete(@PathVariable Integer deptId) {
        //部门下面有子部门不能删除
        SysDept one = deptService.getOne(new QueryWrapper<SysDept>().select("dept_id").eq("parent_id", deptId), false);
        if (null != one) {
            throw new ServiceException("存在下级部门，不允许删除");
        }
        //部门被员工应用不能删除
        SysUser user = userService.getOne(new QueryWrapper<SysUser>().select("user_id").eq("dept_id", deptId), false);
        if (null != user) {
            throw new ServiceException("部门已有员工归属，不允许删除");
        }
        return R.success().data(deptService.removeById(deptId));
    }

    /**
     * 生成部门树
     *
     * @return
     */
    @GetMapping("/selectTree")

    public R selectTree() {
        return R.success().data(deptService.treeselect());
    }


}

