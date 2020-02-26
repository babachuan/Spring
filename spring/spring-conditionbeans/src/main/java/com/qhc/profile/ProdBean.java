package com.qhc.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class ProdBean implements ShowInterface{
    private String msg="This is production Envirenment";

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ProdBean() {
        System.out.println("=====prod====");
    }

    @Override
    public void show() {
        System.out.println(this.msg);
    }
}
