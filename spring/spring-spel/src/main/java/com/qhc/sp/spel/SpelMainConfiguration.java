package com.qhc.sp.spel;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan(basePackages = "com.qhc.sp.spel")
@PropertySource("classpath:application.properties")
public class SpelMainConfiguration {


}
