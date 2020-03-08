package com.qhc.proxy.staticproxy;

import org.springframework.stereotype.Component;

@Component
public class BoxProxy implements Decorater {

    private Decorater decorater;

    public BoxProxy(Decorater decorater) {
        this.decorater = decorater;
    }

    @Override
    public void buybox() {
        //监控原来box类的buybox行为，并为其进行增强
        System.out.println("Put a flower to the box");
        this.decorater.buybox();
        System.out.println("This box is a England box with a flower!");
    }
}
