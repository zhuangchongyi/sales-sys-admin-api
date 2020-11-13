package com.dc.framework.aspectj;


import com.dc.common.lang.annotation.DataScope;
import com.dc.common.constant.CustomConstant;
import com.dc.common.utils.UserSecurityUtil;
import com.dc.common.vo.BaseEntity;
import com.dc.project.system.entity.SysRole;
import com.dc.project.system.entity.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author zhuangcy
 * @date 2020/10/31 12:10
 * @description 数据权限过滤处理类
 */
@Aspect
@Component
public class DataScopeAspectj {

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.dc.common.lang.annotation.DataScope)")
    public void dataSourcePointcut() {
    }

    /**
     * 在执行之前配置
     *
     * @param point
     * @throws Exception
     */
    @Before("dataSourcePointcut()")
    public void doBefore(JoinPoint point) {
        DataScope dataScope = getAnnotation(point);
        if (null == dataScope) {
            return;
        }
        SysUser user = UserSecurityUtil.getUser();
        if (!UserSecurityUtil.isAdmin(user.getUserId())) {
            dataScopeFilter(point, user, dataScope);
        }
    }

    private void dataScopeFilter(JoinPoint point, SysUser user, DataScope dataScopeAnn) {
        StringBuilder sqlString = new StringBuilder();
        String userAlias = dataScopeAnn.userAlias();
        String userColumn = dataScopeAnn.userColumn();
        for (SysRole role : user.getRoles()) {
            String dataScope = role.getDataScope();
            if (CustomConstant.DATA_SCOPE_ALL.equals(dataScope)) {// 全部数据权限
                sqlString = new StringBuilder();
                break;
            } else if (CustomConstant.DATA_SCOPE_CUSTOM.equals(dataScope)) {// 自定义数据权限
                if (StringUtils.isEmpty(userAlias)) {
                    sqlString.append(String.format(
                            " or %s in ( select user_id from sys_user du join sys_role_dept drd on du.dept_id=drd.dept_id and role_id = %s ) ",
                            userColumn, role.getRoleId()));
                } else {
                    sqlString.append(String.format(
                            " or %s.%s in ( select user_id from sys_user du join sys_role_dept drd on du.dept_id=drd.dept_id and role_id = %s ) ",
                            userAlias, userColumn, role.getRoleId()));
                }
            } else if (CustomConstant.DATA_SCOPE_DEPT.equals(dataScope)) {// 本部门数据权限
                if (StringUtils.isEmpty(userAlias)) {
                    sqlString.append(String.format(
                            " or %s in ( select user_id from sys_user du join sys_dept dd on du.dept_id=dd.dept_id and dd.dept_id = %s ) ",
                            userColumn, user.getDeptId()));
                } else {
                    sqlString.append(String.format(
                            " or %s.%s in ( select user_id from sys_user du join sys_dept dd on du.dept_id=dd.dept_id and dd.dept_id = %s ) ",
                            userAlias, userColumn, user.getDeptId()));
                }
            } else if (CustomConstant.DATA_SCOPE_DEPT_AND_CHILD.equals(dataScope)) {// 部门及部门以下数据权限
                if (StringUtils.isEmpty(userAlias)) {
                    sqlString.append(String.format(
                            " or %s in ( select user_id from sys_user du join sys_dept dd on dd.dept_id=du.dept_id and (dd.dept_id = %s or find_in_set(%s , ancestors )))",
                            userColumn, user.getDeptId(), user.getDeptId()));
                } else {
                    sqlString.append(String.format(
                            " or %s.%s in ( select user_id from sys_user du join sys_dept dd on dd.dept_id=du.dept_id and (dd.dept_id = %s or find_in_set(%s , ancestors )))",
                            userAlias, userColumn, user.getDeptId(), user.getDeptId()));
                }
            }
        }
        //仅本人数据权限
        if (StringUtils.isEmpty(userAlias)) {
            sqlString.append(String.format(" or %s = %s ", userColumn, user.getUserId()));
        } else {
            sqlString.append(String.format(" or %s.%s = %s ", userAlias, userColumn, user.getUserId()));
        }

        verifyParams(point, sqlString.toString());
    }

    private void verifyParams(JoinPoint point, String sql) {
        Object[] args = point.getArgs();
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (arg instanceof BaseEntity) {
                BaseEntity baseEntity = (BaseEntity) arg;
                if (StringUtils.isNotBlank(sql)) {
                    baseEntity.getParams().put(CustomConstant.DATA_SCOPE, " and (" + sql.substring(4) + ")");
                } else {
                    baseEntity.getParams().put(CustomConstant.DATA_SCOPE, "and 1=1");
                }
                break;
            }
        }
    }

    /**
     * 获取注解
     *
     * @param point
     * @return
     */
    private DataScope getAnnotation(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        if (null != method) {
            return method.getAnnotation(DataScope.class);
        }
        return null;
    }

}
