package org.ko.prototype.rest.log.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ko.prototype.data.bean.PageCondition;

@Data
@EqualsAndHashCode(callSuper = true)
public class QuerySendCodeLogCondition<SendCodeLog> extends PageCondition<SendCodeLog> {


}