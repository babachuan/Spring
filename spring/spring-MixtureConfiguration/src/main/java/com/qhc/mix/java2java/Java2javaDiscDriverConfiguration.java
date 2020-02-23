package com.qhc.mix.java2java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(Java2javaDiscConfiguration.class)
public class Java2javaDiscDriverConfiguration {
    @Bean
    public Java2javaDiscDriver java2javaDiscDriver(){
        return new Java2javaDiscDriver();
    }
}
