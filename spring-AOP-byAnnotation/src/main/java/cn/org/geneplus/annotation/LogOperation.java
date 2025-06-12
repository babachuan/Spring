package cn.org.geneplus.annotation;

import java.lang.annotation.*;

/**
 * @Author:quhaichuan
 * @Date:2025/6/10 18:10
 * 日志注解类
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogOperation {
    String value() default "";
}
