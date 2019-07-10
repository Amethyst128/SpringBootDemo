package com.zy.mybatisinterceptor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

//springboot启动时会自动注入数据源和配置jpa，会报异常：Cannot determine embedded database driver class for database type NONE
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@MapperScan("com.zy.mybatisinterceptor.dao.mapper")
public class MybatisinterceptorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisinterceptorApplication.class, args);
    }

}
