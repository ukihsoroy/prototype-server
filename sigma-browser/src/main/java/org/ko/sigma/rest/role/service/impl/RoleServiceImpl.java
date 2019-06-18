package org.ko.sigma.rest.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ko.sigma.core.exception.TransactionalException;
import org.ko.sigma.core.type.SystemCode;
import org.ko.sigma.core.type.SystemConstants;
import org.ko.sigma.data.entity.Role;
import org.ko.sigma.rest.role.condition.RoleQueryListCondition;
import org.ko.sigma.rest.role.repository.RoleRepository;
import org.ko.sigma.rest.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class RoleServiceImpl extends ServiceImpl<RoleRepository, Role> implements RoleService {

    /**
     * 权限数据库对象
     */
    @Autowired private RoleRepository roleRepository;

    @Override
    public List<Role> queryRoleList(RoleQueryListCondition condition) {
        return roleRepository.selectList(new QueryWrapper<Role>().eq("available_status", 1));
    }

    @Override
    public Role queryRoleInfo(Long id) {
        return roleRepository.selectById(id);
    }

    @Override
    public Long createRole(Role role) {
        role.setAvailableStatus(SystemConstants.Enable.Available);
        if (roleRepository.insert(role) == 0) {
            throw new TransactionalException(SystemCode.INSERT_ERROR);
        }
        return role.getId();
    }

    @Override
    public Role updateRole(Long id, Role role) {
        role.setId(id);
        if (roleRepository.updateById(role) == 0) {
            throw new TransactionalException(SystemCode.UPDATE_ERROR);
        }
        return role;
    }

    @Override
    public Long deleteRole(Long id) {
        Role role = new Role();
        role.setId(id);
        role.setAvailableStatus(SystemConstants.Enable.Deleted);
        if (roleRepository.updateById(role) == 0) {
            throw new TransactionalException(SystemCode.DELETE_ERROR);
        }
        return id;
    }


}
