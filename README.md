![spring](https://img.shields.io/badge/spring-5.1.3.RELEASE-brightgreen.svg)     ![author](https://img.shields.io/badge/author-quhaichuan-orange.svg)     ![jdk](https://img.shields.io/badge/jdk->=1.8-blue.svg)

本项目仓库提供 spring常用应用场景，每个用例都会提供独立的示例项目代码

**版本说明**：

spring:  5.1.3.RELEASE

maven:  3.5.4

---

## 1. spring 基础使用

| Samples                                                      | Description                                    | Toplink                                                      |
| ------------------------------------------------------------ | ---------------------------------------------- | ------------------------------------------------------------ |
| [spring-autocomponent](spring/spring-autocomponent)          | 自动化装配                                     | [beans-autowired-annotation](https://docs.spring.io/spring/docs/5.1.13.RELEASE/spring-framework-reference/core.html#beans-autowired-annotation) |
| [spring-javacomponent](spring/spring-javacomponent)          | Java代码装配                                   | [beans-java-configuration-annotation](https://docs.spring.io/spring/docs/5.1.13.RELEASE/spring-framework-reference/core.html#beans-java-configuration-annotation) |
| [spring-XMLcomponent](spring/spring-XMLcomponent)            | XML装配                                        | [beans-xml-configuration](https://docs.spring.io/spring/docs/5.1.13.RELEASE/spring-framework-reference/core.html#beans-factory-class) |
| [spring-MixtureConfiguration](spring/spring-MixtureConfiguration) | XML&JAVA导入<br>和混合装配                     | [beans-java-combining](https://docs.spring.io/spring/docs/5.1.13.RELEASE/spring-framework-reference/core.html#beans-java-combining) |
| [spring-conditionbeans](spring/spring-conditionbeans)        | Profile条件注册bean<br>Conditional条件注册bean | [beans-definition-profiles-java](https://docs.spring.io/spring/docs/5.1.13.RELEASE/spring-framework-reference/core.html#beans-definition-profiles-java) |
| [spring-ambiguousbean](spring/spring-ambiguousbean)          | 自动装配的限定<br>@Qualifier&@Primary          | [beans-autowired-annotation-qualifiers](https://docs.spring.io/spring/docs/5.1.13.RELEASE/spring-framework-reference/core.html#beans-autowired-annotation-qualifiers) |
| [spring-AOP](spring/spring-AOP)                              | Spring AOP                                     | [aop-introduction-defn](https://docs.spring.io/spring/docs/5.1.13.RELEASE/spring-framework-reference/core.html#aop-introduction-defn) |
| [spring-circular-reference](spring/spring-circular-reference) | 循环依赖                                       | [beans-dependency-resolution](https://docs.spring.io/spring/docs/5.1.13.RELEASE/spring-framework-reference/core.html#beans-dependency-resolution) |
|                                                              |                                                |                                                              |


