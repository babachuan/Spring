package cn.org.geneplus.bean;

/**
 * @Author:quhaichuan
 * @Date:2025/6/4 14:37
 */
public class User {
    private String name;
    private int age;

    public User() {

        System.out.println("第一步：执行无参构造方法");
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("第二步：执行了 setName方法");
        this.name = name;
    }

    // 这里自定义bean的初始化方法
    public void initUser() {
        System.out.println("第三步：执行了 init方法");
    }

    // 容器关闭销毁时执行的方法
    public void destroyUser() {
        System.out.println("第五步：执行了 destroy方法");
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
