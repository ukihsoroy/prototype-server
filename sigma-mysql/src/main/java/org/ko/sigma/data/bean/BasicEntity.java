package org.ko.sigma.data.bean;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;

@Data
public class BasicEntity implements Serializable {

    /**
     * 数据状态：0-删除，1-有效
     */
    @TableLogic
    private short enable;

    /**
     * 更新版本
     */
    private Long version;

    /**
     * 创建用户
     */
    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private java.util.Date gmtCreate;

    /**
     * 更新用户
     */
    @TableField(fill = FieldFill.UPDATE)
    private String modifiedUser;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private java.util.Date gmtModified;


}
