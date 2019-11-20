package com.example.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private UserController userController;


    /**
     * 测试拦截注解的执行顺序  around---after--afterReturning
     * @Around(切入点)  要执行业务 通过参数ProceedingJoinPoint.proceed()
     */
    @Test
    public void getAllUserName() {
        log.info("开始请求 getAllUserName方法...");
        userController.getAllUserName();
        log.info("请求 getAllUserName 执行结束");
    }

}