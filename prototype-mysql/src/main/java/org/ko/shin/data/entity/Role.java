package org.ko.prototype.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.ko.prototype.data.bean.BasicEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>系统角色表，维度表</p>
 * @author K.O
 */
@Data
@TableName("t_role")
@EqualsAndHashCode(callSuper = true)
public class Role extends BasicEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色编码，由系统统一生成，不可修改
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;


}