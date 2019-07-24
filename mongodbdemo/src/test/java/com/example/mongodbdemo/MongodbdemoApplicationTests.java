package com.example.mongodbdemo;

import com.example.mongodbdemo.dao.domain.UserDo;
import com.example.mongodbdemo.dao.mapper.UserRepository;
import com.example.mongodbdemo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodbdemoApplicationTests {

    @Test
    public void contextLoads() {
    }


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Test
    public void testInsert() {
        UserDo user = new UserDo();
        user.setUserName("test");
        user.setAddress("test address");
        user.setAge(Integer.valueOf(12));
        user.setPhoneNumber("1383777777");
        user.setSex("女");
        UserDo messageSent = userRepository.insert(user);
        System.out.println(messageSent);
    }

    @Test
    public void testSelect() {
        List<UserDo> allMessages = userRepository.findAll();
        System.out.println(allMessages);
        List<UserDo> searchResult1 = userRepository.findByUserName("test");
        System.out.println(searchResult1.get(0).getPhoneNumber());
    }

    @Test
    public void testUpdateMessage() {
        //获取详情
        Optional<UserDo> userDo = userRepository.findById("5d37f25e1d3a21e956c28c57");
        System.out.println(userDo.get());

        //测试修改
        userDo.get().setUserName("zhangsan");
        userService.editUser(userDo.get());
        System.out.println(userDo.get());
    }

    @Test
    public void getList() {
        //测试分页查询
        Pageable pageable = PageRequest.of(0, 5);
        Page<UserDo> messagePage = userRepository.findAll(pageable);
        System.out.println(messagePage);
    }

}
