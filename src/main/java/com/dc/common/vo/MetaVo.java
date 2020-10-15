package com.dc.common.vo;

/**
 * 路由显示信息
 *
 * @author ruoyi
 */
public class MetaVo {
    /**
     * 设置该路由在侧边栏和面包屑中展示的名字
     */
    private String title;

    /**
     * 设置该路由的图标，对应路径src/icons/svg
     */
    private String icon;

    /**
     * 设置该路由的图标，对应路径src/icons/svg
     */
    private String permission;

    public MetaVo() {
    }

    public MetaVo(String title, String icon, String permission) {
        this.title = title;
        this.icon = icon;
        this.permission = permission;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
