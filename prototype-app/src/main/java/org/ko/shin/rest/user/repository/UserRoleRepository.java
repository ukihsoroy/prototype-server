package org.ko.prototype.rest.user.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.ko.prototype.data.entity.UserRole;
import org.ko.prototype.rest.role.dto.RoleDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends BaseMapper<UserRole> {

    List<RoleDTO> findRolesByUserId(@Param("id") Long id);

    Long insertList(@Param("userRoles") List<UserRole> userRoles);

}
