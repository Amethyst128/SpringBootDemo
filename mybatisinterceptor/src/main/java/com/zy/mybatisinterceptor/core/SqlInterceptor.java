package com.zy.mybatisinterceptor.core;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import java.lang.reflect.Field;
import java.util.Date;
import java.util.Properties;


/**
 * @Description: 拦截器核心代码，拦截重写crateTime和updateTime
 * @Author: zhangyu
 * @Date:Created in 9:37 2019/7/10
 */
@Slf4j
@Component
//@Intercepts({@Signature(type = Executor.class, method = "prepare", args = {MappedStatement.class, Object.class})})
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class SqlInterceptor implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(SqlInterceptor.class);


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        //获取sql命令
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();

        logger.info("获取的sql命令为：" + sqlCommandType);

        //获取参数
        Object parameter = invocation.getArgs()[1];
        if (null != parameter) {
            //获取成员变量
            Field[] declaredFields = parameter.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                if (field.getAnnotation(Time.CreateTime.class) != null) {
                    //insert语句，插入createTime
                    if (SqlCommandType.INSERT.equals(sqlCommandType)) {
                        field.setAccessible(true);
                        if (field.get(parameter) == null) {
                            field.set(parameter, new Date());
                        }
                    }
                }

                if (field.getAnnotation(Time.UpdateTime.class) != null) {
                    //insert或者update语句，插入updateTime
                    if (SqlCommandType.INSERT.equals(sqlCommandType) || SqlCommandType.UPDATE.equals(sqlCommandType)) {
                        field.setAccessible(true);
                        if (field.get(parameter) == null) {
                            field.set(parameter, new Date());
                        }
                    }
                }
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        if (o instanceof StatementHandler) {
            return Plugin.wrap(o, this);
        }
        return o;
    }

    @Override
    public void setProperties(Properties properties) {
    }

}
