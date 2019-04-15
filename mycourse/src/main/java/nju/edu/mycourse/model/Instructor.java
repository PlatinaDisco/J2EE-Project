package nju.edu.mycourse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "instructortbl")
public class Instructor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //数据库内id
    @Column(nullable = false, unique = true)
    private String njuEmail; //教师邮箱，通过邮箱注册账号
    @Column(nullable = false)
    private String password; //账号密码
    @Column(nullable = false)
    private String name;     //教师姓名
    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy(value="id ASC")
    private Set<Subject> subjects;//教师创建课程

    public Instructor() {}

    public Instructor(String njuEmail, String password, String name) {
        this.njuEmail = njuEmail;
        this.password = password;
        this.name = name;
        this.subjects = new HashSet<>();
    }

    public String getNjuEmail() {
        return njuEmail;
    }

    public void setNjuEmail(String njuEmail) {
        this.njuEmail = njuEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

}
