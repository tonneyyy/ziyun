package com.hxzy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//扫描 mybatis接口包，这个没有就报错
@MapperScan(basePackages = "com.hxzy.mapper")
public class ZiyunApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZiyunApplication.class, args);
    }

}
