package org.ko.sigma.rest.system;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
//用SpringRunner来运行测试用例
@RunWith(SpringRunner.class)
public class GeneratorPassword {

    @Autowired private PasswordEncoder passwordEncoder;

    @Test public void generator() {
        String password = passwordEncoder.encode("tiger");
        System.out.println(password);
    }
}
