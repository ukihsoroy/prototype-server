package org.ko.prototype.rest.dict.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ko.prototype.data.bean.PageCondition;

@Data
@EqualsAndHashCode(callSuper = true)
public class QueryDictCondition<Dict> extends PageCondition<Dict> {


}