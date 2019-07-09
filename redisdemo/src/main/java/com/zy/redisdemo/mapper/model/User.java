package com.zy.redisdemo.mapper.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Description: 用户实体类
 * @Author: zhangyu
 * @Date:Created in 11:16 2019/6/17
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class User {

    private Long id;
    private String userName;
    private String guid;
    private int age;
    private Date gmtCreate;
    private Date gmtModified;

}
