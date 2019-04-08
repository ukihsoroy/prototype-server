package org.ko.prototype.rest.role.service;

import org.ko.prototype.data.master.domain.Role;

public interface RoleService {

    /**
     * 创建新的权限
     * @param mapRole
     */
    Long createRole(Role mapRole);
}
