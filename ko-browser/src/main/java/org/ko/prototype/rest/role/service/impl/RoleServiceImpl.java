package org.ko.prototype.rest.role.service.impl;

import org.ko.prototype.core.type.SystemConstants;
import org.ko.prototype.data.master.domain.Role;
import org.ko.prototype.rest.role.repository.RoleRepository;
import org.ko.prototype.rest.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired private RoleRepository roleRepository;

    @Override
    public Long createRole(Role role) {
        role.setDelStatus(SystemConstants.DelStatus.Available);
        roleRepository.insert(role);
        return role.getId();
    }
}
