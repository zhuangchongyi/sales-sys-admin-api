package com.dc.project.finance.dao;

import com.dc.project.finance.entity.SysReceivable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.math.BigDecimal;
import java.util.List;

/**
 * 财务应收款主表 Mapper 接口
 *
 * @author zhuangcy
 * @since 2020-10-21
 */
public interface SysReceivableDao extends BaseMapper<SysReceivable> {

    BigDecimal getReceivePrice(SysReceivable receivable);

    List<SysReceivable> getClienteleReceivable(SysReceivable receivable);

    List<SysReceivable> getClienteleReceivableList(SysReceivable receivable);
}
