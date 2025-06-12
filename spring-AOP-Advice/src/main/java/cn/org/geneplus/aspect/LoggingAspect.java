package cn.org.geneplus.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author:quhaichuan
 * @Date:2025/6/11 16:48
 */
@Aspect
@Component
public class LoggingAspect {

    // 切点：匹配 UserService 下所有方法
    @Pointcut("execution(* cn.org.geneplus.service.UserService.*(..))")
    public void userServiceMethods(){}

    // 1. 前置通知
    @Before("userServiceMethods()")
    public void logBefore(JoinPoint joinPoint){
        System.out.println("🔔【Before】方法执行前：" + joinPoint.getSignature().getName());

    }

    // 2. 后置通知（无论是否抛异常）
    @After("userServiceMethods()")
    public void logAfter(JoinPoint joinPoint){
        System.out.println("🧹【After】方法执行完成：" + joinPoint.getSignature().getName());
    }

    // 3. 返回通知（成功返回）,这里的returning参数表示返回值，返回值必须和返回值类型一致
    @AfterReturning(pointcut = "userServiceMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result){
        System.out.println("✅【AfterReturning】返回值：" + result);
    }

    // 4. 异常通知,只有抛出异常时才会执行
    @AfterThrowing(pointcut = "userServiceMethods()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex){
        System.out.println("❌【AfterThrowing】异常信息："  + ex.getMessage());
    }

    // 5. 环绕通知
    @Around("userServiceMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable{
        String methodName = joinPoint.getSignature().getName();
        // 获取参数列表
        Object[] args = joinPoint.getArgs();

        // 执行前调用
        System.out.println("🚦【Around-前】调用方法：" + methodName + " 参数：" + Arrays.toString(args));

        // 执行原方法
        Object result;
        try  {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            System.out.println("🚨【Around-异常】" + throwable.getMessage());
            throw throwable;
        }

        // 执行后调用
        System.out.println("🚦【Around-后】返回结果：" + result);

        // 一定要返回，否则代理对象执行结果为空
        return result;
    }

}

