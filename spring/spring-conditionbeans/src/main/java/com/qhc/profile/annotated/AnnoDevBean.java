package com.qhc.profile.annotated;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Development
public class AnnoDevBean implements AnnoShowInterface {
    private String msg="This is development Envirenment";
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public AnnoDevBean() {
        System.out.println("=====anno-dev====");
    }

    @Override
    public void show() {
        System.out.println(this.msg);
    }
}
