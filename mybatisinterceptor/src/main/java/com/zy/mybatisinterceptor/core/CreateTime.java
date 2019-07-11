package com.zy.mybatisinterceptor.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: createTime自定义注解
 * @Author: zhangyu
 * @Date:Created in 16:48 2019/7/11
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface CreateTime {
    String value() default "";
}
