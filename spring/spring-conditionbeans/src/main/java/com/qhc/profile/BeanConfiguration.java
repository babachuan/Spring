package com.qhc.profile;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.qhc.profile")
@PropertySource("classpath:application.properties")
public class BeanConfiguration {
}
