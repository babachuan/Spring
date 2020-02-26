package com.qhc.ambiguous.priorqualifier;

import com.qhc.ambiguous.priorqualifier.annotation.Posicle;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("cold-cake4")
@Posicle
public class Popsicle implements Dessert4 {
    private String name = "Popsicle";
    @Override
    public void taste() {
        System.out.println("it claims like Popsicle.");
    }

    public String getName() {
        return name;
    }
}
