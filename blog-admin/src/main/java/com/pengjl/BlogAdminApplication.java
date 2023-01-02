package com.pengjl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.pengjl.mapper")
public class BlogAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogAdminApplication.class,args);
    }
}
