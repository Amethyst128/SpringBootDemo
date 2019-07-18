package com.zy.mybatisplusdemo.core.enumerate;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * @Description: 年级枚举类
 * @Author: zhangyu
 * @Date:Created in 10:07 2019/7/17
 * <p>
 * 这里的枚举定义有三种形式：
 * 1. IENUM<>接口的通用处理类：通用枚举注入  implements IEnum<Integer>，重写其中的getValue（）方法；
 * 2. 原生枚举（带{@com.baomidou.mybatisplus.annotation.EnumValue}):  将要存入数据库的字段使用@EnumValue注释；
 * 3. 原生枚举： 默认使用枚举值顺序： 0：MALE， 1：FEMALE
 */


//public enum GradeEnum {
//
//
//    PRIMARY(1, "小学"), SECONDORY(2, "中学"), HIGH(3, "高中");
//
//    GradeEnum(int code, String desc) {
//        this.code = code;
//        this.desc = desc;
//    }
//
//    public int getCode() {
//        return code;
//    }
//
//    public String getDesc() {
//        return desc;
//    }
//
//    @EnumValue //标记数据库存的值是code
//    private final int code;
//    private String desc;
//
//
//}

public enum GradeEnum implements IEnum<Integer> {

    PRIMARY(1, "小学"), SECONDORY(2, "中学"), HIGH(3, "高中");

    GradeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private final int code;
    private String desc;

    @Override
    public Integer getValue() {
        return this.code;
    }

}
