package org.ko.shin.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.ko.shin.data.bean.BasicEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>用户权限关联表，事实表</p>
 * @author K.O
 */
@Data
@TableName("t_user_role")
@EqualsAndHashCode(callSuper = true)
public class UserRole extends BasicEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户编码（UUID），由系统统一生成，不可修改
     */
    private Long userId;

    /**
     * 角色编码，由系统统一生成，不可修改
     */
    private String roleCode;


}