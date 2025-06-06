package cn.org.geneplus.bean;

/**
 * @Author:quhaichuan
 * @Date:2025/6/4 10:21
 */
public class Cat {
    private String name;
    private Mouse mouse;

    public Cat() {
    }

    public Cat(String name, Mouse mouse) {
        this.name = name;
        this.mouse = mouse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", mouse=" + mouse +
                '}';
    }
}
