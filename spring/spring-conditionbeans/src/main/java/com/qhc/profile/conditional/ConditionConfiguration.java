package com.qhc.profile.conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:condition.properties")
public class ConditionConfiguration {

    @Bean
    @Conditional(PersonExistCondition.class)
    public Person person(){
        return new Person();
    }
}
