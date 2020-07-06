package org.ko.shin.rest.dict.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ko.shin.data.bean.PageCondition;

@Data
@EqualsAndHashCode(callSuper = true)
public class QueryDictCondition<Dict> extends PageCondition<Dict> {


}