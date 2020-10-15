package com.dc.project.basis.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 产品文件表
 *
 * @author zhuangchongyi
 * @since 2020-09-05
 */
@Data
@Accessors(chain = true)
public class SysMaterielFile implements Serializable {
    private static final long serialVersionUID = -5714248233639065126L;
    /**
     * 主键
     */
    @TableId(value = "pk_id", type = IdType.AUTO)
    private Long pkId;

    /**
     * 产品编号
     */
    private Integer materielId;

    /**
     * 存储文件名
     */
    private String fileName;

    /**
     * 展示文件名
     */
    private String name;

    /**
     * 图片url
     */
    private String url;

    /**
     * 图片路径
     */
    private String path;

    /**
     * 标注
     */
    private String remark;
    /**
     * 创建者
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
