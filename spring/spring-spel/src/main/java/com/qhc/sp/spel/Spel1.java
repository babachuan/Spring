package com.qhc.sp.spel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Spel1 {
    @Value("#{systemEnvironment['spel.name']}")
    private String name;


    public Spel1(String name) {
        this.name = name;
    }

    public void test(){
        System.out.println(name);
    }
}
