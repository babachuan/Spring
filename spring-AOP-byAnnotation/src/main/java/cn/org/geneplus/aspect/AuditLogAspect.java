package cn.org.geneplus.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author:quhaichuan
 * @Date:2025/6/11 13:28
 */
@Aspect
@Component
public class AuditLogAspect {
    /**
     * 基于注解的切面类，实现日志审计
     */

    // 定义切点
    @Pointcut("@annotation(cn.org.geneplus.annotation.LogOperation)")
    public void auditableMethods(){}

    @Around("auditableMethods()")
    public Object logOperation(ProceedingJoinPoint pjp) throws Throwable{
        String methodName = pjp.getSignature().toShortString();
        System.out.println("🔍 ==开始审计日志记录：" + methodName);

        // 执行原方法
        Object result = pjp.proceed();

        System.out.println("✅ ==审计结束，结果：" + result);
        return result;
    }
}
