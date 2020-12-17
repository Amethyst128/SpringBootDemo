package com.example.test;

import com.example.test.domain.UserDo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
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


    /**
     * @desc 测试 怎么样会报空指针异常
     * <p>
     * 总结：
     * 1. null使用等号 不会抛异常；
     * 2. null使用equals时，使用null调用equals会报空指针异常
     * 延伸：
     * - ==和equals都是比较对象的存放地址，如果equals没有被重写的话；
     * - == 用于基本数据类型比较的时候，比较的是基本类型的值；    用于对象之间比较的时候，比较的是内存存放的地址；
     * - equals 用在对象的比较，被重写后，一般用来比较对象的值；
     */
    @Test
    public void testNullPoint() {
        String a = null;
        if (a == null) {
            System.out.println("右null");

            if (null == a) {
                System.out.println("左null");
            }
            if (a.equals("aaa")) {
                System.out.println("空equals");  //nullPointException
            }
            if ("aaa".equals(a)) {
                System.out.println("equals空");
            }
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

    /**
     * 测试java的反射机制
     * -四种获取Class对象的方法 Java反射机制
     */
    @Test
    public void ClassCheck() {
        try {
            System.out.println("通过类本身获得class对象");
            Class userClass = this.getClass();
            System.out.println(userClass.getName());
            System.out.println("===========");

            System.out.println("通过子类的实例获得父类class对象");
            UserDo useInfo = new UserDo();
            userClass = useInfo.getClass();
            System.out.println(userClass.getName());
            Class subUserClass = userClass.getSuperclass();
            System.out.println(subUserClass.getName());
            System.out.println("===========");

            System.out.println("通过类名.class获取class对象");
            Class forClass = UserDo.class;
            System.out.println(forClass.getName());
            System.out.println("===========");

            System.out.println("通过类名的字符串获取class对象");
            Class forName = Class.forName("com.hqh.reflect.UseInfo");
            System.out.println(forName.getName());
            System.out.println("=============");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testfinally() {
        System.out.println(testFinally());
    }


    /**
     * - 验证 finally中操作变量，是否能够生效；（不会生效，sout a=4;
     * - 验证 finally走完之后，return的是上面的a还是下面的；( 上面的a；   当执行到第一个return a的时候，会先去执行fanilly中的代码，再执行return；
     */
    public Integer testFinally() {
        int a = 3;
        try {
            a = 4;
            System.out.println(a);
            return a;
        } catch (Exception e) {
            log.info("exception");
        } finally {
            a = 5;
            System.out.println("finally+" + a);
        }
        return a;
    }


    //-------------------------测试时间转换------------------------------------

    @Test
    public void testDateUtils() {
        String time = "1570524844858";
        Date date = numberDateFormatToDate(time);
        System.out.println(date);
    }

    private static final String IOS_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static Date numberDateFormatToDate(String timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat(IOS_DATE_FORMAT);
        Date date = null;
        try {
            if (timestamp.length() == 13) {
                date = sdf.parse(sdf.format(Long.parseLong(timestamp)));
            } else {
                date = sdf.parse(sdf.format(Long.parseLong(timestamp) * 1000));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    //------------------------------------------------------------


    @Test
    public void testMapPut() {

    }

}
