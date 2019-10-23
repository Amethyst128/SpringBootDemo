package com.example.rabbitmqdemo.config;

/**
 * @Desc: rabbitMQ 的相关定义
 * @Author: zy
 * @Date:Created in 2019/8/12
 */
public class RabbitMQConstant {

    /**
     * 消息交换机
     */
    public static final String EXCHANGE_MESSAGE_CALLBACK = "message_callback_exchange";

    public static final String ROUTING_KEY_MESSAGE_CALLBACK = "message_callback_routing_key";

    /**
     * 队列
     */
    public static final String QUEUE_MESSAGE_SEND_A = "message_queue_send_A";
    public static final String QUEUE_MESSAGE_SEND_B = "message_queue_send_B";
    public static final String QUEUE_MESSAGE_SEND_C = "message_queue_send_C";




}
