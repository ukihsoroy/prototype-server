package org.ko.shin.rest.log.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ko.shin.data.bean.PageCondition;

@Data
@EqualsAndHashCode(callSuper = true)
public class QuerySendCodeLogCondition<SendCodeLog> extends PageCondition<SendCodeLog> {


}