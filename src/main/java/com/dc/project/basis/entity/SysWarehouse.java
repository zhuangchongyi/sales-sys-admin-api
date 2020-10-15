package com.dc.project.basis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dc.common.vo.BaseEntity;
import com.dc.project.system.entity.SysDept;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 仓库表
 *
 * @author zhuangchongyi
 * @since 2020-09-03
 */
@Data
@Accessors(chain = true)
public class SysWarehouse extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 仓库序号
     */
    @TableId(value = "warehouse_id", type = IdType.AUTO)
    private Integer warehouseId;

    /**
     * 仓库编码
     */
    @NotNull(message = "仓库编码不能为空")
    @Length(max = 10, message = "长度不能超过10位")
    private String warehouseNum;

    /**
     * 仓库名称
     */
    @NotNull(message = "仓库名称不能为空")
    private String warehouseName;

    /**
     * 部门id
     */
    private Integer deptId;

    /**
     * 仓库状态（0正常 1停用）
     */
    private String status;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @JsonIgnore
    private String delFlag;

    /**
     * 备注
     */
    private String remark;

    @TableField(exist = false)
    private SysDept dept;
}
