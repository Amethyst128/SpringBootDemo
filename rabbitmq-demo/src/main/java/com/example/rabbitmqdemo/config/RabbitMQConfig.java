package com.example.rabbitmqdemo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Desc: MQ相关配置
 * @Author: zy
 * @Date:Created in 2019/8/12
 */
@Configuration
public class RabbitMQConfig {

    /**
     *
     */
    @Configuration
    public class MessageCallbackConfig {
        @Bean
        public TopicExchange messageCallbackTopic() {
            return new TopicExchange(RabbitMQConstant.EXCHANGE_MESSAGE_CALLBACK);
        }

        @Bean
        public Queue messageCallbackQueue() {
            return new Queue(RabbitMQConstant.QUEUE_MESSAGE_SEND_A);
        }

        @Bean
        public Binding messageCallbackBinding(TopicExchange messageCallbackTopic, Queue messageCallbackQueue) {
            return BindingBuilder.bind(messageCallbackQueue).to(messageCallbackTopic)
                    .with(RabbitMQConstant.ROUTING_KEY_MESSAGE_CALLBACK);
        }
    }


}
