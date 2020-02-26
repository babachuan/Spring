package com.qhc.profile.annotated;
import org.springframework.stereotype.Component;

@Component
@Production
public class AnnoProdBean implements AnnoShowInterface {
    private String msg="This is production Envirenment";

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public AnnoProdBean() {
        System.out.println("=====anno-prod====");
    }

    @Override
    public void show() {
        System.out.println(this.msg);
    }
}
