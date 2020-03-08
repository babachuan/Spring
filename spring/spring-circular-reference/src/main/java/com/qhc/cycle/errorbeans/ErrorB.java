package com.qhc.cycle.errorbeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ErrorB {
    @Autowired
    private ErrorC c;

    public ErrorC getC() {
        return c;
    }

    public void setC(ErrorC c) {
        this.c = c;
    }
}
