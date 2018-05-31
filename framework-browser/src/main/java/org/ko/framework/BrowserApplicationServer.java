package org.ko.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "org.ko.data.*.mapper")
public class BrowserApplicationServer {

    public static void main(String[] args) {
        SpringApplication.run(BrowserApplicationServer.class, args);
    }
}
