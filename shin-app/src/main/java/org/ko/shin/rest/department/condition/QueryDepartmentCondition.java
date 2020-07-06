package org.ko.shin.rest.department.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ko.shin.data.bean.PageCondition;

@Data
@EqualsAndHashCode(callSuper = true)
public class QueryDepartmentCondition<Department> extends PageCondition<Department> {


}