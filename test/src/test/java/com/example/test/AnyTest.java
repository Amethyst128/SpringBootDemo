package com.example.test;

import com.example.test.domain.Student;
import com.example.test.domain.UserDo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
        a.forEach(t -> t.length());
    }

    @Test
    public void testTime() {
        // 精确到毫秒
        // 获取当前时间戳
        System.out.println(System.currentTimeMillis());
        System.out.println(Calendar.getInstance().getTimeInMillis());
        System.out.println(new Date().getTime());

        // 精确到秒
        // 获取当前时间戳
        System.out.println(System.currentTimeMillis() / 1000);
        System.out.println(Calendar.getInstance().getTimeInMillis() / 1000);
        System.out.println(new Date().getTime() / 1000);
        System.out.println(Instant.now().getEpochSecond());
    }

    @Test
    public void testDate() throws ParseException {
        Date now = new Date();
        System.out.println(now);
        String date1 = "2020/02/17 00:00:00";
        Date date11 = DateUtils.StrToDate(date1);
        String date2 = "2020-02-17 23:59:59";
        Date date22 = DateUtils.StrToDate(date2);
        System.out.println("date11=" + date11 + "  date22=" + date22);
        System.out.println(now.after(date11));
        System.out.println(now.before(date22));


        String DateStr1 = "2011-10-1 10:20:16";
        String DateStr2 = "2011-10-07 15:50:35";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateTime1 = null;
        try {
            dateTime1 = dateFormat.parse(DateStr1);
            Date dateTime2 = dateFormat.parse(DateStr2);
            int i = dateTime1.compareTo(dateTime2);
            System.out.println(dateTime1);
            System.out.println(dateTime2);
            System.out.println(i < 0);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        String x1 = "2018-06-05 10:02";
        String x2 = "2018-06-05 10:08";

        Date ddd1 = sdf.parse(x1);
        Date ddd2 = sdf.parse(x2);
        System.out.println(ddd1 + "   " + ddd2);


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
        String date = formatter.format(new Date());//格式化数据，取当前时间结果为 2014-10-30
        System.out.println(date);


    }

    @Test
    public void testRateLength() {
        String rate = "aaabbb '真的太喜欢了'，;{}:[ ]/U''$emoji";
        String str = "\uD83C\uDC02\uD83D\uDC02☀-⟿";
        String a = "公开 =15字， 应该返蘑豆";
        String b = "   公开 =15字， 应该返蘑豆   ";
        System.out.println(a.length());
        System.out.println(b.length());
        System.out.println(rate.length());
        System.out.println(str.length());
    }


    @Test
    public void testSplitString() {
        String str = "#1# #2# #3# #4#";
        str.replaceAll("#", "");

        String[] strArr = str.replaceAll("#", "").split(" ");
        List<String> path = new ArrayList<>();
        for (String s : strArr) {
            path.add(idToUrl(Integer.parseInt(s), 1));
        }
        List<String> list = Arrays.asList(strArr);
        System.out.println(path);


    }

    public static String idToUrl(long id, int version) {
        StringBuilder sb = new StringBuilder();
        if (id > 0L) {
            sb.append(version);
            sb.append((new BigInteger(String.valueOf(id * 2L + 56L), 10)).toString(36));
        }

        return sb.toString();
    }


    @Test
    public void testString() {
        String a = "#1160# #1191# #1198#";
        System.out.println(a.contains("#1160# #1191#"));
        String b = "#100001#";
        System.out.println(b.equals("#100001#"));
        System.out.println("100001" == "100001");
        System.out.println("100001".equals("100001"));

        Integer c = 100001;
        System.out.println(c.equals(100001));
        System.out.println(c.equals("100001"));
        System.out.println(c == 100001);


        System.out.println(getBoolean());
    }

    private Boolean getBoolean() {
        String d = "#1625#,#1597#";
        String str = "#3856# #1625# #4085#";

        for (String path : d.split(",")) {
            if (str.contains(path)) {
                return true;
            }
        }
        return false;
    }


    @Test
    public void test2() {
        String tags = "992,1258,1259,1260,1261,3311,3323,3324";
        System.out.println(tags.contains("992") && tags.contains("3311"));
//        System.out.println(getBoolean2());
    }

    private boolean getBoolean2() {
        String tags = "3324,3323,1261,1260";
        Integer tagId = 2000;
        if ("#10005820#".contains("#10005853#") || "#10005820#".equals("#10005820#")) { // 进口医疗器械补充认证
            if (tags.contains("1261") && (tags.equals(2000) || tags.contains("3324"))) {
                return true;
            }
            return false;
        }
        return false;
    }

    @Test
    public void testList() {
        UserDo userDo = new UserDo();
        userDo.setAge(21).setGrade(2).setSex("nv").setUserName("zhangsan");
        System.out.println(userDo);
        System.out.println(userDo.toString());
    }

    @Test
    public void testList1() {
        List<String> list1 = new ArrayList<>();
        System.out.println(list1.add("rrrrr"));
        System.out.println(new ArrayList<>().add("ddddddd"));
    }


    @Test
    public void testListAddByIndex() {
        List<String> str = new ArrayList<>();
        str.add("1");
        str.add("2");
        System.out.println(str);
        str.add(0, "0");
        System.out.println(str);
        str.add(9, "0");
        System.out.println(str);
    }

    @Test
    public void testStringSplit() {
        String a = "#3856# #1625# #4193#";
        String[] b = a.split(" ");
        for (String s : b) {
            System.out.println(s);
        }
    }

    @Test
    public void testListAdd() {
        List<String> a = new ArrayList<>();
        a.add(0, "1");
        System.out.println(a);
    }

    // 测试lamada表达式 filter
    @Test
    public void testFilter() {
        List<String> list = new ArrayList<>();
        list.add("abcy");
        list.add("abcdy");
        list.add("abefdy");
        list.add("aefay");
        System.out.println("==================" + list);
        list = list.stream().filter(t -> t.contains("abcd") && t.contains("abc")).collect(Collectors.toList());
        System.out.println("==================" + list);
    }


    @Test
    public void testNullPE() {
        UserDo userDo = new UserDo();
        user


        userDo.setListThings(null);
        if (userDo.getListThings() == null) {
            System.out.println("1");
        } else if (CollectionUtils.isEmpty(userDo.getListThings())) {
            System.out.println("2");
        } else {
            System.out.println(userDo.getListThings());
        }
    }

    @Test
    public void testMap() {
        UserDo userDo = new UserDo();
        userDo.getMapString().put("dffff", "fffff");
        System.out.println(userDo.getMapString());
        Map<String, String> aaa = userDo.getMapString();
        aaa.put("aaa", "aaa");
        aaa.put("gggg", "gggg");
        System.out.println(aaa);
    }

    @Test
    public void testBig() {
        BigDecimal payCost = BigDecimal.ZERO;
        BigDecimal itemCost = new BigDecimal(0.28990);
        payCost = payCost.add(itemCost.setScale(2, BigDecimal.ROUND_HALF_UP));
        System.out.println(payCost);
    }

    @Test
    public void testUpdateObject() {
        UserDo userDo = new UserDo();
        Student student = new Student();
        student.setAge(13);
        userDo.setStudent(student);
        log.info("userDo={}", userDo);
        student.setGrade(3).setSex("女");
        log.info("userDo={}", userDo);

        boolean a = BigDecimal.ZERO.equals(new BigDecimal(0.00));
        System.out.println(a);

    }

    @Test
    public void testReadom() {
        /**
         * 生成一个随机数
         */
        String val = "";
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            val += String.valueOf(random.nextInt(10));
        }
        System.out.println(System.currentTimeMillis() + val);

    }

    @Test
    public void testDate2() {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        int a = instance.get(Calendar.DAY_OF_MONTH);
        System.out.println(String.valueOf(System.currentTimeMillis()).concat("622103199205186025".substring(10, 14)));
    }

    @Test
    public void testRemoveList() {
        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        List<String> list = list1.stream().filter(t->t.equals("3")).collect(Collectors.toList());
        list1.removeAll(list);
    }

}
