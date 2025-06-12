package cn.org.geneplus.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author:quhaichuan
 * @Date:2025/6/10 11:12
 */
@Aspect
@Component
public class LoggingAspect {

    // 定义一个切入点，匹配所有UserService类下的所有方法
//    @Pointcut("execution(* cn.org.geneplus.service.UserService.*(..))")
//    public void userServiceMethods(){}

    // 结合注解匹配，拦截所有方法
    @Pointcut("@annotation(cn.org.geneplus.service.UserService.*(..))")
    public void userServiceMethods(){}

    // 前置通知，在方法执行之前执行
    @Before("userServiceMethods()")
    public void beforeMethod(JoinPoint joinPoint){
        System.out.println("【AOP前置通知】调用方法"+joinPoint.getSignature());
    }
}
