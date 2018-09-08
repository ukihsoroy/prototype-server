package org.ko.prototype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "org.ko.prototype.rest.*.repository")
public class BrowserApplicationServer {

    public static void main(String[] args) {
        SpringApplication.run(BrowserApplicationServer.class, args);
    }
}
