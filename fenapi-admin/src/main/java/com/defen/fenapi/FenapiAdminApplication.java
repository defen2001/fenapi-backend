package com.defen.fenapi;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.defen.fenapi.mapper")
//@EnableDubbo
public class FenapiAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(FenapiAdminApplication.class, args);
    }
}
