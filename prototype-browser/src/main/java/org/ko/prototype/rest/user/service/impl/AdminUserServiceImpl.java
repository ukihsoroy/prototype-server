package org.ko.prototype.rest.user.service.impl;

import org.ko.prototype.core.type.SystemConstants;
import org.ko.prototype.data.master.domain.AdminUser;
import org.ko.prototype.rest.user.condition.AdminUserQueryCondition;
import org.ko.prototype.rest.user.repository.AdminUserRepository;
import org.ko.prototype.rest.user.service.AdminUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class AdminUserServiceImpl implements AdminUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminUserServiceImpl.class);

    @Autowired private AdminUserRepository adminUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查找用户信息
        return null;
    }


    @Override
    public List<AdminUser> queryUserList(AdminUserQueryCondition condition) {
        Example e = new Example(AdminUser.class);
        e.createCriteria()
                .andEqualTo("deleteStatus", "1");
        return adminUserRepository.selectByExample(e);
    }

    @Override
    public AdminUser queryUserInfo(Long id) {
        return adminUserRepository.selectByPrimaryKey(id);
    }

    @Override
    public Long createUser(AdminUser adminUser) {
        adminUser.setDeleteStatus(SystemConstants.DeleteStatus.Available);
        adminUserRepository.insert(adminUser);
        return adminUser.getId();
    }
}
