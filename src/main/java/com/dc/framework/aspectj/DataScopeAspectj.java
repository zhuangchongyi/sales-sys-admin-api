package com.dc.framework.aspectj;

import com.dc.common.utils.UserSecurityUtils;
import com.dc.common.vo.BaseEntity;
import com.dc.framework.annotation.DataScope;
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
     * 全部数据权限
     */
    public static final String DATA_SCOPE_ALL = "1";

    /**
     * 自定数据权限
     */
    public static final String DATA_SCOPE_CUSTOM = "2";

    /**
     * 部门数据权限
     */
    public static final String DATA_SCOPE_DEPT = "3";

    /**
     * 部门及以下数据权限
     */
    public static final String DATA_SCOPE_DEPT_AND_CHILD = "4";

    /**
     * 仅本人数据权限
     */
    public static final String DATA_SCOPE_SELF = "5";

    /**
     * 数据权限过滤关键字
     */
    public static final String DATA_SCOPE = "dataScope";

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.dc.framework.annotation.DataScope)")
    public void dataSourcePointcut() {
    }

    /**
     * 在执行之前配置
     *
     * @param point
     * @throws Exception
     */
    @Before("dataSourcePointcut()")
    public void doBefore(JoinPoint point) throws Exception {
        DataScope dataScope = getAnnotation(point);
        if (null == dataScope){
            return;
        }
        SysUser user = UserSecurityUtils.getUser();
        if (UserSecurityUtils.isAdmin(user.getUserId())){
            dataScopeFilter(point,user,dataScope);
        }
    }

    private void dataScopeFilter(JoinPoint point, SysUser user, DataScope dataScopeAnn) {
        StringBuilder sqlString = new StringBuilder();

        for (SysRole role : user.getRoles()) {
            String dataScope = role.getDataScope();
            if (DATA_SCOPE_ALL.equals(dataScope)) {
                sqlString = new StringBuilder();
                break;
            } else if (DATA_SCOPE_CUSTOM.equals(dataScope)) {
                sqlString.append(String.format(
                        " OR {}.dept_id IN ( SELECT dept_id FROM sys_role_dept WHERE role_id = {} ) ", dataScopeAnn.deptAlias(),
                        role.getRoleId()));
            } else if (DATA_SCOPE_DEPT.equals(dataScope)) {
                sqlString.append(String.format(" OR {}.dept_id = {} ", dataScopeAnn.deptAlias(), user.getDeptId()));
            } else if (DATA_SCOPE_DEPT_AND_CHILD.equals(dataScope)) {
                sqlString.append(String.format(
                        " OR {}.dept_id IN ( SELECT dept_id FROM sys_dept WHERE dept_id = {} or find_in_set( {} , ancestors ) )",
                        dataScopeAnn.deptAlias(), user.getDeptId(), user.getDeptId()));
            } else if (DATA_SCOPE_SELF.equals(dataScope)) {
                if (StringUtils.isNotBlank(dataScopeAnn.userAlias())) {
                    sqlString.append(String.format(" OR {}.user_id = {} ", dataScopeAnn.userAlias(), user.getUserId()));
                } else {
                    // 数据权限为仅本人且没有userAlias别名不查询任何数据
                    sqlString.append(" OR 1=0 ");
                }
            }
        }

        if (StringUtils.isNotBlank(sqlString.toString())) {
            BaseEntity baseEntity = (BaseEntity) point.getArgs()[0];
            baseEntity.getParams().put(DATA_SCOPE, " AND (" + sqlString.substring(4) + ")");
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
