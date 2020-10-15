package com.dc.project.basis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dc.project.basis.entity.SysMaterielFile;
import org.springframework.web.multipart.MultipartFile;

/**
 * 产品文件表 服务类
 *
 * @author zhuangchongyi
 * @since 2020-09-05
 */
public interface ISysMaterielFileService extends IService<SysMaterielFile> {

    boolean upload(Integer materielId, MultipartFile file);

    boolean delete(Long pkId);

}
