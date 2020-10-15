package com.dc.framework.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.dc.common.utils.UserSecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MybatisMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Date date = new Date();
        String username = UserSecurityUtils.getUsername();
        this.setFieldValByName("createTime", date, metaObject);
        this.setFieldValByName("createBy", username, metaObject);
        this.setFieldValByName("updateTime", date, metaObject);
        this.setFieldValByName("updateBy", username, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("updateBy", UserSecurityUtils.getUsername(), metaObject);
    }
}
