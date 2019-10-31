package org.ko.sigma.rest.log.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ko.sigma.core.exception.TransactionalException;
import org.ko.sigma.core.type.SystemCode;
import org.ko.sigma.data.entity.SendCodeLog;
import org.ko.sigma.rest.log.condition.QuerySendCodeLogCondition;
import org.ko.sigma.rest.log.repository.SendCodeLogRepository;
import org.ko.sigma.rest.log.service.SendCodeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class SendCodeLogServiceImpl extends ServiceImpl<SendCodeLogRepository, SendCodeLog> implements SendCodeLogService {

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
            throw new TransactionalException(SystemCode.INSERT_ERROR);
        }
        return sendCodeLog.getId();
    }

    @Override
    public SendCodeLog updateSendCodeLog(Long id, SendCodeLog sendCodeLog) {
        sendCodeLog.setId(id);
        if (sendCodeLogRepository.updateById(sendCodeLog) == 0) {
            throw new TransactionalException(SystemCode.UPDATE_ERROR);
        }
        return sendCodeLog;
    }

    @Override
    public Long deleteSendCodeLog(Long id) {
        if (sendCodeLogRepository.deleteById(id) == 0) {
            throw new TransactionalException(SystemCode.DELETE_ERROR);
        }
        return id;
    }


}