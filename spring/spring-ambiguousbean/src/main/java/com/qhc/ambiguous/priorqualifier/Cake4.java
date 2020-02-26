package com.qhc.ambiguous.priorqualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("cold-cake4")
@com.qhc.ambiguous.priorqualifier.annotation.Cake4
public class Cake4 implements Dessert4 {
    private String name = "cake4";
    @Override
    public void taste() {
        System.out.println("it claims like cake4.");
    }

    public String getName() {
        return name;
    }
}
