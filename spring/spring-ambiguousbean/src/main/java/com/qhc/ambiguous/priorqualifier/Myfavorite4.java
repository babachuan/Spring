package com.qhc.ambiguous.priorqualifier;

import com.qhc.ambiguous.priorqualifier.annotation.Posicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Myfavorite4 {
    @Autowired
    @Qualifier("cold-cake4")
    @Posicle
    private Dessert4 dessert;

    public void show(){
        System.out.println("my favorite is==");
        dessert.taste();
    }
}
