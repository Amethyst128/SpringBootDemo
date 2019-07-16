package com.zy.mybatisplusdemo.dao.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体
 * *mybatisPlus相当于是JPA的思想，表名和字段名与实体的实体名称和字段名称能够一一映射，要么一样要么驼峰名映射成功；
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

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private String pwdQuestion;
    private String pwdAnswer;

    private static final long serialVersionUID = 1L;

}