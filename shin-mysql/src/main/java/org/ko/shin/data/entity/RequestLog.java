package org.ko.shin.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.ko.shin.data.bean.BasicEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>请求日志表</p>
 * @author K.O
 */
@Data
@TableName("t_request_log")
@EqualsAndHashCode(callSuper = true)
public class RequestLog extends BasicEntity {

    /**
     * 主键ID，使用UUID
     */
    @TableId(type = IdType.AUTO)
    private String id;

    /**
     * 请求用户ID
     */
    private String userId;

    /**
     * 用户ip信息
     */
    private String ipAddr;

    /**
     * 请求时间
     */
    private java.util.Date requestTime;

    /**
     * 请求url
     */
    private String requestLink;

    /**
     * 创建用户ID
     */
    private int createUserId;

    /**
     * 创建时间
     */
    private java.util.Date createDate;

    /**
     * 更新用户ID
     */
    private int updateUserId;

    /**
     * 更新时间
     */
    private java.util.Date updateDate;


}