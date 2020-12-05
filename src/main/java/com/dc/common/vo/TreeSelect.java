package com.dc.common.vo;

import com.dc.project.basis.entity.SysCategory;
import com.dc.project.system.entity.SysDept;
import com.dc.project.system.entity.SysMenu;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author zhuangcy
 * @Description 树列表对象
 */
@Data
public class TreeSelect implements Serializable {
    private static final long serialVersionUID = -1464539134937459934L;
    /**
     * 节点ID
     */
    private Integer id;

    /**
     * 节点名称
     */
    private String label;

    /**
     * 图片url
     */
    private String url;

    public TreeSelect() {
    }

    /**
     * 子节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeSelect> children;

    public TreeSelect(SysDept dept) {
        this.id = dept.getDeptId();
        this.label = dept.getDeptName();
        this.children = dept.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    public TreeSelect(SysMenu menu) {
        this.id = menu.getMenuId();
        this.label = menu.getMenuName();
        this.children = menu.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    public TreeSelect(SysCategory category) {
        this.id = category.getCategoryId();
        this.label = category.getCategoryName();
        this.url = category.getUrl();
        this.children = category.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }
}
