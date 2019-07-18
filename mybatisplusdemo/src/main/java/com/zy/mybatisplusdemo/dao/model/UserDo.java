package com.zy.mybatisplusdemo.dao.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.zy.mybatisplusdemo.core.enumerate.GradeEnum;
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
    private String pwdQuestion;
    private String pwdAnswer;

    //逻辑删除功能----从3.1.1开始不再需要单独写一个sqlInjector的bean，直接定义@TableLogic注解即可，这个先不测试
    @TableLogic
    private int is_deleted;

    //自定义字段填充功能----定义TableField 自动填充能能识别填充类型
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * ----测试通用枚举功能；
     * 年级--枚举属性   原生枚举（带{@link com.baomidou.mybatisplus.annotation.EnumValue})
     * 数据库字段：grade INT(2)
     */
//    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    @TableField(value = "grade")
    public GradeEnum grade;


    /**
     * ----测试乐观锁字段
     * 一、特别注意：
     * 1. @version注解必须要；
     * 2. 支持的数据类型只有:int,Integer,long,Long,Date,Timestamp,LocalDateTime
     * 3. 整数类型下 newVersion = oldVersion + 1
     * 4. newVersion 会回写到 entity 中
     * 5. 仅支持 updateById(id) 与 update(entity, wrapper) 方法
     * 6. 在 update(entity, wrapper) 方法下, wrapper 不能复用!!!
     * 二、乐观锁的配置只需要两步
     * 1. SpringBoot配置定义一下 乐观锁的插件Bean：OptimisticLockerInterceptor
     * 2. 对应的字段必须使用@version注解；
     */
    @Version
    private Integer updateVersion;

    private static final long serialVersionUID = 1L;


    @Override
    public String toString() {
        return "UserDo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", pwd='" + pwd + '\'' +
                ", email='" + email + '\'' +
                ", is_deleted=" + is_deleted +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", grade=" + grade +
                ", updateVersion=" + updateVersion +
                ", pwdQuestion='" + pwdQuestion + '\'' +
                ", pwdAnswer='" + pwdAnswer + '\'' +
                '}';
    }

}