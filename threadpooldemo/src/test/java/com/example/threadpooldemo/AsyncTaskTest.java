package com.example.threadpooldemo;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Desc:
 * @Author: zy
 * @Date:Created in 2019/7/22
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AsyncTaskTest {

    private static final Logger logger = LoggerFactory.getLogger(AsyncTaskTest.class);

    @Async("xTaskExecutor")
    public void test04() {
        logger.info("进入test04方法...");
        logger.info("test04-在执行异步代码块之前结束。");
    }


    @Async
    public void test05() {
        logger.info("进入test05方法...");
        logger.info("test05-在执行异步代码块之前结束。");
    }

}
