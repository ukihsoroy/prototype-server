package org.ko.sigma.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.ko.sigma.data.bean.BasicEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>部门表</p>
 * @author K.O
 */
@Data
@TableName("t_department")
@EqualsAndHashCode(callSuper = true)
public class Department extends BasicEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 部门编码，由系统统一生成，不可修改
     */
    private String code;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 父级部门编码
     */
    private String parentCode;

}