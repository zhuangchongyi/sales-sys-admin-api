package com.dc.project.open.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dc.common.lang.annotation.RepeatSubmit;
import com.dc.common.vo.OpenUser;
import com.dc.common.vo.R;
import com.dc.project.basis.service.ISysClienteleService;
import com.dc.project.open.entity.ClienteleAddress;
import com.dc.project.open.service.IClienteleAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhuangcy
 * @date 2020/11/16 9:36
 * @description 客户请求控制层
 */
@Api(tags = "客户信息请求接口")
@RestController
@RequestMapping("/open/user")
@Validated
public class UserController {
    @Autowired
    private ISysClienteleService clienteleService;
    @Autowired
    private IClienteleAddressService addressService;

    @ApiOperation(value = "查询客户信息")
    @GetMapping
    public R getClientele() {
        return R.success().data(clienteleService.getClientele());
    }

    @ApiOperation(value = "查询客户地址列表")
    @GetMapping("/address")
    public R listAddress() {
        OpenUser clientele = (OpenUser) SecurityUtils.getSubject().getPrincipal();
        QueryWrapper<ClienteleAddress> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(ClienteleAddress::getClienteleId, clientele.getClientele().getClienteleId());
        return R.success().data(addressService.list(wrapper));
    }

    @ApiOperation(value = "添加客户地址")
    @RepeatSubmit
    @PostMapping("/address")
    public R addAddress(@RequestBody ClienteleAddress address) {
        return R.success().data(addressService.save(address));
    }

    @ApiOperation(value = "修改客户地址")
    @RepeatSubmit
    @PutMapping("/address")
    public R editAddress(@RequestBody ClienteleAddress address) {
        return R.success().data(addressService.update(address));
    }


}
