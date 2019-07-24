package com.example.mongodbdemo.service;

import com.example.mongodbdemo.dao.domain.UserDo;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Desc:
 * @Author: zy
 * @Date:Created in 2019/7/23
 */
public interface UserService {

    /**
     * @desc 创建或者编辑一条消息
     * @Author zy
     * @Date 2019/7/24
     */
    void editUser(UserDo userDo);

    /**
     * @desc 删除一条消息
     * @Author zy
     * @Date 2019/7/24
     */
    void deleteUserById(String messageId);

    /**
     * @desc 获取某一条的消息详情
     * @Author zy
     * @Date 2019/7/24
     */
    UserDo getDetailById(String messageId);

    /**
     * @desc 分页获取消息列表
     * @Author zy
     * @Date 2019/7/24
     */
    void getUserList(Pageable pageable);

}
