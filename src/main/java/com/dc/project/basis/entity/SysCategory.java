package com.dc.project.basis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dc.common.vo.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义类别表
 *
 * @author zhuangchongyi
 * @since 2020-09-03
 */
@Data
@Accessors(chain = true)
public class SysCategory extends BaseEntity {

    /**
     * 类别id
     */
    @TableId(value = "category_id", type = IdType.AUTO)
    private Integer categoryId;

    /**
     * 类别编码
     */
    @NotNull(message = "编码不能为空")
    @Length(max = 10, message = "长度不能超过10位")
    private String categoryNum;

    /**
     * 类别名称
     */
    @NotNull(message = "名称不能为空")
    private String categoryName;

    /**
     * 父级类别id
     */
    private Integer parentId;

    /**
     * 类型（0产品类别 1客户类别）
     */
    @NotNull(message = "类别不能为空")
    private String category;

    /**
     * 状态（0正常 1停用）
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

    /**
     * 子分类
     */
    @TableField(exist = false)
    private List<SysCategory> children = new ArrayList<>();
}
