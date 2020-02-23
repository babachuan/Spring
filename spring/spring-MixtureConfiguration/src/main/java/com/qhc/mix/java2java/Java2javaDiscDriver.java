package com.qhc.mix.java2java;

import org.springframework.beans.factory.annotation.Autowired;

public class Java2javaDiscDriver {
    @Autowired
    private Java2javaDisc java2javaDisc;
    public void paly(){
        System.out.println(java2javaDisc.getContent());
    }
}
