package org.ko.framework.core.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 前台属性结构数据
 * modify by yangxingyi
 */
@Getter
@Setter
@ToString
public class BaseTreeNodeData implements Serializable{

    /**
     * 显示名
     */
    private String label;

    /**
     * 数据
     */
    private String data;

    /**
     * 展开图标样式
     */
    private String expandedIcon;

    /**
     * 折叠图标样式
     */
    private String collapsedIcon;

    /**
     * 默认图标样式
     */
    private String icon;

    /**
     * 子节点
     */
    private List<BaseTreeNodeData> children;
}
