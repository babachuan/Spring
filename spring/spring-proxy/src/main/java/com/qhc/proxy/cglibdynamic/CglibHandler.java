package com.qhc.proxy.cglibdynamic;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

@Component
public class CglibHandler implements MethodInterceptor {

    private Object target;

    public Object getCglibBuyer(Object objectTarget) {
        this.target=objectTarget;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(objectTarget.getClass());
        enhancer.setCallback(this);
        Object proxyObject = enhancer.create();
        return proxyObject;

    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println(method.getName() + " ->>方法被执行");
        if (method.getName().equals("cglibbuyFromEngland")) {
            System.out.println("Prepare Folwer A");
            Object object = method.invoke(target, objects);
            System.out.println("FlowerA is combined with the England Box=cglib.");
            return object;
        } else {
            System.out.println("Prepare Folwer B");
            Object object = method.invoke(target, objects);
            System.out.println("FlowerB is combined with the USA Box=cglib.");
            return object;
        }
    }
}