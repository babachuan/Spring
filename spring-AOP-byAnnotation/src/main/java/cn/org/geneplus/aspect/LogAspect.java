package cn.org.geneplus.aspect;

import cn.org.geneplus.annotation.LogOperation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author:quhaichuan
 * @Date:2025/6/10 18:14
 */
//@Aspect
//@Component
public class LogAspect {

    @Around("@annotation(logOperation)")
    // ProceedingJoinPiont表示链接点对象，用于访问目标方法的签名、参数、返回值，最重要的是可以调用.proceed()来执行原方法
    // LogOperation表示被拦截的方法上标注的注解，用于获取注解的value值，即操作名称
    public Object logExecution(ProceedingJoinPoint pjp , LogOperation logOperation) throws Throwable{
        // 获取被拦截方法的名称，如register login
        String methodName = pjp.getSignature().getName();

        // 获取被拦截方法的所有实参，作为Object[]数组返回
        Object[] args = pjp.getArgs();

        // 执行通知
        System.out.println("🎯【AOP前置】开始执行操作：" + logOperation.value());
        System.out.println("📦【方法名】" + methodName);
        System.out.println("📥【参数】" + Arrays.toString(args));

        // 执行原方法
        Object result = pjp.proceed();

        System.out.println("✅【AOP后置】操作完成，返回结果：" + result);

        return result;
    }
}
