package org.ko.shin.rest.log.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.ko.shin.data.entity.SendCodeLog;
import org.springframework.stereotype.Repository;
import org.ko.shin.rest.log.dto.SendCodeLogDTO;
import org.ko.shin.rest.log.condition.QuerySendCodeLogCondition;


import java.util.List;

@Repository
public interface SendCodeLogRepository extends BaseMapper<SendCodeLog> {

    List<SendCodeLogDTO> queryList(QuerySendCodeLogCondition condition);

    int insertList (List<SendCodeLog> sendCodeLogs);

    String findCodeByType(
            @Param("sendType") String sendType,
            @Param("messageType") String messageType,
            @Param("address") String address);

}
