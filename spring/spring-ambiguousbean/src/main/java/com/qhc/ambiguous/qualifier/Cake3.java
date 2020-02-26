package com.qhc.ambiguous.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Qualifier("cold-cake3")
public class Cake3 implements Dessert3 {
    private String name = "cake3";
    @Override
    public void taste() {
        System.out.println("it claims like cake3.");
    }

    public String getName() {
        return name;
    }
}
