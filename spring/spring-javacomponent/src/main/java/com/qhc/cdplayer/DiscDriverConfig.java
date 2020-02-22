package com.qhc.cdplayer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscDriverConfig {
    @Bean
    public Disc disc(){
        return  new Disc();
    }

    @Bean
    public DiscDriver discDriver(){
        return new DiscDriver();
    }
}
