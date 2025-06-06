package cn.org.geneplus.bean;

import java.util.Date;

/**
 * @Author:quhaichuan
 * @Date:2025/6/4 9:33
 */
public class Mouse {
    private String name;
    private Date birthday;

    public Mouse() {
    }

    public Mouse(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Mouse{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }


}
