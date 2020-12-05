package com.dc.common.vo;

import com.dc.project.basis.entity.SysClientele;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author zhuangcy
 * @date 2020/11/27
 * @description 开放用户登录实体
 */
@Data
@ApiModel(value = "登录实体对象")
public class OpenUser implements Serializable {
    private static final long serialVersionUID = 5450168174846105037L;
    @NotNull(message = "用户名不能为空")
    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("验证码")
    private String code;

    @JsonIgnore
    private SysClientele clientele;

}
