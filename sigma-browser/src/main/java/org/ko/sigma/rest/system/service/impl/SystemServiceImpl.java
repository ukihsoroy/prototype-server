package org.ko.sigma.rest.system.service.impl;

import org.ko.sigma.rest.system.service.SystemService;
import org.ko.sigma.rest.user.dto.UserDTO;
import org.ko.sigma.rest.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Throwable.class)
public class SystemServiceImpl implements SystemService {

    @Autowired
    private UserRepository userRepository;


    private static final Logger logger = LoggerFactory.getLogger(SystemServiceImpl.class);


    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO = userRepository.loadUserByUsername(username);
        if (userDTO == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return userDTO;
    }


    @Override
    public SocialUserDetails loadUserByUserId(String username) throws UsernameNotFoundException {
        return buildUser(username);
    }

    private SocialUserDetails buildUser(String userId) {
        //假定数据库密码
        String password = passwordEncoder.encode("123456");

        logger.info("数据库密码是: {}", password);

        //根据查找到的用户信息, 判断用户是否被冻结
        return new SocialUser(
                userId,
                password,
                true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN, ROLE_USER"));
    }
}
