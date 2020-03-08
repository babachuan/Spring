package com.qhc.proxy.cglibdynamic;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("cglibboxes")
public class CglibBoxes{
    public void cglibbuyFromEngland() {
        System.out.println("The box is buyed from England by cglib.");
    }

    public void cglibbuyFromUAS() {
        System.out.println("The box is buyed from USA by cglib.");
    }
}
