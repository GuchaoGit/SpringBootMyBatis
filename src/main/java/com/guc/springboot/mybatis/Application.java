package com.guc.springboot.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author guc
 * @Date 2020/4/8 14:04
 * @Description 程序入口
 */
@SpringBootApplication
// 扫描 mapper 接口位置
@MapperScan(basePackages = {"com.guc.springboot.mybatis.dao"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
