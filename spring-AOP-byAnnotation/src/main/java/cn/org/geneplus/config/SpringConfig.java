package cn.org.geneplus.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author:quhaichuan
 * @Date:2025/6/11 10:43
 */
@ComponentScan(basePackages = "cn.org.geneplus")
@EnableAspectJAutoProxy
public class SpringConfig {
}
