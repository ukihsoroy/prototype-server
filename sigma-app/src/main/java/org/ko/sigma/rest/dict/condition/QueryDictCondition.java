package org.ko.sigma.rest.dict.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ko.sigma.data.bean.PageCondition;

@Data
@EqualsAndHashCode(callSuper = true)
public class QueryDictCondition<Dict> extends PageCondition<Dict> {


}