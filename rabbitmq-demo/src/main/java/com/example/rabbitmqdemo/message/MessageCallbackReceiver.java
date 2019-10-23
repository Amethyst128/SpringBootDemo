package com.example.rabbitmqdemo.message;

import com.example.rabbitmqdemo.config.RabbitMQConstant;
import com.example.rabbitmqdemo.json.JsonUtil;
import com.example.rabbitmqdemo.message.dto.HuaweiCallback;
import com.example.rabbitmqdemo.message.dto.MessageCallback;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageCallbackReceiver {

    @RabbitListener(queues = RabbitMQConstant.QUEUE_MESSAGE_SEND_A)
    @RabbitHandler
    public void receiver(String message) {

        MessageCallback callback = JsonUtil.toPojo(message, new TypeReference<MessageCallback>() {
        });

        if (callback.getType().equals("huawei")) {
            HuaweiCallback huaweiCallback = JsonUtil.toPojo(String.valueOf(callback.getCallback()),
                    new TypeReference<HuaweiCallback>() {});
        }



        System.out.println(message);

    }


}
