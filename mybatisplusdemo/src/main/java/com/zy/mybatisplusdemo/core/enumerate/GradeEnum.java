package com.zy.mybatisplusdemo.core.enumerate;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * @Description: 年级枚举类
 * @Author: zhangyu
 * @Date:Created in 10:07 2019/7/17
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
//public enum GradeEnum implements IEnum<Integer> {
public enum GradeEnum {


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

    @EnumValue //标记数据库存的值是code
    private final int code;
    private String desc;

//    @Override
//    public Integer getValue() {
//        return this.code;
//    }
}
