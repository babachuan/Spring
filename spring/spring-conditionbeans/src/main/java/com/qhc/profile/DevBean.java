package com.qhc.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevBean implements ShowInterface{
    private String msg="This is development Envirenment";
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DevBean() {
        System.out.println("=====dev====");
    }

    @Override
    public void show() {
        System.out.println(this.msg);
    }
}
