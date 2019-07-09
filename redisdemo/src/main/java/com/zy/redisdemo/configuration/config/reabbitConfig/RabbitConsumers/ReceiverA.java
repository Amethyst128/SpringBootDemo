package com.zy.redisdemo.configuration.config.reabbitConfig.RabbitConsumers;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: 消息消费者A
 * @Author: zhangyu
 * @Date:Created in 10:38 2019/6/12
 */
@Component
@RabbitListener(queues = "queue_fanout_A")
public class ReceiverA {

    @RabbitHandler
    public void process(String helloStr) {
        System.out.println("AReceiver: " + helloStr);
    }

}
