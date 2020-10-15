package com.dc.project.basis.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.vo.R;
import com.dc.project.basis.entity.SysMaterielFile;
import com.dc.project.basis.service.ISysMaterielFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 产品文件表 前端控制器
 *
 * @author zhuangchongyi
 * @since 2020-09-05
 */
@RestController
@RequestMapping("/basis/materielFile")
public class SysMaterielFileController {
    @Autowired
    private ISysMaterielFileService materielFileService;

    @GetMapping
    public R list(Page page, SysMaterielFile materielFile) {
        QueryWrapper<SysMaterielFile> queryWrapper = new QueryWrapper<SysMaterielFile>()
                .eq("materiel_id", materielFile.getMaterielId()).orderByDesc("create_time");
        return R.success().data(materielFileService.page(page, queryWrapper));
    }

    @PutMapping
    public R update(@RequestBody SysMaterielFile materielFile) {
        UpdateWrapper<SysMaterielFile> queryWrapper = new UpdateWrapper<SysMaterielFile>()
                .set("remark", materielFile.getRemark())
                .eq("pk_id", materielFile.getPkId());
        return R.success().data(materielFileService.update(queryWrapper));
    }

    @DeleteMapping("/{pkId}")
    public R delete(@PathVariable Long pkId) {
        return R.success().data(materielFileService.delete(pkId));
    }

    /**
     * 上传图纸
     *
     * @return
     */
    @PostMapping("/upload/{materielId}")
    public R upload(@PathVariable Integer materielId, @RequestParam("file") MultipartFile file) {
        return R.success().data(materielFileService.upload(materielId, file));
    }

}

