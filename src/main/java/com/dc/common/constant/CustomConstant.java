package com.dc.common.constant;

/**
 * @Author zhuangcy
 * @Description 自定义常量
 * @Date 2020/10/12 12:20
 */
public class CustomConstant {
    /**
     * 密码加密次数
     */
    public static final int ENCRYPTION_NUM = 3;
    /**
     * 用户默认密码
     */
    public static final String DEFAULT_PASSWORD = "123456";
    /**
     * 否状态
     */
    public static final String NO_STATUS = "0";
    /**
     * 是状态
     */
    public static final String YES_STATUS = "1";
    /**
     * 启用状态
     */
    public static final String START_STATUS = "0";
    /**
     * 禁用状态
     */
    public static final String STOP_STATUS = "1";

    /**
     * 普通用户类型
     */
    public static final String ORDINARY_USER_TYPE = "0";
    /**
     * 系统用户类型
     */
    public static final String ADMIN_USER_TYPE = "1";


    /************************ 前端路由属性 *****************************/
    /**
     * 隐藏菜单
     */
    public static final String HIDDEN = "1";
    /**
     * 是否菜单外链（否）
     */
    public static final String YES_FRAME = "0";
    /**
     * 是否菜单外链（是）
     */
    public static final String NO_FRAME = "1";
    /**
     * 菜单类型（目录）
     */
    public static final String TYPE_DIR = "M";
    /**
     * 菜单类型（菜单）
     */
    public static final String TYPE_MENU = "C";
    /**
     * 菜单类型（按钮）
     */
    public static final String TYPE_BUTTON = "F";
    /**
     * Layout组件标识
     */
    public final static String LAYOUT = "Layout";


    /**
     * 验证码缓存 Key值
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_code_key:";
    /**
     * 验证码缓存名称
     */
    public static final String CAPTCHA_CACHE_NAME = "captchaCache";

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
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";
}
