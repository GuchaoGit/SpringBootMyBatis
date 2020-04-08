# SpringBoot

## Spring Boot 整合Mybatis开发
[MyBatis](https://github.com/GuchaoGit/MyBatis.git)框架基础

## 项目搭建步骤：
1. 新建项目  
2. 修改pom.xml 配置文件，添加依赖
3. 创建目录结构
4. 创建实体 entity
5. 创建dao层及对应的mapper.xml文件
6. 创建service层
7. 创建controller层
8. 将 Mybatis 和 Spring Boot 整合，需要配置 Mybatis 的 mapper 接口位置和 xml 文件的位置，只需要两个代码文件就可以完成的整合
```
//配置application.properties、SpringbootApplication.java 
//1、application.properties
#mapper  xml文件位置
mybatis.mapper-locations=classpath:mapper/*.xml

//2、SpringbootApplication.java
package com.shiyanlou.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 扫描 mapper 接口位置
@MapperScan(basePackages = {"com.shiyanlou.springboot.dao"})
public class SpringbootApplication{

  public static void main(String[] args){
    SpringApplication.run(SpringbootApplication.class, args);
  }
}
```




