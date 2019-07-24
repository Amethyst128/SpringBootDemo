package com.example.mongodbdemo.dao.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Desc: 实体定义
 * @Author: zy
 * @Date:Created in 2019/7/23
 */
@Data
@Document(collection = "user")
public class UserDo {

    @Id
    private String id;
    private Integer isDeleted;
    private String userName;
    private Integer age;
    private String sex;
    private String phoneNumber;
    private String address;

}
