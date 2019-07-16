package com.zy.mybatisinterceptor.core;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Description: mybatis 动态sql拦截器
 * @Author: zhangyu
 * @Date:Created in 16:42 2019/7/9
 */
@Deprecated
//@Component
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
@Slf4j
public class MybatisInterceptor implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(SqlInterceptor.class);

    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private static final ReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();


    @Override
    public Object intercept(Invocation invocation) {
        try {
            StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
            MetaObject metaobject = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
            Object paramObject = metaobject.getValue("delegate.boundSql.parameterObject");
            if (paramObject instanceof Map) {
                HashMap paramMap = (HashMap) paramObject;
                if (paramMap.get("count") != null && paramMap.get("count").toString().equalsIgnoreCase("sum")) {
                    String sql = (String) metaobject.getValue("delegate.boundSql.sql");
                    sql = "select count(1) count from (" + sql + ")";
                    metaobject.setValue("delegate.boundSql.sql", sql);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("sql error");
        }
        return null;
    }

    @Override
    public Object plugin(Object o) {
        if (o instanceof StatementHandler){
            return Plugin.wrap(o,this);
        }
        return o;
    }

    @Override
    public void setProperties(Properties properties) {

    }

}
