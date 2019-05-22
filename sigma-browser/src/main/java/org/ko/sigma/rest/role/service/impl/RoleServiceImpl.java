package org.ko.sigma.rest.role.service.impl;

import org.ko.sigma.core.exception.TransactionalException;
import org.ko.sigma.core.type.SystemCode;
import org.ko.sigma.core.type.SystemConstants;
import org.ko.sigma.data.master.domain.Role;
import org.ko.sigma.rest.role.condition.RoleQueryListCondition;
import org.ko.sigma.rest.role.repository.RoleRepository;
import org.ko.sigma.rest.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class RoleServiceImpl implements RoleService {

    /**
     * 权限数据库对象
     */
    @Autowired private RoleRepository roleRepository;

    @Override
    public List<Role> queryRoleList(RoleQueryListCondition condition) {
        Example e = new Example(Role.class);
        e.createCriteria()
                .andEqualTo("availableStatus", "1");
        return roleRepository.selectByExample(e);
    }

    @Override
    public Role queryRoleInfo(Long id) {
        return roleRepository.selectByPrimaryKey(id);
    }

    @Override
    public Long createRole(Role role) {
        role.setAvailableStatus(SystemConstants.AvailableStatus.Available);
        if (roleRepository.insert(role) == 0) {
            throw new TransactionalException(SystemCode.INSERT_ERROR);
        }
        return role.getId();
    }

    @Override
    public Role updateRole(Long id, Role role) {
        role.setId(id);
        if (roleRepository.updateByPrimaryKeySelective(role) == 0) {
            throw new TransactionalException(SystemCode.UPDATE_ERROR);
        }
        return role;
    }

    @Override
    public Long deleteRole(Long id) {
        Role role = new Role();
        role.setId(id);
        role.setAvailableStatus(SystemConstants.AvailableStatus.Deleted);
        if (roleRepository.updateByPrimaryKeySelective(role) == 0) {
            throw new TransactionalException(SystemCode.DELETE_ERROR);
        }
        return id;
    }


}
