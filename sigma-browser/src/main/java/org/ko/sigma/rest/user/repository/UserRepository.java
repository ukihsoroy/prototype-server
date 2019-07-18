package org.ko.sigma.rest.user.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.ko.sigma.data.entity.User;
import org.ko.sigma.rest.user.condition.QueryUserPageCondition;
import org.ko.sigma.rest.user.dto.UserDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseMapper<User> {

    UserDTO loadUserByUsername(String username);

    IPage<UserDTO> queryUserList(QueryUserPageCondition<User> condition);

}
