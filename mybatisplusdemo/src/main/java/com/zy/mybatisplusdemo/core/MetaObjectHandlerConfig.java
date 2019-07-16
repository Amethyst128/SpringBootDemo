package com.zy.mybatisplusdemo.core;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description: 配置公共字段的自动补充功能，@TableField(..fill = FieldFill.INSERT)
 * # 特别注意，3.0-gamma之前的版本 MetaObjectHandler 是抽象类,3.0-RC之后的版本MetaObjectHandler 是接口
 * # 特别注意2：@TableField(..fill = FieldFill.INSERT)中的自动填充字段有四种配置，DEFAULT,INSERT,UPDATE,INSER_UPDATE,默认是DEFAULT
 * @Author: zhangyu
 * @Date:Created in 11:18 2019/7/16
 */
@Component
public class MetaObjectHandlerConfig extends MetaObjectHandler {

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
