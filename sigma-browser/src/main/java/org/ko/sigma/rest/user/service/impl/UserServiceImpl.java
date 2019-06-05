package org.ko.sigma.rest.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ko.sigma.rest.user.entity.UserEntity;
import org.ko.sigma.core.exception.TransactionalException;
import org.ko.sigma.core.type.SystemConstants;
import org.ko.sigma.rest.user.condition.UserQueryListCondition;
import org.ko.sigma.rest.user.repository.UserRepository;
import org.ko.sigma.rest.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.ko.sigma.core.type.SystemCode.*;

@Service
@Transactional(rollbackFor = Throwable.class)
public class UserServiceImpl extends ServiceImpl<UserRepository, UserEntity> implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.selectOne(new QueryWrapper<UserEntity>().eq("username", username));
    }


    @Override
    public List<UserEntity> queryUserList(UserQueryListCondition condition) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        return userRepository.selectList(queryWrapper);
    }

    @Override
    public UserEntity queryUserInfo(Long id) {
        return userRepository.selectById(id);
    }

    @Override
    public Long createUser(UserEntity userEntity) {
        userEntity.setAvailableStatus(SystemConstants.AvailableStatus.Available);
        if (userRepository.insert(userEntity) == 0) {
            throw new TransactionalException(INSERT_ERROR);
        }
        return userEntity.getId();
    }

    @Override
    public UserEntity updateUser(Long id, UserEntity userEntity) {
        userEntity.setId(id);
        if (userRepository.updateById(userEntity) == 0) {
            throw new TransactionalException(UPDATE_ERROR);
        }
        return userEntity;
    }

    @Override
    public Long removeUser(Long id) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setAvailableStatus(SystemConstants.AvailableStatus.Deleted);
        if (userRepository.updateById(userEntity) == 0) {
            throw new TransactionalException(DELETE_ERROR);
        }
        return id;
    }
}
