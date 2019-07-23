package com.example.threadpooldemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadpooldemoApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(ThreadpooldemoApplicationTests.class);

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;
    @Autowired
    private ThreadPoolTaskExecutor xtaskExecutor;
    @Autowired
    private AsyncTaskTest asyncTaskTest;


    @Test
    public void contextLoads() {
    }


    @Test
    public void test01() {
        logger.info("进入test01方法");
        taskExecutor.execute(() -> {
            logger.info("执行异步代码块");
            logger.info("异步代码块执行完成");
        });
        logger.info("在执行异步代码块之前结束");
    }

    @Test
    public void test02() {
        logger.info("进入test02方法");
        xtaskExecutor.execute(() -> {
            logger.info("执行异步代码块");
            logger.info("异步代码块执行完成");
        });
        logger.info("在执行异步代码块之前结束");
    }

    @Test
    public void test03() {
        logger.info("进入test03方法");
        asyncTaskTest.test04();
        asyncTaskTest.test05();
        logger.info("test03-在执行异步代码块之前结束");
    }


}
