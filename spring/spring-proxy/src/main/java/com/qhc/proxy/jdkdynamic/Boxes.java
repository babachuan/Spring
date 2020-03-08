package com.qhc.proxy.jdkdynamic;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("boxes")
public class Boxes  implements BuyFromWorld{
    @Override
    public void buyFromEngland() {
        System.out.println("The box is producted in England.");
    }

    @Override
    public void buyFromUAS() {
        System.out.println("The box is producted in USA.");
    }
}
