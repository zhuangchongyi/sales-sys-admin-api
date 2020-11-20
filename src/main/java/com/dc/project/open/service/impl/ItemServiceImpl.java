package com.dc.project.open.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.project.open.vo.ItemVo;
import com.dc.project.basis.service.ISysMaterielService;
import com.dc.project.open.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements IItemService {
    @Autowired
    private ISysMaterielService materielService;


    @Override
    public List<ItemVo> list(Page page) {
        return materielService.listItem(page);
    }

    @Override
    public ItemVo detail(Integer id) {
        return materielService.detailItem(id);
    }
}
