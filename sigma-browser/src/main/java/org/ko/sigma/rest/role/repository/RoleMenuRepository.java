package org.ko.sigma.rest.role.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.ko.sigma.data.entity.RoleMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMenuRepository extends BaseMapper<RoleMenu> {

    /**
     * <p>批量插入菜单</p>
     * @param roleMenus
     * @return
     */
    Long insertList(@Param("roleMenus") List<RoleMenu> roleMenus);
}
