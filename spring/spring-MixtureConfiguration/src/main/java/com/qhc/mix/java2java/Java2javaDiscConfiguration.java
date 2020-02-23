package com.qhc.mix.java2java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Java2javaDiscConfiguration {
    @Bean
    public Java2javaDisc java2javaDisc(){
        return new Java2javaDisc();
    }
}
