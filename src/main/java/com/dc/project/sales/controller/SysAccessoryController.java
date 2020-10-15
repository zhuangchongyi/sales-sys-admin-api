package com.dc.project.sales.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.vo.R;
import com.dc.project.basis.entity.SysMaterielFile;
import com.dc.project.sales.entity.SysAccessory;
import com.dc.project.sales.service.ISysAccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 附件表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-09-17
 */
@RestController
@RequestMapping("/sales/accessory")
public class SysAccessoryController {
    @Autowired
    private ISysAccessoryService accessoryService;

    @GetMapping
    public R list(Page page, SysAccessory accessory) {
        QueryWrapper<SysAccessory> queryWrapper = new QueryWrapper<SysAccessory>()
                .eq("sub_id", accessory.getSubId()).orderByDesc("create_time");
        return R.success().data(accessoryService.page(page, queryWrapper));
    }

    @PutMapping
    public R update(@RequestBody SysMaterielFile materielFile) {
        UpdateWrapper<SysAccessory> queryWrapper = new UpdateWrapper<SysAccessory>()
                .set("remark", materielFile.getRemark())
                .eq("pk_id", materielFile.getPkId());
        return R.success().data(accessoryService.update(queryWrapper));
    }

    @DeleteMapping("/{pkId}")
    public R delete(@PathVariable Long pkId) {
        return R.success().data(accessoryService.delete(pkId));
    }

    /**
     * 上传图纸
     *
     * @return
     */
    @PostMapping("/upload/{id}")
    public R upload(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        return R.success().data(accessoryService.upload(id, file));
    }
}

