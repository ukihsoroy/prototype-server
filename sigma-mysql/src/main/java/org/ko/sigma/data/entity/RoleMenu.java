package org.ko.sigma.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.ko.sigma.data.bean.BasicEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>权限菜单关联表，事实表</p>
 * @author K.O
 */
@Data
@TableName("t_role_menu")
@EqualsAndHashCode(callSuper = true)
public class RoleMenu extends BasicEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色编码，由系统统一生成，不可修改
     */
    private String roleCode;

    /**
     * 菜单编码，由系统统一生成，不可修改
     */
    private Long menuId;

    /**
     * 创建数据权限：0-否，1-是
     */
    private short create;

    /**
     * 读取详情权限：0-否，1-是
     */
    private short read;

    /**
     * 更新数据权限：0-否，1-是
     */
    private short update;

    /**
     * 删除数据权限：0-否，1-是
     */
    private short delete;

    /**
     * 数据状态：0-有效，1-删除
     */
    private short disable;


}