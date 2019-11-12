package com.example.threadpooldemo.core.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * @Desc: 线程池 配置
 * <p>
 * 线程池执行线程的顺序：
 * 1. 当线程数小于核心线程数，则创建线程；
 * 2. 当线程数大于核心线程数，且任务队列未满时，将任务放入任务队列；
 * 3. 当线程数大于核心线程数，且任务队列已满的时：
 * -- 若线程数小于最大线程数，则创建线程；
 * -- 若线程数等于最大线程数，则执行拒绝策略；
 * <p>
 * -@EnableAsync注解：
 * -- springboot中的启动类中需要添加注解@EnableAsync来开启异步调用，
 * -- 在需要异步执行的方法上添加@Async("taskExecutor")注解进行标注;
 * -- (带有返回值的Future<T>，需要用到AsyncResult)
 * <p>
 * 线程池：Executor--ExecutorService---默认的实现类ThreadPoolExecutor(线程池执行者)
 * -- ThreadPoolExecutor总共有四个构造实现；
 * <p>
 * 继续研究：https://www.jianshu.com/p/50fffbf21b39
 * <p>
 * @Author: zy
 * @Date:Created in 2019/7/22
 */
@EnableAsync
@Configuration
@Slf4j
public class ThreadPoolConfig {

    @Bean("taskExecutor")
    public Executor taskExecutor() {
        log.info("configuration taskExecutor---");

        var executor = new ThreadPoolTaskExecutor();
        /*
         * 核心线程数
         * - 核心线程会一直存活，即使在没有执行线程的时候
         * - 设置allowCoreThreadTimeout = true(默认是false)，核心线程会在超时没有执行线程时，关闭；
         */
        executor.setCorePoolSize(5);
        /*
         * 最大线程数
         * - 当线程数>=corePoolSize时，且任务队列已满时，线程池会优先创建新线程处理；
         * - 当线程数=maxPoolSize时，且任务队列已满时，使用拒绝策略；
         */
        executor.setMaxPoolSize(10);
        /*
         *  阻塞队列的容量
         *  - 当线程数=corePoolSize时，新任务会放在队列中排队 等待执行；
         */
        executor.setQueueCapacity(20);
        /*
         *  线程空闲允许存活的时间
         *  - 当线程空闲数量达到krrpAliveSeconds时，线程会退出，知道线程数量=corePoolSize；
         *  - 如果设置了allowCorePoolThreadTimeout=true，则会直接线程退出到 线程数量=0；
         */
        executor.setKeepAliveSeconds(60);
        /*
         *  是否允许核心线程超时   默认为false--不回收核心线程
         *  - 设置为true，则线程池会回收核心线程；
         */
        executor.setAllowCoreThreadTimeOut(false);
        /*
         * 配置线程池中线程的名称前缀
         */
        executor.setThreadNamePrefix("threadpool");
        /*
         * 拒绝策略
         * 两种情况下会执行拒绝策略：
         * - 当线程数量=maxPoolSize，且阻塞队列已满时；
         * - 当线程池被调用shutdown()后，会等待线程池里的任务执行完毕，再shutdown；
         * - 如果在调用shutdown()和线程池真正shutdown之间提交任务，会拒绝新任务；
         *
         * 线程池会调用rejectedExecutionHandler来处理这个任务。如果没有默认是AbortPolicy，即直接抛异常，不执行任务；
         *
         * ThreadPoolExecutor类有几个内部实现类来处理这类的情况：
         * - AbortPolicy 丢弃任务并抛出RejectedExecutionException
         * - CallerRunsPolicy 只要线程池未关闭，该策略直接在调用者线程中，运行当前被丢弃的任务;
         *    显然这样做不会真的丢弃任务，但是，任务提交线程的性能极有可能会急剧下降;
         * - DiscardPolicy 直接丢弃任务，不做任何处理;
         * - DiscardOldestPolicy 丢弃队列中最老的一个请求，也就是即将被执行的一个任务，并尝试再次提交当前任务;
         *
         * 实现RejectedExecutionHandler接口，可自定义处理器
         */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        /*
         * 设置  等待所有任务执行完成后关闭线程
         */
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        return executor;
    }

    //----------------------------------------------------------------------

    /**
     * @desc 线程池thread.pool  - xTaskExecutor 加了代码配置和配置文件配置
     * - 如果配置文件对某个属性重配置，则Spring在处理的时候，加载了配置文件中的属性配置，并覆盖了代码中的属性；
     * @Author zy
     * @Date 2019/11/12
     */
    @Data
    @Configuration
    @ConfigurationProperties(prefix = "thread.pool")
    public static class ThreadPoolProperties {
        private Integer corePoolSize = 5;
        private Integer maxPoolSize = 5;
        private Integer queueCapacity = 20;
        private Integer keepAliveSeconds = 60;
        private Boolean allowCoreThreadTimeOut = false;
        private String threadNamePrefix = "thread-";
        private Boolean waitForTaskToCompleteOnShuutdown = false;
        private String rejectExecutionHandler = REHEnum.AbortPolicy.name();

        @Bean("xTaskExecutor")
        public Executor xxxTaskExecutor() {
            log.info("config xxx task configuraion");
            var executor = new ThreadPoolTaskExecutor();
            executor.setCorePoolSize(corePoolSize);
            executor.setMaxPoolSize(maxPoolSize);
            executor.setQueueCapacity(queueCapacity);
            executor.setKeepAliveSeconds(keepAliveSeconds);
            executor.setThreadNamePrefix(threadNamePrefix);
            executor.setAllowCoreThreadTimeOut(allowCoreThreadTimeOut);
            executor.setRejectedExecutionHandler(REHEnum.get(rejectExecutionHandler));
            executor.setWaitForTasksToCompleteOnShutdown(waitForTaskToCompleteOnShuutdown);
            executor.initialize();
            return executor;
        }

        /**
         * RejectedExecutionHandler Enum
         */
        enum REHEnum {
            AbortPolicy(new ThreadPoolExecutor.AbortPolicy()),
            CallerRunsPolicy(new ThreadPoolExecutor.CallerRunsPolicy()),
            DiscardPolicy(new ThreadPoolExecutor.DiscardPolicy()),
            DiscarfOldestPolicy(new ThreadPoolExecutor.DiscardOldestPolicy());

            private RejectedExecutionHandler rejectedExecutionHandler;

            REHEnum(RejectedExecutionHandler handler) {
                this.rejectedExecutionHandler = handler;
            }

            private static RejectedExecutionHandler get(String handlerName) {
                return Arrays.stream(REHEnum.values())
                        .filter(it -> it.name().equals(handlerName))
                        .findFirst()
                        .orElse(AbortPolicy)
                        .getRejectedExecutionHandler();
            }

            RejectedExecutionHandler getRejectedExecutionHandler() {
                return rejectedExecutionHandler;
            }
        }
    }
    //-------------------------------------------------------------------------

}
