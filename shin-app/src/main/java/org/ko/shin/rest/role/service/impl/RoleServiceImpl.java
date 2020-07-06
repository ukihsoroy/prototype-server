package org.ko.shin.rest.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ko.shin.core.exception.BusinessException;
import org.ko.shin.core.constant.SystemCode;
import org.ko.shin.data.constants.RoleConstants;
import org.ko.shin.data.constants.RoleMenuConstants;
import org.ko.shin.data.entity.Role;
import org.ko.shin.data.entity.RoleMenu;
import org.ko.shin.rest.role.condition.QueryRoleCondition;
import org.ko.shin.rest.role.repository.RoleMenuRepository;
import org.ko.shin.rest.role.repository.RoleRepository;
import org.ko.shin.rest.role.service.RoleService;
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

    /**
     * 权限菜单关联数据
     */
    @Autowired private RoleMenuRepository roleMenuRepository;

    @Override
    public List<Role> queryRoleList(QueryRoleCondition condition) {
        return roleRepository.selectList(new QueryWrapper<>());
    }

    @Override
    public Role queryRoleInfo(String code) {
        return roleRepository.selectOne(new QueryWrapper<Role>().eq(RoleConstants.Columns.CODE, code));
    }

    @Override
    public Long createRole(Role role) {
        if (roleRepository.insert(role) == 0) {
            throw new BusinessException(SystemCode.UNKNOWN_ERROR);
        }
        return role.getId();
    }

    @Override
    public Role updateRole(String code, Role role) {
        role.setCode(code);
        if (roleRepository.update(role, new UpdateWrapper<Role>().eq(RoleConstants.Columns.CODE, code)) == 0) {
            throw new BusinessException(SystemCode.UNKNOWN_ERROR);
        }
        return role;
    }

    @Override
    public String deleteRole(String code) {
        roleMenuRepository.delete(new QueryWrapper<RoleMenu>().eq(RoleMenuConstants.Columns.ROLE_CODE, code));
        if (roleRepository.delete(new QueryWrapper<Role>().eq(RoleConstants.Columns.CODE, code)) == 0) {
            throw new BusinessException(SystemCode.UNKNOWN_ERROR);
        }
        return code;
    }


}
