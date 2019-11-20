package com.example.test.schedule;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SendMessageToUserTask {

    @Scheduled(fixedDelay = 5000)
    public void SendMessageToUserTask(){
        log.info("进入 SendMessageToUserTask 定时器...");
    }

}
