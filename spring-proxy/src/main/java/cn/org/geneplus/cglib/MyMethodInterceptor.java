package cn.org.geneplus.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author:quhaichuan
 * @Date:2025/6/6 14:47
 */
public class MyMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        /**
         * Object obj,生成之后的代理对象，即生成的子类
         * Method method, 父类中的方法（父类中声明的方法）
         * Object[] args, 方法调用时传入的参数数组
         * MethodProxy proxy, MethodProxy 是 CGLIB 提供的一个工具类，用于高效地调用目标类的原始方法（也就是被代理类中的方法）
         */

        System.out.println(method.getName()+" 方法调用前的日志记录");
        Object result = proxy.invokeSuper(obj, args); // 调用父类中的方法
        System.out.println(method.getName()+ " 方法调用后的日志记录");


        return result;
    }
}
