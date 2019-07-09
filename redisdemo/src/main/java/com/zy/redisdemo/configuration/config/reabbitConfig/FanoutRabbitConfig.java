package com.zy.redisdemo.configuration.config.reabbitConfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Description: RabbitMQ 广播模式配置类
 * @Author: zhangyu
 * @Date:Created in 2019-06-12
 */
@Configuration
public class FanoutRabbitConfig {


    //定义三个消息队列
    @Bean
    public Queue aMessage() {
        return new Queue("queue_fanout_A");
    }

    @Bean
    public Queue bMessage() {
        return new Queue("queue_fanout_B");
    }

    @Bean
    public Queue cMessage() {
        return new Queue("queue_fanout_C");
    }

    //定义一个交换器
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("AmethystFanoutExchange");
    }

    //将队列和交换器绑定
    @Bean
    Binding bindingExchangeA() {
        return BindingBuilder.bind(aMessage()).to(fanoutExchange());
    }

    @Bean
    Binding bindingExchangeB() {
        return BindingBuilder.bind(bMessage()).to(fanoutExchange());
    }

    @Bean
    Binding bindingExchangeC() {
        return BindingBuilder.bind(cMessage()).to(fanoutExchange());
    }

}
