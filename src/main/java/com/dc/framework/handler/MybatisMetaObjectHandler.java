package com.dc.framework.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.dc.common.utils.UserSecurityUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MybatisMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Date date = new Date();
        String username = UserSecurityUtil.getNickname();
        Integer userId = UserSecurityUtil.getUserId();
        this.setFieldValByName("createTime", date, metaObject);
        this.setFieldValByName("createBy", username, metaObject);
        this.setFieldValByName("createId", userId, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("updateBy", UserSecurityUtil.getNickname(), metaObject);
    }
}
