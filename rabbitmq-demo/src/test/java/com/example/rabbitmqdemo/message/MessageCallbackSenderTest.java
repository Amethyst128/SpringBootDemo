package com.example.rabbitmqdemo.message;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MessageCallbackSenderTest {

    @Autowired
    private MessageCallbackSender messageCallbackSender;

    @Test
    public void send() {
        messageCallbackSender.send();
    }
}