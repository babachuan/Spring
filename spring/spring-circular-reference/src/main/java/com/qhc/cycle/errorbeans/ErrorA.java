package com.qhc.cycle.errorbeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope("prototype")  //如果把A中的prototype去掉，后面B和C的引用即使用prototype，后面也会变为单例行为
public class ErrorA {
    @Autowired
    private ErrorB b;

    public ErrorB getB() {
        return b;
    }

    public void setB(ErrorB b) {
        this.b = b;
    }
}
