package com.dc.project.basis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dc.common.vo.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 基本单位表
 *
 * @author zhuangchongyi
 * @since 2020-09-03
 */
@Data
@Accessors(chain = true)
public class SysUnits extends BaseEntity {

    /**
     * 单位编号
     */
    @TableId(value = "units_id", type = IdType.AUTO)
    private Integer unitsId;

    /**
     * 单位编码
     */
    @NotNull(message = "编码不能为空")
    @Length(max = 10, message = "长度不能超过10位")
    private String unitsNum;

    /**
     * 单位名称
     */
    @NotNull(message = "名称不能为空")
    private String unitsName;

    /**
     * 单位类别（0基本单位，1包装单位）
     */
    @NotNull(message = "类别不能为空")
    private String unitsType;

    /**
     * 状态
     */
    private String status;

    /**
     * 逻辑删除
     */
    @JsonIgnore
    private String delFlag;

    /**
     * 备注
     */
    private String remark;
}
