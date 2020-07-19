package org.ko.prototype.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.ko.prototype.data.bean.BasicEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>字典表</p>
 * @author K.O
 */
@Data
@TableName("t_dict")
@EqualsAndHashCode(callSuper = true)
public class Dict extends BasicEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 字典编码
     */
    private String code;

    /**
     * 字典名称
     */
    private String value;

    /**
     * 字典类型
     */
    private String type;

    /**
     * 描述
     */
    private String description;

}