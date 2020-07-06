package org.ko.shin.rest.department.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.ko.shin.data.entity.Department;
import org.springframework.stereotype.Repository;
import org.ko.shin.rest.department.dto.DepartmentDTO;
import org.ko.shin.rest.department.condition.QueryDepartmentCondition;


import java.util.List;

@Repository
public interface DepartmentRepository extends BaseMapper<Department> {

    List<DepartmentDTO> queryList(QueryDepartmentCondition condition);

    int insertList (List<Department> departments);

}
