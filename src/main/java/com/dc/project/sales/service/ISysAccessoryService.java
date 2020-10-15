package com.dc.project.sales.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dc.project.sales.entity.SysAccessory;
import org.springframework.web.multipart.MultipartFile;

/**
 * 附件表 服务类
 *
 * @author zhuangcy
 * @since 2020-09-17
 */
public interface ISysAccessoryService extends IService<SysAccessory> {

    boolean upload(Long id, MultipartFile file);

    boolean delete(Long pkId);

}
