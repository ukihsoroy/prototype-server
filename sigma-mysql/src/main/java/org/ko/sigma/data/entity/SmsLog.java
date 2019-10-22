package org.ko.sigma.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.ko.sigma.data.bean.BasicEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>短信验证码日志表</p>
 * @author K.O
 */
@Data
@TableName("t_sms_log")
@EqualsAndHashCode(callSuper = true)
public class SmsLog extends BasicEntity {

    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long userId;

    /**
     * 
     */
    private short type;

    /**
     * 
     */
    private String info;

    /**
     * 
     */
    private String smsCode;


}