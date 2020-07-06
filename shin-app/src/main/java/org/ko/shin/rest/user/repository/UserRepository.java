package org.ko.shin.rest.user.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.ko.shin.data.entity.User;
import org.ko.shin.rest.user.condition.QueryUserCondition;
import org.ko.shin.rest.user.dto.UserDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseMapper<User> {

    /**
     * 通过用户名加载用户
     * @param username 用户名
     * @return
     */
    UserDTO loadUserByUsername(String username);

    /**
     * 通过手机号加载用户
     * @param mobile 手机号
     * @return
     */
    UserDTO loadUserByMobile(String mobile);

    /**
     * 查询用户列表
     * @param condition 搜索条件
     * @return
     */
    IPage<UserDTO> queryUserList(QueryUserCondition<User> condition);

}
