package com.dc.project.open.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.project.open.vo.ItemVo;

import java.util.List;

public interface IItemService {
    List<ItemVo> list(Page page);

    ItemVo detail(Integer id);

}
