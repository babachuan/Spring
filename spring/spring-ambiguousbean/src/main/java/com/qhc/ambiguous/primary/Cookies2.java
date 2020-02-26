package com.qhc.ambiguous.primary;

import org.springframework.stereotype.Component;

@Component
public class Cookies2 implements Dessert2 {
    private String name = "cookies2";
    @Override
    public void taste() {
        System.out.println("it claims like cookies2.");
    }
    public String getName() {
        return name;
    }
}
