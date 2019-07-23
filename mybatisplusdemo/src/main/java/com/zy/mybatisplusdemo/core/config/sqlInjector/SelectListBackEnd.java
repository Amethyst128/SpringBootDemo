package com.zy.mybatisplusdemo.core.config.sqlInjector;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 自定义sql注入器 实现步骤：
 * - 1. 继承AbstractMethod实现injectMappedStatement()，实现sql自定义注入方式;
 * - 2. 自定义一个sql注入器MySqlInjector，继承DefaultSqlInjector类，实现其中的getMethodList(),将自定义的注入方法，add到methodList中；
 * - 3. 将自定义的注解器，配置到MybatisSqlSessionFactory中的 globalConfig参数中的 sqlInject中；
 *
 * @Desc: 自定义sql注入的某个方法
 * @Author: zy
 * @Date:Created in 2019/7/23
 */
public class SelectListBackEnd extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod sqlMethod = SqlMethod.SELECT_LIST;
        String sql = String.format(sqlMethod.getSql(), this.sqlSelectColumns(tableInfo, true), tableInfo.getTableName(), this.sqlWhereEntityWrapper(true, tableInfo), this.sqlComment());

        //填写sql注入的部分，拼装sql
        StringBuilder sb = new StringBuilder(sql);
        sb.insert(sql.indexOf("</where>"), "and name = zhangsan\n");
        sql = sb.toString();

        SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, sql, modelClass);
        return this.addSelectMappedStatementForTable(mapperClass, sqlMethod.getMethod(), sqlSource, tableInfo);
    }

}
