package com.dc.project.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
//        return R.success().data(deptService.getById(deptId));
        return R.success().data(deptService.getOne(new QueryWrapper<SysDept>()
                .select("dept_id, dept_num, dept_name, parent_id, dept_type, leader, phone, email, status, address").eq("dept_id", deptId)));
    }

    @RequiresPermissions("system:dept:add")
    @PostMapping
    public R add(@RequestBody SysDept dept) {
        int count = deptService.count(new QueryWrapper<SysDept>().select("dept_id").eq("dept_num", dept.getDeptNum()));
        if (count > 0)
            throw new ServiceException("部门编码不允许重复");
        return R.success().data(deptService.save(dept));
    }

    @RequiresPermissions("system:dept:edit")
    @PutMapping
    public R update(@RequestBody SysDept dept) {
        SysDept res = deptService.getOne(new QueryWrapper<SysDept>().select("dept_id").eq("dept_num", dept.getDeptNum()));
        if (null != res && !res.getDeptId().equals(dept.getDeptId()))
            throw new ServiceException("部门编码不允许重复");
        return R.success().data(deptService.updateById(dept));
    }

    @RequiresPermissions("system:dept:delete")
    @DeleteMapping("/{deptId}")
    public R delete(@PathVariable Integer deptId) {
        //部门下面有子部门不能删除
        SysDept one = deptService.getOne(new QueryWrapper<SysDept>().select("dept_id").eq("parent_id", deptId));
        if (null != one) {
            throw new ServiceException("存在下级部门不允许删除");
        }
        //部门被员工应用不能删除
        SysUser user = userService.getOne(new QueryWrapper<SysUser>().select("user_id").eq("dept_id", deptId));
        if (null != user) {
            throw new ServiceException("部门已使用不允许删除");
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

