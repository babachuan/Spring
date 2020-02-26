package com.qhc.ambiguous.priorqualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("cold-icecream4")
public class IceCream4 implements Dessert4 {
    private String name = "iceCream3";
    @Override
    public void taste() {
        System.out.println("it claims like iceCream4.");
    }

    public String getName() {
        return name;
    }
}
