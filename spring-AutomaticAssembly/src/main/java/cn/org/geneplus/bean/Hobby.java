package cn.org.geneplus.bean;

/**
 * @Author:quhaichuan
 * @Date:2025/6/4 16:35
 */
public class Hobby {
    private String hobyname;
    private String type;

    public Hobby() {
    }

    public Hobby(String hobyname, String type) {
        this.hobyname = hobyname;
        this.type = type;
    }

    public String getHobyname() {
        return hobyname;
    }

    public void setHobyname(String hobyname) {
        this.hobyname = hobyname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Hobby{" +
                "hobyname='" + hobyname + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
