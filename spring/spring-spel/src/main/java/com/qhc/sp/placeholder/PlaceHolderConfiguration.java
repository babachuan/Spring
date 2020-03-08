package com.qhc.sp.placeholder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan(basePackages = "com.qhc.sp.placeholder")
@PropertySource("classpath:application.properties")
public class PlaceHolderConfiguration {

}
