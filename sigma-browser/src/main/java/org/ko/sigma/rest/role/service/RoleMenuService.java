package org.ko.sigma.rest.role.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ko.sigma.data.entity.RoleMenu;

import java.util.List;

public interface RoleMenuService extends IService<RoleMenu> {

    /**
     * <p>创建权限和菜单关联</p>
     * @return 权限ID
     */
    public Long createRoleMenu(String roleCode, List<Long> menuIds);
}
