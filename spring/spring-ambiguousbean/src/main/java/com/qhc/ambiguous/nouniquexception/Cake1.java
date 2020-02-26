package com.qhc.ambiguous.nouniquexception;

import org.springframework.stereotype.Component;

@Component
public class Cake1 implements Dessert1{
    private String name = "cake1";
    @Override
    public void taste() {
        System.out.println("it claims like cake1.");
    }

    public String getName() {
        return name;
    }
}
