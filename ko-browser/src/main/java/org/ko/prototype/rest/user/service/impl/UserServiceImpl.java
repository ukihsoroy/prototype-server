package org.ko.prototype.rest.user.service.impl;

import org.ko.prototype.core.type.SystemConstants;
import org.ko.prototype.data.master.domain.User;
import org.ko.prototype.rest.user.condition.UserQueryCondition;
import org.ko.prototype.rest.user.repository.UserRepository;
import org.ko.prototype.rest.user.service.UserService;
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
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查找用户信息
        return null;
    }


    @Override
    public List<User> queryUserList(UserQueryCondition condition) {
        Example e = new Example(User.class);
        e.createCriteria()
                .andEqualTo("delStatus", "1");
        return userRepository.selectByExample(e);
    }

    @Override
    public User queryUserInfo(Long id) {
        return userRepository.selectByPrimaryKey(id);
    }

    @Override
    public Long createUser(User user) {
        user.setDelStatus(SystemConstants.DelStatus.Available);
        userRepository.insert(user);
        return user.getId();
    }

    @Override
    public User updateUser(Long id, User user) {
        user.setId(id);
        userRepository.updateByPrimaryKeySelective(user);
        return user;
    }

    @Override
    public Long removeUser(Long id) {
        userRepository.deleteByPrimaryKey(id);
        return id;
    }
}
