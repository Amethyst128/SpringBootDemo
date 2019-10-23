package com.example.rabbitmqdemo.message;

import com.example.rabbitmqdemo.config.RabbitMQConstant;
import com.example.rabbitmqdemo.json.JsonUtil;
import com.example.rabbitmqdemo.message.dto.HuaweiCallback;
import com.example.rabbitmqdemo.message.dto.MessageCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageCallbackSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        HuaweiCallback huaweiCallback = new HuaweiCallback();
        huaweiCallback.setResult(true);
        huaweiCallback.setUrl("https://huawei.com/xx/xxx");

        MessageCallback<HuaweiCallback> callback = new MessageCallback<>();
        callback.setType("huawei");
        callback.setCallback(huaweiCallback);

        rabbitTemplate.convertAndSend(RabbitMQConstant.EXCHANGE_MESSAGE_CALLBACK,
                RabbitMQConstant.ROUTING_KEY_MESSAGE_CALLBACK, JsonUtil.toJson(callback));
    }

}
