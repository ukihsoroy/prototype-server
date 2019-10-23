package org.ko.sigma.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.ko.sigma.data.bean.BasicEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>字典表，如果条件允许，可以放一部分进入缓存</p>
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
     * 编码
     */
    private String code;

    /**
     * 字典值
     */
    private String value;

    /**
     * 类型
     */
    private short type;

    /**
     * 描述
     */
    private String desc;

    /**
     * 数据状态：0-有效，1-删除
     */
    private short disable;


}