package com.example.test.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Slf4j
public class UserController {


    @GetMapping
    public void getAllUserName(){
        log.info("进入controller方法...");
    }


}
