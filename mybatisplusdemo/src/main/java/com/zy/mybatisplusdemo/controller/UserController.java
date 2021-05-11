package com.zy.mybatisplusdemo.controller;

import com.zy.mybatisplusdemo.dao.mapper.UserDoMapper;
import com.zy.mybatisplusdemo.dao.model.UserDo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author: zhangyu
 * @Date:Created in 16:52 2019/7/15
 */
@Api(value = "PubDoctorController", tags = "医生相关")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserDoMapper userDoMapper;

    @PostMapping("/get")
    public List<UserDo> getUserList() {
        return userDoMapper.getUserList();
    }

}
