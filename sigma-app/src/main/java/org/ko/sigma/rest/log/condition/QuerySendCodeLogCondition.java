package org.ko.sigma.rest.log.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ko.sigma.data.bean.PageCondition;

@Data
@EqualsAndHashCode(callSuper = true)
public class QuerySendCodeLogCondition<SendCodeLog> extends PageCondition<SendCodeLog> {


}