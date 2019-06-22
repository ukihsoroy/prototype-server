package org.ko.sigma.rest.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.ko.sigma.data.constants.UserConstants;
import org.ko.sigma.rest.system.service.SystemService;
import org.ko.sigma.rest.user.bean.UserEntity;
import org.ko.sigma.rest.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Throwable.class)
public class SystemServiceImpl implements SystemService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.selectOne(new QueryWrapper<UserEntity>().eq(UserConstants.Columns.USERNAME, username));
    }

    @Override
    public UserEntity login(String username, String password) {
        return null;
    }

    @Override
    public UserEntity signIn(UserEntity userEntity) {
        return null;
    }

    @Override
    public Long logout() {
        return null;
    }
}
