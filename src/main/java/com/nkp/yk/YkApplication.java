package com.nkp.yk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.nkp.dao")
@ComponentScan(basePackages = {"com.nkp.*"})
public class YkApplication {

    public static void main(String[] args) {
        SpringApplication.run(YkApplication.class, args);
    }

}
