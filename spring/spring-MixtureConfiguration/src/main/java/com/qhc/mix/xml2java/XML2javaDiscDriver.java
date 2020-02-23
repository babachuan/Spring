package com.qhc.mix.xml2java;
import org.springframework.beans.factory.annotation.Autowired;

public class XML2javaDiscDriver {
    @Autowired
    private XML2javaDisc xml2javaDisc;

    public void paly() {
        System.out.println(xml2javaDisc.getContent());
    }
}
