package com.zy.mybatisplusdemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.mybatisplusdemo.core.enumerate.GradeEnum;
import com.zy.mybatisplusdemo.dao.mapper.UserDoMapper;
import com.zy.mybatisplusdemo.dao.model.UserDo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisplusdemoApplicationTests {

    @Resource
    private UserDoMapper userDoMapper;

    @Test
    public void contextLoads() {
    }


    //测试insert方法，以及自动填充功能
    //测试insert is_deleted的值
    //测试insert枚举类属性
    @Test
    public void createUser() {
        UserDo user = new UserDo();
        user.setAddress("浙江杭州")
                .setAge(24)
                .setPwd("456789")
                .setPwdQuestion("年份")
                .setPwdAnswer("2018")
                .setPhoneNumber("13888888888")
                .setSex("男")
                .setUserName("zhangsan")
                .setGrade(GradeEnum.HIGH);
        System.out.println("开始插入用户信息");
        int result = userDoMapper.insert(user);
        if (result > 0) {
            System.out.println("插入用户成功");
        } else {
            System.out.println("插入用户失败");
        }
    }

    //条件查询<放在map中>
    @Test
    public void selectUserByName() {
        Map map = new HashMap();
        //这里的map中的字段名需要和数据库表中的一致，不支持驼峰转换
        map.put("user_name", "Amethyst");
        List<UserDo> userList = userDoMapper.selectByMap(map);
        System.out.println(userList);
    }

    /**
     * 乐观锁插件没有生效
     *
     * @param
     * @Author zy
     * @Date 2019/7/17
     */
    @Test
    public void updateUser() {
        UserDo user = new UserDo();
        user.setId(30).setAddress("上海")
                .setAge(24)
                .setPwd("789654")
                .setPwdQuestion("年份")
                .setPwdAnswer("2017")
                .setPhoneNumber("13888888888")
                .setSex("男")
                .setUserName("Amethyst")
                .setUpdateVersion(1);

        try {
            System.out.println("开始休眠");
            // 可以延迟10000s ,手动去修改数据库这条数据的version 为其他数字 ，最后的结果就是无法更新成功
            Thread.sleep(10000);
            // Thread.sleep(0);
            System.out.println("休眠结束");
            int result = userDoMapper.updateById(user); // 3.根据ID修改这条数据（mybatis plus 的内层帮我们多加了一个where条件，保证乐观锁的实现） /
            if (result > 0) {
                System.out.println("修改用户成功");
            } else {
                System.out.println("修改用户失败");
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * pageNumber 从第几页开始加载
     * pageSize 每页加载多少条
     *
     * @Date 2019/7/16
     */
    @Test
    public void getUserListByPage() {
        int pageSize = 10;
        int pageNumber = 0;
        Page<UserDo> page = new Page<>(pageNumber, pageSize);
        QueryWrapper<UserDo> entityWrapper = new QueryWrapper<>();
        entityWrapper.eq("user_name", "zhangsan");
        IPage<UserDo> userDoList = userDoMapper.selectPage(page, entityWrapper);
        System.out.println(userDoList);
    }

    //========================================================================================


}
