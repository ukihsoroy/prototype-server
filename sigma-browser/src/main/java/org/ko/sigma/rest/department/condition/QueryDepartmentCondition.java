package org.ko.sigma.rest.department.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ko.sigma.data.bean.PageCondition;

@Data
@EqualsAndHashCode(callSuper = true)
public class QueryDepartmentCondition<Department> extends PageCondition<Department> {


}