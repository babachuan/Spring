package com.qhc.ambiguous.nouniquexception;

import org.springframework.stereotype.Component;

@Component
public class IceCream1 implements Dessert1{
    private String name = "iceCream1";
    @Override
    public void taste() {
        System.out.println("it claims like iceCream1.");
    }

    public String getName() {
        return name;
    }
}
