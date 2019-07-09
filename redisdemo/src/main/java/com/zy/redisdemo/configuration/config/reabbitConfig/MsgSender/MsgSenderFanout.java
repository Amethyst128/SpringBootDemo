package com.zy.redisdemo.configuration.config.reabbitConfig.MsgSender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 消息的生产者
 * @Author: zhangyu
 * @Date:Created in 11:29 2019/6/12
 */
@Component
public class MsgSenderFanout {

    @Autowired
    private AmqpTemplate rabbitTemplate;
//    private RabbitTemplate rabbitTemplate;

    public void sendMsg() {
        String context = "Hi,fanout msg";
        System.out.println("Sender:" + context);
        this.rabbitTemplate.convertAndSend("AmethystFanoutExchange", "", context);
    }

}
