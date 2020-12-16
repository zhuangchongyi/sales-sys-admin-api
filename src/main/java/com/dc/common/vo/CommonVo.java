package com.dc.common.vo;

import com.dc.common.valid.AuditGroup;
import com.dc.common.valid.InsertGroup;
import com.dc.common.valid.SubmitGroup;
import com.dc.common.valid.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author zhuangcy
 * @date 2020/12/3
 * @description 采购订单vo
 */
@Data
public class CommonVo<H, B> {
    /**
     * 主体信息
     */
    @NotNull(message = "主体信息不能为空", groups = {InsertGroup.class, UpdateGroup.class})
    private H header;

    /**
     * 子表信息
     */
    @NotEmpty(message = "子表信息不能为空", groups = {InsertGroup.class, UpdateGroup.class})
    private List<B> bodys;

    /**
     * 删除的子表id
     */
    private List<Long> delSubIds;

    /**
     * 提交id
     */
    @NotEmpty(message = "id不能为空", groups = {SubmitGroup.class})
    private List<Integer> ids;

    /**
     * 状态
     */
    @NotNull(message = "状态不能为空", groups = {SubmitGroup.class, AuditGroup.class})
    private String status;

    /**
     * 单据id
     */
    @NotNull(message = "id不能为空", groups = {AuditGroup.class})
    private Integer id;
}
