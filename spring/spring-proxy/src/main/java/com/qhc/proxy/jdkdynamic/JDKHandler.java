package com.qhc.proxy.jdkdynamic;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class JDKHandler implements InvocationHandler {

    private Object target;

    public Object getBuyer(Object objectTarget) {
        this.target=objectTarget;
        Object instance = Proxy.newProxyInstance(objectTarget.getClass().getClassLoader(), objectTarget.getClass().getInterfaces(),
                this);
        return instance;

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName() + " ->>方法被执行");
        if (method.getName().equals("buyFromEngland")) {
            System.out.println("Prepare Folwer A");
            Object object = method.invoke(target, args);
            System.out.println("FlowerA is combined with the England Box.");
            return object;
        } else {
            System.out.println("Prepare Folwer B");
            Object object = method.invoke(target, args);
            System.out.println("FlowerB is combined with the USA Box.");
            return object;
        }
    }

}