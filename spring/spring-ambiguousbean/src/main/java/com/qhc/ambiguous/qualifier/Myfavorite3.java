package com.qhc.ambiguous.qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Myfavorite3 {
    @Autowired
    @Qualifier("cold-icecream3")
    private Dessert3 dessert;

    public void show(){
        System.out.println("my favorite is==");
        dessert.taste();
    }
}
