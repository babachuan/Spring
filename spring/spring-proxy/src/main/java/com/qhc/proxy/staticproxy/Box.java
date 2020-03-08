package com.qhc.proxy.staticproxy;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("box")
public class Box implements Decorater{
    @Override
    public void buybox() {
        System.out.println("This box id buyed from England.");
    }
}
