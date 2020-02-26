package com.qhc.ambiguous.primary;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Cake2 implements Dessert2 {
    private String name = "cake2";
    @Override
    public void taste() {
        System.out.println("it claims like cake2.");
    }

    public String getName() {
        return name;
    }
}
