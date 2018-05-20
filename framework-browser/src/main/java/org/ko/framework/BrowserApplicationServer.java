package org.ko.framework;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "org.ko.data.*.mapper")
public class BrowserApplicationServer {

    public static void main(String[] args) {
        SpringApplication.run(BrowserApplicationServer.class, args);
    }
}
