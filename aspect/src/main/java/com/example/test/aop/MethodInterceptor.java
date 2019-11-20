package com.example.test.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
/**
 * AOP切面：
 * 切点为：
 * 1. 某个注解
 * 2. 某个包路径
 */
public class MethodInterceptor {


    //------------------拦截某个注解---------

    @Pointcut("@annotation(org.springframework.scheduling.annotation.Scheduled)")
    public void proxyAspect(){
    }

    @Around("proxyAspect()")
    public Boolean doAround(){
        log.info("进入切面方法 doAround()...");
        return true;
    }

    @After("proxyAspect()")
    public Boolean doAfter(){
        log.info("进入切面方法 doAfter()...");
        return true;
    }

    @AfterReturning("proxyAspect()")
    public Boolean doAfterReturning(){
        log.info("进入切面方法 doAfterReturning()...");
        return true;
    }

    //-------------------按照包路径进行拦截--------

    @Pointcut("execution(* com.example.test.controller.*.*(..))")
    public void proxyAspectPackage(){
    }

    @Around("proxyAspectPackage()")
    public Boolean doAroundToPackage(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("进入切面方法 doAroundToPackage()...");
        joinPoint.proceed();
        return true;
    }

    @After("proxyAspectPackage()")
    public Boolean doAfterToPackage(){
        log.info("进入切面方法 doAfterToPackage()...");
        return true;
    }

    @AfterReturning("proxyAspectPackage()")
    public Boolean doAfterReturningToPackage(){
        log.info("进入切面方法 doAfterReturningToPackage()...");
        return true;
    }

}
