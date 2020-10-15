package com.dc.project.sales.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 附件表
 *
 * @author zhuangcy
 * @since 2020-09-17
 */
@Data
@Accessors(chain = true)
public class SysAccessory {

    /**
     * 主键
     */
    @TableId(value = "pk_id", type = IdType.AUTO)
    private Long pkId;

    /**
     * 来源编号
     */
    private Integer materielId;

    /**
     * 目的编号
     */
    private Long subId;

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
