package com.example.test;

import com.example.test.domain.UserDo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void mul() {
        BigDecimal a = BigDecimal.valueOf(0.02);
        BigDecimal b = BigDecimal.valueOf(2);
        BigDecimal c = a.multiply(b);
        System.out.println(c);

        BigDecimal d = new BigDecimal(0.02);
        BigDecimal e = new BigDecimal("0.02");
        System.out.println(e + ":" + d);
    }

    @Test
    public void testNullPoint() {
        String a = null;
        if (a == null) {
            System.out.println("右null");
        }
        if (null == a) {
            System.out.println("左null");
        }
//        if (a.equals("aaa")){
//            System.out.println("空equals");
//        }
        if ("aaa".equals(a)) {
            System.out.println("equals空");
        }
    }

    @Test
    public void testMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("ameth", 1);
        map.put("ameth", 2);
        map.put("ameth", 3);
        map.put("ameth", 4);
        System.out.println(map.get("ameth"));
    }

    /**
     * @param
     * @desc 测试流操作和直接foreach的执行效率
     * @Author zy
     * @Date 2019/9/29
     */
    @Test
    public void testSpead() {
        UserDo user = new UserDo().setUserName("zhangsan").setAge(13);
        List<String> testList = new ArrayList<>();
        testList.add("_simulate_flag_1");
        testList.add("_simulate_flag_2");
        testList.add("_simulate_flag_3");
        testList.add("4");

        long a = System.currentTimeMillis();
        testList.forEach(t -> {
            if (t.startsWith("_simulate_flag_")) {
                return;
            }
            user.setUserName("_simulate_flag_" + t);
        });
        System.out.println("\r<br> a执行耗时 : " + (System.currentTimeMillis() - a) / 1000f + " 秒 ");
        long b = System.currentTimeMillis();
        testList.stream().map(t -> {
            if (t.startsWith("_simulate_flag_")) {
                return t;
            }
            user.setUserName("_simulate_flag_" + t);
            return t;
        });
        System.out.println("\r<br> b执行耗时 : " + (System.currentTimeMillis() - b) / 1000f + " 秒 ");
    }

}
