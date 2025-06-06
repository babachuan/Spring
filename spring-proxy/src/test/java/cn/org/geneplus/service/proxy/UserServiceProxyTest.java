package cn.org.geneplus.service.proxy;

import cn.org.geneplus.cglib.MyMethodInterceptor;
import cn.org.geneplus.config.SpringConfig;
import cn.org.geneplus.jdkDproxy.MyInvocationHandler;
import cn.org.geneplus.service.UserService;
import cn.org.geneplus.service.impl.UserServiceImpl;
import cn.org.geneplus.service.impl.UserServiceImpl2;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Proxy;

/**
 * @Author:quhaichuan
 * @Date:2025/6/5 16:12
 */
public class UserServiceProxyTest extends TestCase {

    @Test
    public void testStaticProxy(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserServiceProxy userServiceProxy = applicationContext.getBean("userServiceProxy", UserServiceProxy.class);
        userServiceProxy.addUser("张三");
        userServiceProxy.deleteUser("李四");

    }

    // 测试使用JDK动态代理
    @Test
    public void testJdkDproxy(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserServiceImpl userServiceImpl = applicationContext.getBean("userServiceImpl", UserServiceImpl.class);
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(userServiceImpl);


        /**
         *     关于newProxyInstance的构造方法参数如下：
         *     public static Object newProxyInstance(ClassLoader loader, # 被代理对象的类加载器
         *                                           Class<?>[] interfaces, # 被代理对象实现的所有接口
         *                                           InvocationHandler h) { # 执行处理器对象，专门用于增强规则
         *    ClassLoader classLoader = userServiceImpl.getClass().getClassLoader();
         *    Class[] interfaces = userServiceImpl.getClass().getInterfaces();
         *
         *
         *
         *
         */

        UserService proxy = (UserService) Proxy.newProxyInstance(
                userServiceImpl.getClass().getClassLoader(),
                userServiceImpl.getClass().getInterfaces(),
                myInvocationHandler
        );
        proxy.addUser("张三");
        System.out.println("-----------");
        proxy.deleteUser("李四");
    }

    // 测试cglib动态代理
    @Test
    public void testCglibDproxy(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserServiceImpl2 userServiceImpl2 = applicationContext.getBean("userServiceImpl2", UserServiceImpl2.class);

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(userServiceImpl2.getClass());
        enhancer.setCallback(new MyMethodInterceptor());
        UserServiceImpl2 proxy = (UserServiceImpl2)enhancer.create();
        proxy.addUser("张三");
        System.out.println("-----------");
        proxy.deleteUser("张力场");


    }

}