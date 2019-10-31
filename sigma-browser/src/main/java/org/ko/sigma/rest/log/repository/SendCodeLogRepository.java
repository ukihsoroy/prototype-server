package org.ko.sigma.rest.log.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.ko.sigma.data.entity.SendCodeLog;
import org.springframework.stereotype.Repository;
import org.ko.sigma.rest.log.dto.SendCodeLogDTO;
import org.ko.sigma.rest.log.condition.QuerySendCodeLogCondition;


import java.util.List;

@Repository
public interface SendCodeLogRepository extends BaseMapper<SendCodeLog> {

    List<SendCodeLogDTO> queryList(QuerySendCodeLogCondition condition);

    int insertList (List<SendCodeLog> sendCodeLogs);

}
