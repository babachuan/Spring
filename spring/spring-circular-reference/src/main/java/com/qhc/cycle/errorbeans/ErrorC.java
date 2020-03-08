package com.qhc.cycle.errorbeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ErrorC {
    @Autowired
    private ErrorA a;

    public ErrorA getA() {
        return a;
    }

    public void setA(ErrorA a) {
        this.a = a;
    }
}
