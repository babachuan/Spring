package com.qhc.ambiguous.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("cold-icecream3")
public class IceCream3 implements Dessert3 {
    private String name = "iceCream3";
    @Override
    public void taste() {
        System.out.println("it claims like iceCream3.");
    }

    public String getName() {
        return name;
    }
}
