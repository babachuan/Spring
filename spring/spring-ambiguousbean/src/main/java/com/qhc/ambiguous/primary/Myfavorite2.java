package com.qhc.ambiguous.primary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Myfavorite2 {
    @Autowired
    private Dessert2 dessert;

    public void show(){
        System.out.println("my favorite is==");
        dessert.taste();
    }
}
