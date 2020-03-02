package com.qhc.sp.scope;

import org.springframework.stereotype.Component;

@Component  //默认情况下的bean作用域是singleton
public class SingetonBean {
    private String name ="singleton";

    public String getName() {
        return name;
    }
}
