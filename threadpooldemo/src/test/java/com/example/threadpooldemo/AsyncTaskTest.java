package com.example.threadpooldemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Desc:
 * @Author: zy
 * @Date:Created in 2019/7/22
 */
@Component
@Slf4j
public class AsyncTaskTest {

    /**
     * @desc 指定 自定义线程池异步执行
     */
    @Async("xTaskExecutor")
    public void test04() {
        log.info("进入test04方法...");
        log.info("test04-在执行异步代码块之前结束。");
    }

    /**
     * @desc 不指定 执行的线程池，使用默认的线程池
     * - 默认线程池的前缀为 threadPool
     */
    @Async
    public void test05() {
        log.info("进入test05方法...");
        log.info("test05-在执行异步代码块之前结束。");
    }

}
