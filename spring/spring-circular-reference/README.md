## Spring基础-循环依赖

Bean A → Bean B → Bean C → Bean A

Spring 在依赖注入时默认产生的bean是`singleton`的，在注入的时候不会进行循环引用检查，而是直接创建。

但如果将bean设置成`prototype`的话，在实例化bean时，会检查依赖bean是否创建，依此，如果有循环依赖，Spring会抛出`BeanCurrentlyInCreationException`异常。

*说明*：类似`errorbeans`中的演示，当prototype的bean被singleton的bean依赖时，后面的bean的行为也展现出单例。

