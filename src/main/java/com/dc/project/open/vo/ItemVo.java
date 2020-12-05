package com.dc.project.open.vo;

import com.dc.project.basis.entity.SysMaterielModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author zhuangcy
 * @date 2020/11/27
 * @description 产品信息
 */
@ApiModel(value = "商品信息")
@Data
public class ItemVo implements Serializable {
    private static final long serialVersionUID = 81325056113375072L;
    @ApiModelProperty(value = "产品id")
    private Integer itemId;
    @ApiModelProperty(value = "产品名称")
    private String itemName;
    @ApiModelProperty(value = "产品规格")
    private String specification;
    @ApiModelProperty(value = "产品价格")
    private BigDecimal price;
    private BigDecimal maxPrice;
    private BigDecimal minPrice;
    @ApiModelProperty(value = "产品型号")
    private List<SysMaterielModel> modelNames;
    private String url;
    private Integer categoryId;
    @ApiModelProperty(value = "产品类别")
    private String categoryName;
    private List<String> urls;
}
