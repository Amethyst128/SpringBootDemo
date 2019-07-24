package com.example.mongodbdemo.service.impl;

import com.example.mongodbdemo.dao.domain.UserDo;
import com.example.mongodbdemo.dao.mapper.UserRepository;
import com.example.mongodbdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Desc:
 * @Author: zy
 * @Date:Created in 2019/7/24
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void editUser(UserDo userDo) {
        if (null != userDo && null != userDo.getId()) {
            createMessage(userDo);
        } else if (null != userDo) {
            updateMessage(userDo);
        }
    }

    private void createMessage(UserDo userDo) {
        userRepository.save(userDo);
    }

    //TODO 这里修改的时候，把所有字段的值都传过来吧
    private void updateMessage(UserDo userDo) {
        if (userRepository.existsById(userDo.getId())) {
            Query queue = new Query(new Criteria("id").is(userDo.getId()));
            Update update = new Update().set("userName", userDo.getUserName())
                    .set("age", userDo.getAge())
                    .set("phoneNumber", userDo.getPhoneNumber());
            mongoTemplate.updateFirst(queue, update, UserDo.class);
        }
    }

    @Override
    public void deleteUserById(String messageId) {
        //根据Id 逻辑删除
        Query queue = new Query(new Criteria("id").is(messageId));
        Update update = new Update().set("isDeleted", 1);
        mongoTemplate.updateFirst(queue, update, UserDo.class);
    }

    @Override
    public UserDo getDetailById(String messageId) {
        Optional<UserDo> message = userRepository.findById(messageId);
        UserDo userDos = message.get();
        return userDos;
    }

    @Override
    public void getUserList(Pageable pageable) {
        Page<UserDo> messagePage = userRepository.findAll(pageable);
    }
}
