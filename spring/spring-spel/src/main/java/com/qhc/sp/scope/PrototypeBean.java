package com.qhc.sp.scope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)   //指定作用域为prototype，每次注入都会产生一个新的bean；
public class PrototypeBean {
    private String name ="prototype";

    public String getName() {
        return name;
    }
}
