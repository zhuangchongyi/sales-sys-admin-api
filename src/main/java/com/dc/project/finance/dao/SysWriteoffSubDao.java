package com.dc.project.finance.dao;

import com.dc.project.finance.entity.SysWriteoffSub;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 应收核销明细表 Mapper 接口
 *
 * @author zhuangcy
 * @since 2020-10-26
 */
public interface SysWriteoffSubDao extends BaseMapper<SysWriteoffSub> {

    List<SysWriteoffSub> findReceiptPrice(Integer writeoffId);

    List<SysWriteoffSub> findReceivablePrice(Integer writeoffId);
}
