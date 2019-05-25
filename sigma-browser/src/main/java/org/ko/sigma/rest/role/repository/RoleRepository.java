package org.ko.sigma.rest.role.repository;

import org.ko.sigma.data.master.entity.Role;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface RoleRepository extends Mapper<Role> {

}
