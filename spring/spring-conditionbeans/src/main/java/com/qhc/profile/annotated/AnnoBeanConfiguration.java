package com.qhc.profile.annotated;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.qhc.profile.annotated")
@PropertySource("classpath:application.properties")
public class AnnoBeanConfiguration {
}
