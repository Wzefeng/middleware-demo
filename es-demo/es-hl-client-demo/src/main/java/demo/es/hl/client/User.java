package demo.es.hl.client;

public class User {

    private String username;

    private Integer age;

    private String addr;

    public User() {
    }

    public User(String username, Integer age, String addr) {
        this.username = username;
        this.age = age;
        this.addr = addr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
