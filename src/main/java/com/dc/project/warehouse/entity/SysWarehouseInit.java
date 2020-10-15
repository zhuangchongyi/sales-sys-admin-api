package com.dc.project.warehouse.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.dc.common.vo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 仓库初始化主表
 *
 * @author zhuangcy
 * @since 2020-09-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysWarehouseInit extends BaseEntity {

    /**
     * 仓库id
     */
    @TableId(value = "warehouse_id")
    private Integer warehouseId;

    /**
     * 仓库编码
     */
    private String warehouseNum;

    /**
     * 仓库名称
     */
    private String warehouseName;


}
