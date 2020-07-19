package org.ko.prototype.rest.department.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ko.prototype.data.bean.PageCondition;

@Data
@EqualsAndHashCode(callSuper = true)
public class QueryDepartmentCondition<Department> extends PageCondition<Department> {


}