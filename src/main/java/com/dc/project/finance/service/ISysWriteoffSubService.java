package com.dc.project.finance.service;

import com.dc.project.finance.entity.SysWriteoffSub;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 应收核销明细表 服务类
 *
 * @author zhuangcy
 * @since 2020-10-26
 */
public interface ISysWriteoffSubService extends IService<SysWriteoffSub> {

    List<SysWriteoffSub> findReceiptPrice(Integer writeoffId);

    List<SysWriteoffSub> findReceivablePrice(Integer writeoffId);
}
