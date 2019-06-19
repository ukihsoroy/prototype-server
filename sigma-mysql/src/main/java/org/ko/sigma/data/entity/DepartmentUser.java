package org.ko.sigma.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import java.io.Serializable;

/**
 * <p>部门用户关联表，事实表</p>
 * @author K.O
 */
@Data
@TableName("t_department_user")
public class DepartmentUser implements Serializable {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 部门编码，由系统统一生成，不可修改
     */
    private String departmentCode;

    /**
     * 用户编码（UUID），由系统统一生成，不可修改
     */
    private String userCode;

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