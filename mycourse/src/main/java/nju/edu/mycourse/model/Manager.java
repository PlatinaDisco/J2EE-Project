package nju.edu.mycourse.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "managertbl")
public class Manager implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id; //数据库内id
    private String username; //用户名
    private String password; //密码

    public Manager() {}

    public Manager(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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
}
