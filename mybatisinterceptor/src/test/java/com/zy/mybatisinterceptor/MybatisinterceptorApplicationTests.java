package com.zy.mybatisinterceptor;

import com.zy.mybatisinterceptor.dao.mapper.UserDoMapper;
import com.zy.mybatisinterceptor.dao.model.UserDo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisinterceptorApplicationTests {

    @Resource
    private UserDoMapper userDoMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void createUser() {
        UserDo user = new UserDo();
        user.setAddress("浙江杭州")
                .setAge(23)
                .setPwd("123456")
                .setPwdQuestion("年份")
                .setPwdAnswer("2016")
                .setPhoneNumber("13888888888")
                .setSex("男")
                .setUserName("Amethyst");
        System.out.println("开始插入用户信息");
        userDoMapper.insertUser(user);
        System.out.println("插入用户成功");
    }

}
