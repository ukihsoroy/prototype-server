package org.ko.sigma.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import java.io.Serializable;

/**
 * <p>菜单表，维度表</p>
 * @author K.O
 */
@Data
@TableName("t_menu")
public class Menu implements Serializable {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 菜单编码，由系统统一生成，不可修改
     */
    private String code;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单路由规则索引
     */
    private String path;

    /**
     * 命名视图组件
     */
    private String component;

    /**
     * 路由重定向
     */
    private String redirect;

    /**
     * 菜单元数据。 name: 路由名称，icon: 路由图标
     */
    private String meta;

    /**
     * 上级菜单编码，由系统统一生成，不可修改
     */
    private String parentCode;

    /**
     * 数据状态：0-删除，1-有效
     */
    private short enable;

    /**
     * 更新版本
     */
    private Long version;

    /**
     * 创建用户
     */
    private String createUser;

    /**
     * 创建时间
     */
    private java.util.Date gmtCreate;

    /**
     * 更新用户
     */
    private String modifiedUser;

    /**
     * 更新时间
     */
    private java.util.Date gmtModified;


}