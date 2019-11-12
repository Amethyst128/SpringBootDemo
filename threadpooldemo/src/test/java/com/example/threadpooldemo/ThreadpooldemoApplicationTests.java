package com.example.threadpooldemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;


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
     * @param
     * @desc 异步执行实现方式一： 通过定义的线程池，直接调用execute方法 进行异步执行
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
     * @param
     * @desc test04和test05为异步执行且非同步，
     * - test04 为自定义线程池执行方式；（[custom-thread-1] 标识 是以“custom-thread”开头的线程池）
     * - test05 为默认java线程池执行方式
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


    //-----------------------------------------------------------------------
    // 完成需求：主线程等待 所有的子线程执行完毕，再执行

    /**
     * 实现方式有三种： join()及 CountDownLatch 和 CyclicBarrier
     * - join() thread.join()把指定的线程加入到当前线程，将两个交替执行的线程 合并为顺序执行的线程
     * - CountDownLatch 并发工具类
     * - CyclicBarrier  一个同步辅助类，用于协调多个子线程，让多个子线程在这个屏障前等待，直到所有子线程都到达了这个屏障时，再一起继续执行后面的动作。 和CountDownLatch区别是：CyclicBarrier设置一个信号值，最终再提交一次，而CountDownLatch 是每次子线程执行完成，都对信号量进行改变，并提高信号量的值给主线程；
     */

    /**
     * @desc CountDownLatch实现
     */
    @Test
    public void countDownLatchTest() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            xTaskExecutor.execute(() -> {
                log.info("xTaskExecutor-" + finalI + "开始执行...");
                //子线程执行完毕，计数减1；
                countDownLatch.countDown();
                log.info("xTaskExecutor-" + finalI + "执行结束...");
            });
        }
        log.info("主线程等待子线程...");
        countDownLatch.await();
        log.info("主线程执行完毕...");
    }

    /**
     * @desc todo : 这个的测试有点问题。
     */
    @Test
    public void cyclicBarriesTest() throws BrokenBarrierException, InterruptedException {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        for (int i = 0; i < 4; i++) {
            int finalI = i;
            xTaskExecutor.execute(() -> {
                log.info("xTaskExecutor-" + finalI + "开始执行...");
                try {
                    log.info("cyclicBarrier = {}", cyclicBarrier.getNumberWaiting());
                    log.info("xTaskExecutor-" + finalI + "执行结束...");
                    //子线程执行完，设置一个屏障，当多个线程都到达这个屏障，才进行之后的代码执行；
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        log.info("主线程等待子线程...");
        cyclicBarrier.await();
        log.info("主线程执行完毕...");
    }


}
