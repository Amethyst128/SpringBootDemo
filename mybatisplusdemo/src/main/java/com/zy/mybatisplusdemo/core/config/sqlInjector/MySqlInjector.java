package com.zy.mybatisplusdemo.core.config.sqlInjector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Desc: sql注入器
 * @Author: zy
 * @Date:Created in 2019/7/23
 */
@Component
public class MySqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
//        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
//        //将自定义的注入sql的方法，add写入DefaultSqlInjector--sql注入器中
//        methodList.add(new SelectListBackEnd());
//        return methodList;
        return Stream.of(new SelectListBackEnd()).collect(Collectors.toList());
    }

}
