package com.dc.project.basis.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dc.project.basis.entity.SysMaterielFile;

import java.util.List;

/**
 * 产品文件表 Mapper 接口
 *
 * @author zhuangchongyi
 * @since 2020-09-05
 */
public interface SysMaterielFileDao extends BaseMapper<SysMaterielFile> {

    /**
     * 查询产品url
     *
     * @param id 产品id
     * @return
     */
    List<String> findMaterielUrl(Integer id);


}
