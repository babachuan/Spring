package cn.org.geneplus.beans;

/**
 * @Author:quhaichuan
 * @Date:2025/6/3 17:35
 */
public class Person {

    private Integer userid;
    private String username;
    private String password;

    public Person(Integer userid, String username, String password) {
        System.out.println("全部参数有参构造方法被调用了");
        this.userid = userid;
        this.username = username;
        this.password = password;
        System.out.println("有参构造");
    }

    public Person() {
        System.out.println("无参构造");
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Person{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
