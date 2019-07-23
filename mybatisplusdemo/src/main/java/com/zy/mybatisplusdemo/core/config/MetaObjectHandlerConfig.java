package com.zy.mybatisplusdemo.core.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description: 配置公共字段的自动补充功能，@TableField(..fill = FieldFill.INSERT)
 * # 特别注意，3.0-gamma之前的版本 MetaObjectHandler 是抽象类,3.0-RC之后的版本MetaObjectHandler 是接口
 * # 特别注意2：@TableField(..fill = FieldFill.INSERT)中的自动填充字段有四种配置，DEFAULT,INSERT,UPDATE,INSER_UPDATE,默认是DEFAULT
 * <p>
 * <p>
 * 说明1：  依赖：mybatis-plus-boot-starter  3.1.2
 * 这个类不再维护，当使用SpringBoot-mybatisPlus全家桶的时候，单独手动去设置gloableConfig，
 * 其中public GlobalConfig setMetaObjectHandler(final MetaObjectHandler metaObjectHandler) {},参数类型必须是当前类型的final值，所以不能通过单独定义一个实现MetaObjectHandler的class，来set到GlobalConfig中；   可以通过单独定义一个bean的形式，直接设置进；
 * <p>
 * <p>
 * 说明2：  依赖：mybatisplus-spring-boot-starter 1.0.5
 * 通过自定义一个实现MetaObjectHandler的class来实现GlobalConfig的，可以实现的方式就是  依赖使用的是：mybatisplus-spring-boot-starter
 * @Author zy
 * @Date 2019/7/17
 */
@Deprecated
@Component
public class MetaObjectHandlerConfig implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Object createTime = getFieldValByName("createTime", metaObject);
        Object updateTime = getFieldValByName("updateTime", metaObject);
        if (createTime == null) {
            setFieldValByName("createTime", new Date(), metaObject);
        }
        if (updateTime == null) {
            setFieldValByName("updateTime", new Date(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object updateTime = getFieldValByName("updateTime", metaObject);
        if (updateTime == null) {
            setFieldValByName("updateTime", new Date(), metaObject);
        }
    }

}
