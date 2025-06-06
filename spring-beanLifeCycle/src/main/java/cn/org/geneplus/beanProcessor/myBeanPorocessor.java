package cn.org.geneplus.beanProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @Author:quhaichuan
 * @Date:2025/6/4 14:57
 */
// 实现BeanPostProcessor接口，这里会对所有的bean生效
public class myBeanPorocessor  implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        /**
         *  bean: 这里是要具体要处理的bean，比如User
         *  beanName: 这里是bean的name，比如user
         *  返回值：返回处理后的bean，一定要返回bean，否则会报空指针异常
         */
        System.out.println("bean后置Before通知：postProcessBeforeInitialization执行了");
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("bean后置After通知：postProcessAfterInitialization执行了");
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
