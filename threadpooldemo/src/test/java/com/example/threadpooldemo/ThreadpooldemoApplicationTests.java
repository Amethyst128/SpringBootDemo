package com.example.threadpooldemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.*;


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
    public void test02() throws InterruptedException {
        log.info("进入test02方法");
        xTaskExecutor.execute(() -> {
            log.info("执行异步代码块");
            log.info("异步代码块执行完成");
        });
        Thread.sleep(10);
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
        Thread.sleep(20);
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
     * @desc CyclicBarrier 实现
     * todo : 这个的测试有点问题。
     * 问题已解决：
     * 问题定位为：死锁问题
     * 这里的问题点是：CyclicBarrier的原理是设置一个等待屏障，只有当所有线程都达到这个屏障（即都执行完成），才会继续往下执行。也就是说，这里线程的执行是不会释放当前占用的线程资源的；
     * 解决方式：如果是使用线程池的同时使用CyclicBarrier的话，则线程池中初始化的线程数量一定要大于屏障CyclicBarrier所设置的大小；否则死锁；
     */
    @Test
    public void cyclicBarrierTest() throws BrokenBarrierException, InterruptedException {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        for (int i = 0; i < 5; i++) {
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
        //todo 两次调用await()的意义？？？
        cyclicBarrier.await();
        log.info("主线程执行完毕...");
    }

    @Test
    public void cyclicBarrierTest2() {
        System.out.println("运动员准备进场，全场欢呼............");

        // 指定必须有 6 个运动员到达才行
        CyclicBarrier barrier = new CyclicBarrier(6, () -> {
            System.out.println("所有运动员入场，裁判员一声令下！！！！！");
        });

        ExecutorService service = Executors.newFixedThreadPool(6);
        for (int i = 0; i < 6; i++) {
            service.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " 运动员，进场");
                    // 等到所有的线程都到达指定的临界点
                    barrier.await();
                    System.out.println(Thread.currentThread().getName() + "  运动员出发");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        log.info("运动员全部到达，可以开始比赛啦...");
    }


    /**
     * @desc join()实现
     * - thread.join()把指定的线程加入到当前线程，将两个交替执行的线程 合并为顺序执行的线程
     * @Author zy
     * @Date 2019/11/12
     */
    @Test
    public void joinTest() {
        WorkThread thread1 = new WorkThread();
        WorkThread thread2 = new WorkThread();
        thread1.start();
        thread2.start();
        //阻塞Main线程，执行子线程workThread1和workThread2，完毕后继续执行后续的逻辑
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("主线程等待子线程...");
        log.info("主线程执行完毕...");
    }
    public class WorkThread extends Thread {
        @Override
        public void run() {
            log.info(getName() + "开始执行");
            log.info(getName() + "执行完成");
        }
    }

}
