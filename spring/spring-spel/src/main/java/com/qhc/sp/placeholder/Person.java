package com.qhc.sp.placeholder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Person {
    private String name;
    private int age;

    //这里使用占位符进行注入
    public Person(@Value("${placeholder.name}") String name, @Value("${placeholder.age}") int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
