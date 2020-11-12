package com.dc.project.finance.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dc.common.lang.annotation.DataScope;
import com.dc.project.finance.entity.SysReceipt;

import java.math.BigDecimal;
import java.util.List;

/**
 * 财务收款表 服务类
 *
 * @author zhuangcy
 * @since 2020-10-23
 */
public interface ISysReceiptService extends IService<SysReceipt> {

    @DataScope(userColumn = "create_id")
    IPage<SysReceipt> page(Page<SysReceipt> page, SysReceipt receipt);

    boolean submit(Integer[] ids, String status);

    boolean audit(SysReceipt receipt);

    boolean delete(Integer id);

    BigDecimal findReceiptPriceByClienteleId(Integer clienteleId);

    List<SysReceipt> getClienteleReceipt(SysReceipt receipt);

    List<SysReceipt> getClienteleReceiptList(SysReceipt receipt);
}
