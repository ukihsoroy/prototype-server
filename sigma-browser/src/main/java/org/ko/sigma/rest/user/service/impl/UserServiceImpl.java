package org.ko.sigma.rest.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ko.sigma.core.bean.ExcelHeader;
import org.ko.sigma.core.bean.ExportExcelHelper;
import org.ko.sigma.core.constant.ExcelFormat;
import org.ko.sigma.core.exception.BusinessException;
import org.ko.sigma.data.entity.User;
import org.ko.sigma.rest.user.condition.QueryUserCondition;
import org.ko.sigma.rest.user.dto.UserDTO;
import org.ko.sigma.rest.user.repository.UserRepository;
import org.ko.sigma.rest.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.ko.sigma.core.constant.SystemCode.UNKNOWN_ERROR;

@Service
@Transactional(rollbackFor = Throwable.class)
public class UserServiceImpl extends ServiceImpl<UserRepository, User> implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired private UserRepository userRepository;

    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public IPage<UserDTO> queryUserList(QueryUserCondition<User> condition) {
        return userRepository.queryUserList(condition);
    }

    @Override
    public User queryUserInfo(Long id) {
        return userRepository.selectById(id);
    }

    @Override
    public Long createUser(User user) {
        //加密password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (userRepository.insert(user) == 0) {
            throw new BusinessException(UNKNOWN_ERROR);
        }
        return user.getId();
    }

    @Override
    public User updateUser(Long id, User user) {
        user.setId(id);
        if (userRepository.updateById(user) == 0) {
            throw new BusinessException(UNKNOWN_ERROR);
        }
        return user;
    }

    @Override
    public Long removeUser(Long id) {
        if (userRepository.deleteById(id) == 0) {
            throw new BusinessException(UNKNOWN_ERROR);
        }
        return id;
    }

    @Override
    public void export(String name, QueryUserCondition<User> condition, HttpServletResponse response) {
        condition.setSize(10000);
        IPage<UserDTO> users = userRepository.queryUserList(condition);
        ExportExcelHelper export = new ExportExcelHelper(users.getRecords(), buildExcelHeaders(), buildExcelFormats());
        export.sendHttpResponse(response, name, export.getWorkbook());
    }

    private List<ExcelHeader> buildExcelHeaders() {
        return Arrays.asList(
            ExcelHeader.of(0, 0, 0, 0, "ID"),
            ExcelHeader.of(0, 0, 1, 1, "用户名"),
            ExcelHeader.of(0, 0, 2, 2, "昵称"),
            ExcelHeader.of(0, 0, 3, 3, "生日"),
            ExcelHeader.of(0, 0, 4, 4, "性别"),
            ExcelHeader.of(0, 0, 5, 5, "手机"),
            ExcelHeader.of(0, 0, 6, 6, "邮箱"),
            ExcelHeader.of(0, 0, 7, 7, "签名")
        );
    }

    private Map<String, ExcelFormat> buildExcelFormats() {
        Map<String, ExcelFormat> map = new HashMap<>();
        map.put("id", ExcelFormat.FORMAT_LONG);
        map.put("username", ExcelFormat.FORMAT_VARCHAR);
        map.put("nickname", ExcelFormat.FORMAT_VARCHAR);
        map.put("birthday", ExcelFormat.FORMAT_VARCHAR);
        map.put("gender", ExcelFormat.FORMAT_VARCHAR);
        map.put("mobile", ExcelFormat.FORMAT_VARCHAR);
        map.put("email", ExcelFormat.FORMAT_VARCHAR);
        map.put("signature", ExcelFormat.FORMAT_VARCHAR);
        return map;
    }

}
