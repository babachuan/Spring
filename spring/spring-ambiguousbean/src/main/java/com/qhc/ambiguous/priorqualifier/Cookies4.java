package com.qhc.ambiguous.priorqualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("cold-cookies4")
public class Cookies4 implements Dessert4 {
    private String name = "cookies4";
    @Override
    public void taste() {
        System.out.println("it claims like cookies4.");
    }
    public String getName() {
        return name;
    }
}
