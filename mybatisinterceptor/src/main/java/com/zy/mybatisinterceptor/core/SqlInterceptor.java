package com.zy.mybatisinterceptor.core;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
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
@Component
@Slf4j
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
//        @Signature(type = Executor.class, method = "query", args = {Statement.class, ResultHandler.class})
})
public class SqlInterceptor implements Interceptor {
//
//    /**
//     * org.apache.ibatis.executor.Executor;
//     * 是 Mybatis 的内部执行器，它负责调用 StatementHandler 操作数据库，并把结果集通过 ResultSetHandler 进行自动映射，另外，它还处理了二级缓存的操作。
//     * Executor创建StatementHandler对象,
//     * 同时,创建ParameterHandler和ResultSetHandler对象,而ParameterHandler和ResultSetHandler都依赖TypeHandler;
//     */
//    Executor;
//    /**
//     * org.apache.ibatis.executor.statement.StatementHandler;
//     * 是 Mybatis 直接和数据库执行 sql 脚本的对象，另外，它也实现了 Mybatis 的一级缓存。
//     */
//    StatementHandler;
//    /**
//     * org.apache.ibatis.executor.parameter.ParameterHandler;
//     *  是 Mybatis 实现 sql 入参设置的对象。
//     */
//    ParameterHandler;
//    /**
//     * org.apache.ibatis.executor.resultset.ResultSetHandler;
//     * 是 Mybatis 把 ResultSet 集合映射成 POJO 的接口对象。处理查询结果集;
//     */
//    ResultSetHandler;



    private static final Logger logger = LoggerFactory.getLogger(SqlInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        //获取sql命令操作类型
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        logger.info("获取的sql命令为：" + sqlCommandType);

        //获取参数
        Object parameter = invocation.getArgs()[1];
        if (null != parameter) {
            //获取成员变量
            Field[] declaredFields = parameter.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                if (field.getAnnotation(CreateTime.class) != null) {
                    //insert语句，插入createTime
                    if (SqlCommandType.INSERT.equals(sqlCommandType)) {
                        field.setAccessible(true);
                        field.set(parameter, new Date());
                    }
                }
                if (field.getAnnotation(UpdateTime.class) != null) {
                    //insert或者update语句，插入updateTime
                    if (SqlCommandType.INSERT.equals(sqlCommandType) || SqlCommandType.UPDATE.equals(sqlCommandType)) {
                        field.setAccessible(true);
                        field.set(parameter, new Date());
                    }
                }
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

}
