package com.qhc.ambiguous.primary;

import org.springframework.stereotype.Component;

@Component
public class IceCream2 implements Dessert2 {
    private String name = "iceCream2";
    @Override
    public void taste() {
        System.out.println("it claims like iceCream2.");
    }

    public String getName() {
        return name;
    }
}
