package com.example.threadpooldemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootApplication
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ThreadpooldemoApplicationTests {

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;
    @Autowired
    private ThreadPoolTaskExecutor xTaskExecutor;
    @Autowired
    private AsyncTaskTest asyncTaskTest;


    /**
     * @desc 异步执行实现方式一： 通过定义的线程池，直接调用execute方法 进行异步执行
     * @param
     * @Author zy
     * @Date 2019/11/12
     */
    @Test
    public void test01() {
        log.info("进入test01方法");
        taskExecutor.execute(() -> {
            log.info("执行异步代码块");
            log.info("异步代码块执行完成");
        });
        log.info("在执行异步代码块之前结束");
    }

    @Test
    public void test02() {
        log.info("进入test02方法");
        xTaskExecutor.execute(() -> {
            log.info("执行异步代码块");
            log.info("异步代码块执行完成");
        });
        log.info("在执行异步代码块之前结束");
    }

    /**
     * @desc test04和test05为异步执行且非同步，
     * - test04 为自定义线程池执行方式；（[custom-thread-1] 标识 是以“custom-thread”开头的线程池）
     * - test05 为默认java线程池执行方式
     * @param
     * @Author zy
     * @Date 2019/11/12
     */
    @Test
    public void test03() throws InterruptedException {
        log.info("进入test03方法");
        asyncTaskTest.test04();
        asyncTaskTest.test05();
        Thread.sleep(10);
        log.info("test03-在执行异步代码块之前结束");
    }

}
