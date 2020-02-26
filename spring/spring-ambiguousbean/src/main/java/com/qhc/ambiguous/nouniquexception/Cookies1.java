package com.qhc.ambiguous.nouniquexception;

import org.springframework.stereotype.Component;

@Component
public class Cookies1 implements Dessert1{
    private String name = "cookies1";
    @Override
    public void taste() {
        System.out.println("it claims like cookies1.");
    }
    public String getName() {
        return name;
    }
}
