package com.zy.mybatisinterceptor.dao.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体
 *
 * @Author zhangyu
 * @Date 2019/7/9 15:44
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class UserDo implements Serializable {

    private int id;
    private String userName;
    private String sex;
    private int age;
    private String phoneNumber;
    private String address;
    private String pwd;
    private String email;
    private Date createTime;
    private Date updateTime;
    private String pwdQuestion;
    private String pwdAnswer;

    private static final long serialVersionUID = 1L;

}