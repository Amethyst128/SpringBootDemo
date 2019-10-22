package com.example.rabbitmqdemo.message.dto;

import lombok.Data;

@Data
public class MessageCallback<T> {

    private String type;

    private T callback;

}
