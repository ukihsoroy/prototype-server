package org.ko.sigma.rest.role.service;

import org.ko.sigma.data.master.domain.Role;
import org.ko.sigma.rest.role.condition.RoleQueryListCondition;

import java.util.List;

public interface RoleService {

    /**
     * <p>查询权限列表</p>
     * @param condition
     * @return
     */
    List<Role> queryRoleList(RoleQueryListCondition condition);

    /**
     * <p>通过主键查询权限</p>
     * @param id
     * @return
     */
    Role queryRoleInfo(Long id);

    /**
     * <p>创建新的权限</p>
     * @param mapRole
     */
    Long createRole(Role mapRole);

    /**
     * <p>通过ID</p>
     * @param id role Id
     * @param role 权限对象
     * @return
     */
    Role updateRole(Long id, Role role);

    /**
     * <p>删除权限</p>
     * @param id role主键id
     * @return
     */
    Long deleteRole(Long id);

}
