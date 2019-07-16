package com.zy.mybatisplusdemo.core;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

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

        // MP 全局配置，更多内容进入类看注释，配置 mybatisPlus自定义填充类
        GlobalConfiguration globalConfiguration = new GlobalConfiguration();
        globalConfiguration.setMetaObjectHandler(new MetaObjectHandlerConfig());

        return mybatisSqlSessionFactoryBean;
    }
}
