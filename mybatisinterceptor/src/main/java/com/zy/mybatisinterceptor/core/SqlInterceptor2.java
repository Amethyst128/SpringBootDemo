
package com.zy.mybatisinterceptor.core;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Properties;


/**
 * @Description: 拦截器核心代码，拦截重写crateTime和updateTime
 * @Author: zhangyu
 * @Date:Created in 9:37 2019/7/10
 */
@Deprecated
@Slf4j
//@Component
//@Intercepts({@Signature(type = Executor.class, method = "prepare", args = {MappedStatement.class, Object.class})})
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class SqlInterceptor2 implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(SqlInterceptor2.class);

    private final String INSERT = "insert";
    private final String UPDATE = "update";
    private final String DELETE = "delete";
    private final String SELECT = "select";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //sql操作类型
        String operateType = getOperateType(invocation);
        String sql = getSqlByInvocation(invocation);

        //重新组装sql
        if (null != operateType) {
            if (operateType.equals(INSERT)) {

            } else if (operateType.equals(UPDATE)) {

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

    //获取sql的操作类型
    private String getOperateType(Invocation invocation) {
        final Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        SqlCommandType commondType = ms.getSqlCommandType();
        if (commondType.compareTo(SqlCommandType.SELECT) == 0) {
            return SELECT;
        }
        if (commondType.compareTo(SqlCommandType.INSERT) == 0) {
            return INSERT;
        }
        if (commondType.compareTo(SqlCommandType.UPDATE) == 0) {
            return UPDATE;
        }
        if (commondType.compareTo(SqlCommandType.DELETE) == 0) {
            return DELETE;
        }
        return null;
    }

    //定义一个内部辅助类，包装sql
    class BoundSqlSource implements SqlSource {
        private BoundSql boundSql;

        public BoundSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        @Override
        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }

    //获取sql语句
    private String getSqlByInvocation(Invocation invocation) {
        final Object[] args = invocation.getArgs();
        //MappedStatement对象
        MappedStatement ms = (MappedStatement) args[0];
        //获取存有boundSql的parameterObject对象
        Object parameterObject = args[1];
        BoundSql boundSql = ms.getBoundSql(parameterObject);
        return boundSql.getSql();
    }


}
