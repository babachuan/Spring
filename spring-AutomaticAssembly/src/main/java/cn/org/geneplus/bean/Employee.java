package cn.org.geneplus.bean;

/**
 * @Author:quhaichuan
 * @Date:2025/6/4 16:33
 */
public class Employee {
    private String name;
    private Dep dep;
    private Hobby hobby;

    public Employee() {
    }

    public Employee(String name, Dep dep, Hobby hobby) {
        this.name = name;
        this.dep = dep;
        this.hobby = hobby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dep getDep() {
        return dep;
    }

    public void setDep(Dep dep) {
        this.dep = dep;
    }

    public Hobby getHobby() {
        return hobby;
    }

    public void setHobby(Hobby hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", dep=" + dep +
                ", hobby=" + hobby +
                '}';
    }
}
