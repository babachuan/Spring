package cn.org.geneplus.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author:quhaichuan
 * @Date:2025/6/5 13:38
 */
@ComponentScan(basePackages = "cn.org.geneplus")
@PropertySource("classpath:jdbc.properties")
public class SpringConfig {
}
