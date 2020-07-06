package org.ko.shin.rest.log.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ko.shin.core.authentication.mobile.ISmsCodeService;
import org.ko.shin.core.exception.BusinessException;
import org.ko.shin.core.constant.SystemCode;
import org.ko.shin.data.entity.SendCodeLog;
import org.ko.shin.rest.log.condition.QuerySendCodeLogCondition;
import org.ko.shin.rest.log.repository.SendCodeLogRepository;
import org.ko.shin.rest.log.service.SendCodeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class SendCodeLogServiceImpl extends ServiceImpl<SendCodeLogRepository, SendCodeLog>
        implements SendCodeLogService, ISmsCodeService {

    /**
     * 验证码日志数据库对象
     */
    @Autowired private SendCodeLogRepository sendCodeLogRepository;

    @Override
    public List<SendCodeLog> querySendCodeLogList(QuerySendCodeLogCondition condition) {
        return sendCodeLogRepository.selectList(new QueryWrapper<>());
    }

    @Override
    public SendCodeLog querySendCodeLogInfo(Long id) {
        return sendCodeLogRepository.selectById(id);
    }

    @Override
    public Long createSendCodeLog(SendCodeLog sendCodeLog) {
        if (sendCodeLogRepository.insert(sendCodeLog) == 0) {
            throw new BusinessException(SystemCode.UNKNOWN_ERROR);
        }
        return sendCodeLog.getId();
    }

    @Override
    public SendCodeLog updateSendCodeLog(Long id, SendCodeLog sendCodeLog) {
        sendCodeLog.setId(id);
        if (sendCodeLogRepository.updateById(sendCodeLog) == 0) {
            throw new BusinessException(SystemCode.UNKNOWN_ERROR);
        }
        return sendCodeLog;
    }

    @Override
    public Long deleteSendCodeLog(Long id) {
        if (sendCodeLogRepository.deleteById(id) == 0) {
            throw new BusinessException(SystemCode.UNKNOWN_ERROR);
        }
        return id;
    }

    @Override
    public String findCodeByType(String sendType, String messageType, String address) {
        return sendCodeLogRepository.findCodeByType(sendType, messageType, address);
    }


    @Override
    public String findSmsCode(String mobile) {
        return findCodeByType("sms", "login", mobile);
    }
}