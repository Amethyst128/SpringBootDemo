package com.zy.mybatisplusdemo;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zy.mybatisplusdemo.dao.mapper.UserDoMapper;
import com.zy.mybatisplusdemo.dao.model.UserDo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisplusdemoApplicationTests {

    @Resource
    private UserDoMapper userDoMapper;


    @Test
    public void createUser() {
        UserDo user = new UserDo();
        user.setAddress("浙江杭州")
                .setAge(24)
                .setPwd("456789")
                .setPwdQuestion("年份")
                .setPwdAnswer("2018")
                .setPhoneNumber("13888888888")
                .setSex("男")
                .setUserName("AmethMa");
        System.out.println("开始插入用户信息");
        int result = userDoMapper.insert(user);
        if (result > 0) {
            System.out.println("插入用户成功");
        } else {
            System.out.println("插入用户失败");
        }
    }

    //条件查询<放在map中>
    @Test
    public void selectUserByName() {
        Map map = new HashMap();
        map.put("userName", "zhangsan");
        userDoMapper.selectByMap(map);
    }


    @Test
    public void updateUser() {
        UserDo user = new UserDo();
        user.setId(1).setAddress("上海")
                .setAge(24)
                .setPwd("789654")
                .setPwdQuestion("年份")
                .setPwdAnswer("2017")
                .setPhoneNumber("13888888888")
                .setSex("男")
                .setUserName("Amethyst");
        userDoMapper.updateById(user);
    }

    @Test
    public void getUserListByPage() {
        int pageSize = 10;
        int pageNumber = 3;
        Page<UserDo> page = new Page<>(pageNumber, pageSize);
        EntityWrapper<UserDo> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("userName", "zhangsan");
        userDoMapper.selectPage(page, entityWrapper);
    }

}
