package org.ko.sigma.rest.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.collections.CollectionUtils;
import org.ko.sigma.core.exception.TransactionalException;
import org.ko.sigma.core.exception.UniqueException;
import org.ko.sigma.data.constants.RoleCodeEnum;
import org.ko.sigma.data.constants.UserConstants;
import org.ko.sigma.data.entity.Role;
import org.ko.sigma.data.entity.User;
import org.ko.sigma.data.entity.UserRole;
import org.ko.sigma.rest.system.service.SystemService;
import org.ko.sigma.rest.user.dto.UserDTO;
import org.ko.sigma.rest.user.repository.UserRepository;
import org.ko.sigma.rest.user.repository.UserRoleRepository;
import org.ko.sigma.rest.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.ko.sigma.core.type.SystemCode.*;

@Service
@Transactional(rollbackFor = Throwable.class)
public class SystemServiceImpl implements SystemService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;


    private static final Logger logger = LoggerFactory.getLogger(SystemServiceImpl.class);


    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO = userRepository.loadUserByUsername(username);
        if (userDTO != null) {
            List<String> roles = userDTO.getRoleDTOS().stream().map(Role::getCode).collect(Collectors.toList());
            userDTO.setRoles(roles);
            return userDTO;
        }
        throw new UsernameNotFoundException("用户不存在");
    }

    @Override
    public UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException {
        UserDTO userDTO = userRepository.loadUserByMobile(mobile);
        if (userDTO != null) {
            List<String> roles = userDTO.getRoleDTOS().stream().map(Role::getCode).collect(Collectors.toList());
            userDTO.setRoles(roles);
            return userDTO;
        }
        throw new UsernameNotFoundException("用户不存在");
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

    @Override
    public Long register(UserDTO userDTO, HttpServletRequest request) {

        //检查用户名，邮箱，手机是否重复
        validatorRegister(userDTO);
        
        Long userId = userService.createUser(map(userDTO));


        List<UserRole> userRoles;
        if (CollectionUtils.isNotEmpty(userDTO.getRoles())) {
            userRoles = userDTO.getRoleDTOS().stream().map(roleDTO -> {
                UserRole userRole = new UserRole();
                userRole.setRoleCode(roleDTO.getCode());
                userRole.setUserId(userId);
                return userRole;
            }).collect(Collectors.toList());
        } else {
            userRoles = Stream.of(RoleCodeEnum.ROLE_USER).map(roleDTO -> {
                UserRole userRole = new UserRole();
                userRole.setRoleCode(roleDTO.getCode());
                userRole.setUserId(userId);
                return userRole;
            }).collect(Collectors.toList());
        }

        Long count = userRoleRepository.insertList(userRoles);

        if (count == 0) {
            throw new TransactionalException(REGISTER_USER_ERROR);
        }

        registerSession(userDTO, request);

        return userId;
    }

    @Override
    public void validUserUnique(String column, String value) {
        User user = userRepository.selectOne(new QueryWrapper<User>().eq(column, value));
        if (user != null) {
            throw new UniqueException(column + "重复!");
        }
    }

    private void validatorRegister(UserDTO userDTO) {
        Integer countUsername = userRepository.selectCount(
                new QueryWrapper<User>().eq(UserConstants.Columns.USERNAME, userDTO.getUsername()));
        if (countUsername > 0) {
            throw new TransactionalException(USERNAME_REPEAT);
        }

        Integer countMobile = userRepository.selectCount(
                new QueryWrapper<User>().eq(UserConstants.Columns.MOBILE, userDTO.getMobile()));
        if (countMobile > 0) {
            throw new TransactionalException(MOBILE_REPEAT);
        }

        Integer countEmail = userRepository.selectCount(
                new QueryWrapper<User>().eq(UserConstants.Columns.EMAIL, userDTO.getEmail()));
        if (countEmail > 0) {
            throw new TransactionalException(EMAIL_REPEAT);
        }

    }

    private void registerSession(UserDTO userDTO, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword());
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
        request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
    }

    /**
     * UserDTO mapTo User
     * @param userDTO
     * @return
     */
    private User map (UserDTO userDTO) {
        User user = new User();
        if (userDTO != null) {
            BeanUtils.copyProperties(userDTO, user);
        }
        return user;
    }
}
