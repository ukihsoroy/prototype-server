package org.ko.sigma.rest.department.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.ko.sigma.data.entity.Department;
import org.springframework.stereotype.Repository;
import org.ko.sigma.rest.department.dto.DepartmentDTO;
import org.ko.sigma.rest.department.condition.QueryDepartmentCondition;


import java.util.List;

@Repository
public interface DepartmentRepository extends BaseMapper<Department> {

    List<DepartmentDTO> queryList(QueryDepartmentCondition condition);

    int insertList (List<Department> departments);

}
