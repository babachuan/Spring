package cn.org.geneplus.bean;

/**
 * @Author:quhaichuan
 * @Date:2025/6/4 16:34
 */
public class Dep {
    private String name;
    private String area;

    public Dep() {
    }

    public Dep(String name, String area) {
        this.name = name;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Dep{" +
                "name='" + name + '\'' +
                ", area='" + area + '\'' +
                '}';
    }
}
