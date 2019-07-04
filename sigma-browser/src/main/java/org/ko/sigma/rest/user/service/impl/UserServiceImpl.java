package org.ko.sigma.rest.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ko.sigma.core.exception.TransactionalException;
import org.ko.sigma.data.entity.User;
import org.ko.sigma.rest.user.condition.QueryUserPageCondition;
import org.ko.sigma.rest.user.dto.UserDTO;
import org.ko.sigma.rest.user.repository.UserRepository;
import org.ko.sigma.rest.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.ko.sigma.core.type.SystemCode.*;

@Service
@Transactional(rollbackFor = Throwable.class)
public class UserServiceImpl extends ServiceImpl<UserRepository, User> implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired private UserRepository userRepository;

    @Override
    public IPage<UserDTO> queryUserList(QueryUserPageCondition<User> condition) {
        return userRepository.queryUserList(condition);
    }

    @Override
    public User queryUserInfo(Long id) {
        return userRepository.selectById(id);
    }

    @Override
    public Long createUser(User User) {
        if (userRepository.insert(User) == 0) {
            throw new TransactionalException(INSERT_ERROR);
        }
        return User.getId();
    }

    @Override
    public User updateUser(Long id, User User) {
        User.setId(id);
        if (userRepository.updateById(User) == 0) {
            throw new TransactionalException(UPDATE_ERROR);
        }
        return User;
    }

    @Override
    public Long removeUser(Long id) {
        if (userRepository.deleteById(id) == 0) {
            throw new TransactionalException(DELETE_ERROR);
        }
        return id;
    }

}
