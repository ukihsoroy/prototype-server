package org.ko.sigma.rest.user.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.ko.sigma.data.entity.UserRole;
import org.ko.sigma.rest.role.dto.RoleDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends BaseMapper<UserRole> {

    List<RoleDTO> findRolesByUserId(@Param("id") Long id);

}
