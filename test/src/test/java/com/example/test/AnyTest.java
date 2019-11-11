package com.example.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Desc:
 * @Author: zy
 * @Date:Created in 2019/10/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AnyTest {


    @Test
    public void testa() {
        String pattern = "\\d*";
        String str = "22222222222222222222222";
        System.out.println(str.length() == 23);
        // 包含字母
        if (!Pattern.matches(pattern, str) || str.length() != 23) {
            System.out.println("aaaaaaaaaaaaaaa");
        }
        // 长度不是23
        if (str.length() != 23) {
            System.out.println("bbb");
        }
    }

    @Test
    public void testStr() {
        String a = "aaaaa/bbbbb:ccccc";
        String b = a.split("/")[0];
        String c = a.split(":")[0];
        System.out.println("b=" + b + "c=" + c);
    }

    @Test
    public void testLongValue() {
        String successObject = null;
        Long successCount = Long.valueOf(successObject);
    }


    @Test
    public void testNPE() {
        List<String> a = new ArrayList<>();
        a.forEach(t->t.length());
    }


}
