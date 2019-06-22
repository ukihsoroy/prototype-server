package org.ko.sigma.rest.user.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.ko.sigma.rest.user.bean.UserEntity;
import org.ko.sigma.rest.user.condition.QueryUserPageCondition;
import org.ko.sigma.rest.user.dto.UserDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseMapper<UserEntity> {

    IPage<UserDTO> queryUserList(QueryUserPageCondition<UserEntity> condition);

}
