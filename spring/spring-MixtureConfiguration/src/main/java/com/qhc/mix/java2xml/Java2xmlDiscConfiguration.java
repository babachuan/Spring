package com.qhc.mix.java2xml;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Java2xmlDiscConfiguration {
    @Bean
    public Java2xmlDisc java2xmlDisc(){
        return new Java2xmlDisc();
    }
}
