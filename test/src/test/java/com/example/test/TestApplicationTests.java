package com.example.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

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
        if ("aaa".equals(a)){
            System.out.println("equals空");
        }
    }

}
