package cn.org.geneplus.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author:quhaichuan
 * @Date:2025/6/10 11:11
 */
@ComponentScan(basePackages = "cn.org.geneplus")
@EnableAspectJAutoProxy
public class SpringConfig {
}
