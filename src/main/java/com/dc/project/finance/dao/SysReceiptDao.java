package com.dc.project.finance.dao;

import com.dc.project.finance.entity.SysReceipt;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.math.BigDecimal;
import java.util.List;

/**
 * 财务收款表 Mapper 接口
 *
 * @author zhuangcy
 * @since 2020-10-23
 */
public interface SysReceiptDao extends BaseMapper<SysReceipt> {

    BigDecimal getReceiptPrice(SysReceipt receipt);

    List<SysReceipt> getClienteleReceipt(SysReceipt receipt);

    List<SysReceipt> getClienteleReceiptList(SysReceipt receipt);
}
