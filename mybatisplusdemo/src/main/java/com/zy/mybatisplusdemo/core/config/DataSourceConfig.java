package com.zy.mybatisplusdemo.core.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.reflection.MetaClass;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Date;

/**
 * @Description: 设置数据源信息
 * @Author: zhangyu
 * @Date:Created in 11:43 2019/7/10
 */
@Configuration
public class DataSourceConfig {

    //当前环境---env.getProperty() 获取当前活动中的某个属性；
    @Autowired
    private Environment env;

    /**
     * 老的数据源：com.mysql.jdbc.Driver
     * 最新数据源：com.mysql.cj.jdbc.Driver
     */
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/amethyst_test?charset=utf-8");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("rootroot");
        return dataSource;
    }

    /**
     * 1.要把mybatis对应的SqlSessionFactoryBean  改成mybatisPlus对应的MybatisSqlSessionFactoryBean
     * 2.这里在mybatisSqlSessionFactoryBean.setDataSource(dataSource()); setDataSource的时候会报一个异常：java.lang.NoClassDefFoundError: org/springframework/jdbc/datasource/TransactionAwareDataSourceProxy； 异常原因：少一个JDBC的依赖；
     * 3.问题：自动填充功能没有生效：我们需要重写metaHandler中的方法，完成需要做的自动填充的动作；然后将实现的的方法，写到MP全局配置，并且设置到mybatisSqlSessionFactory中，才能使自动填充功能生效；
     *
     * @Author zhangyu
     * @Date 2019/7/16 9:33
     */
    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactory() throws Exception {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(dataSource());
        mybatisSqlSessionFactoryBean.setTypeAliasesPackage(env.getProperty("mybatis-plus.aliasesPackage"));
        mybatisSqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(env.getProperty("mybatis-plus.config-location")));
        mybatisSqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis-plus.mapper-locations")));

        // MP 全局配置，更多内容进入类看注释，配置 mybatisPlus自定义填充类，------并设置到mybatisSqlSessionFactoryBean中；
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setMetaObjectHandler(metaObjectHandler());
        mybatisSqlSessionFactoryBean.setGlobalConfig(globalConfig);

        return mybatisSqlSessionFactoryBean;
    }

    /**
     * 自定义填充器
     * @param
     * @Author zy
     * @Date 2019/7/17
     */
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandler() {
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
        };
    }

}
