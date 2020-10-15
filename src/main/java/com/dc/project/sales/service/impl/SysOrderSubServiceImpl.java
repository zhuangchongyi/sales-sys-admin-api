package com.dc.project.sales.service.impl;

import com.dc.project.sales.entity.SysOrderSub;
import com.dc.project.sales.dao.SysOrderSubDao;
import com.dc.project.sales.service.ISysOrderSubService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 销售订单子表 服务实现类
 *
 * @author zhuangcy
 * @since 2020-09-17
 */
@Service
public class SysOrderSubServiceImpl extends ServiceImpl<SysOrderSubDao, SysOrderSub> implements ISysOrderSubService {

    @Override
    public boolean delete(Long subId) {
        boolean remove = this.removeById(subId);
        //ftp删除文件
        return remove;
    }
}
