package com.qhc.ambiguous.nouniquexception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Myfavorite1 {
    @Autowired
    private Dessert1 dessert;

    public void show(){
        System.out.println("my favorite is==");
        dessert.taste();
    }
}
