package com.example.mongodbdemo.dao.mapper;

import com.example.mongodbdemo.dao.domain.UserDo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @Desc: repository
 * @Author: zy
 * @Date:Created in 2019/7/23
 */
public interface UserRepository extends MongoRepository<UserDo, String> {

    List<UserDo> findByUserName(String userName);

}
