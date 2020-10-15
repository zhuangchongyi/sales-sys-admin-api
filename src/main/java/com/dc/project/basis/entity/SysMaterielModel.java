package com.dc.project.basis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 产品型号关联表
 *
 * @author zhuangchongyi
 * @since 2020-09-03
 */
@Data
@Accessors(chain = true)
public class SysMaterielModel {

    /**
     * 主键
     */
    @TableId
    private Long pkId;
    /**
     * 产品id
     */
    private Integer mId;


    /**
     * 型号
     */
    private String modelName;
}
