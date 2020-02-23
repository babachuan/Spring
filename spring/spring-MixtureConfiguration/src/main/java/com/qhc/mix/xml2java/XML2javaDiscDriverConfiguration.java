package com.qhc.mix.xml2java;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:xml2java-spring-context.txt.xml")
public class XML2javaDiscDriverConfiguration {
    @Bean
    public XML2javaDiscDriver xml2javaDiscDriver(){
        return new XML2javaDiscDriver();
    }
}
