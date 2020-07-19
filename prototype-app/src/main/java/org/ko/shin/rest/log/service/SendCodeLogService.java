package org.ko.prototype.rest.log.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.ko.prototype.data.entity.SendCodeLog;
import org.ko.prototype.rest.log.condition.QuerySendCodeLogCondition;

import java.util.List;

/**
 * 验证码日志
 */
public interface SendCodeLogService extends IService<SendCodeLog> {

    /**
     * <p>查询验证码日志列表</p>
     * @param condition
     * @return
     */
    List<SendCodeLog> querySendCodeLogList(QuerySendCodeLogCondition condition);

    /**
     * <p>通过主键查询验证码日志详细信息</p>
     * @param id
     * @return
     */
    SendCodeLog querySendCodeLogInfo(Long id);

    /**
     * <p>新增验证码日志一条数据</p>
     * @param sendCodeLog
     */
    Long createSendCodeLog(SendCodeLog sendCodeLog);

    /**
     * <p>通过ID更新验证码日志</p>
     * @param id SendCodeLog Id
     * @param sendCodeLog
     * @return
     */
    SendCodeLog updateSendCodeLog(Long id, SendCodeLog sendCodeLog);

    /**
     * <p>删除验证码日志数据</p>
     * @param id SendCodeLog 主键id
     * @return
     */
    Long deleteSendCodeLog(Long id);

    /**
     * 通过发送类型，消息类型，接收方 获取验证码
     * @param sendType
     * @param messageType
     * @param address
     * @return
     */
    String findCodeByType(
            @Param("sendType") String sendType,
            @Param("messageType") String messageType,
            @Param("address") String address);

}