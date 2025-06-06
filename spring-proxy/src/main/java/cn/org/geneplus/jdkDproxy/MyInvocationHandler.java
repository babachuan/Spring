package cn.org.geneplus.jdkDproxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author:quhaichuan
 * @Date:2025/6/6 10:32
 */
// 这个InvocationHandler接口是jdk动态代理的实现类,尽量不要交给spring管理，因为spring管理对象，对象创建之后，会进行缓存，下次再获取对象时，会直接从缓存中获取对象，不会重新创建对象。
// 而我们是需要代理相同接口的实现类，这里会出问题。
public class MyInvocationHandler implements InvocationHandler {

    // 被代理的类，通过下面的有参构造方法从外面传进来
    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        /**
         * Object : 这里是代理对象
         * Method : 这里是被代理对象的方法，或者是接口中定义的方法，在method的前后就可以进行规则增强了
         * Object[] args : 这里是方法参数，方法在运行时的参数列表
         */
        System.out.println(method.getName()+" 方法调用前的日志记录");
        Object result = method.invoke(target, args);
        System.out.println(method.getName() + " 方法的参数为：" +Arrays.toString(args));
        System.out.println(method.getName()+ " 方法调用后的日志记录");
        return result;
    }
}
