package com.qhc.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Audience {
    @Pointcut("execution(* Show.show(..))")
    public void perform() {}

    @After("perform()")
    public void applause(){
        System.out.println("表演非常精彩");
    }

    @Before("perform()")
    public void entrance(){
        System.out.println("观众已经进场");
    }
}
