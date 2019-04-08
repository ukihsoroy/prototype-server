package org.ko.prototype.rest.role.repository;

import org.ko.prototype.data.master.domain.Role;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface RoleRepository extends Mapper<Role> {

}
