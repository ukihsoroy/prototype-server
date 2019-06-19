package org.ko.sigma.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import java.io.Serializable;

/**
 * <p>请求日志表</p>
 * @author K.O
 */
@Data
@TableName("t_request_log")
public class RequestLog implements Serializable {

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
     * 删除状态：0-删除 1-有效
     */
    private short availableStatus;

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