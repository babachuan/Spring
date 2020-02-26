package com.qhc.ambiguous.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("cold-cookies3")
public class Cookies3 implements Dessert3 {
    private String name = "cookies3";
    @Override
    public void taste() {
        System.out.println("it claims like cookies3.");
    }
    public String getName() {
        return name;
    }
}
