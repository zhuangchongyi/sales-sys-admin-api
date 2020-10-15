package com.dc.project.warehouse.service;

import com.dc.project.warehouse.entity.SysScrap;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 报废单主表 服务类
 *
 * @author zhuangcy
 * @since 2020-09-25
 */
public interface ISysScrapService extends IService<SysScrap> {

    boolean addAndUpdate(Map formMap) throws Exception;

    boolean delete(Integer id);

    boolean submit(Integer[] ids, String status);

    boolean audit(SysScrap scrap);
}
