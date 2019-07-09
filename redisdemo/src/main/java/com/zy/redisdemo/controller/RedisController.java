package com.zy.redisdemo.controller;

import com.zy.redisdemo.configuration.util.RedisUtil;
import com.zy.redisdemo.mapper.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description: redis控制器
 * @Author: zhangyu
 * @Date:Created in 11:07 2019/6/17
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    //redis中存储的过期时间
    private static int expireTime = 60;

    @Resource
    private RedisUtil redisUtil;

    @PostMapping("set")
    public boolean redisSet(String key, String value) {
        User user = new User();
        user.setAge(1);
        user.setUserName("redis test");
        user.setGuid("1111");
        user.setGmtCreate(new Date());
        //todo zy 2019-06-21 测试redis
//        return redisUtil.
        return true;
    }


}
