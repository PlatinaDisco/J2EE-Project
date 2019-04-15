package nju.edu.mycourse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "studenttbl")
public class Student implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  //数据库内id
    @Column(nullable = false, unique = true)
    private String njuEmail; //学生邮箱，根据学生邮箱注册账号
    @Column(nullable = false)
    private String password; //学生账号密码
    @Column(nullable = false)
    private String name;     //学生姓名
    @Column(nullable = false, unique = true)
    private String studentId; //学生id
    @Column(nullable = false)
    private String type; //学生类型
    @Column(nullable = false)
    private boolean isLogout;//该账号是否注销
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "student_course",joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    @OrderBy(value="id ASC")
    private Set<Course> courses;//学生选课列表
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "student_droppedcourse",joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    @OrderBy(value="id ASC")
    private Set<Course> droppedCourses; //学生退课列表
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy(value="id ASC")
    private Set<CourseGradeBean> grades; //学生成绩列表

    public Student() {}

    public Student(String njuEmail, String password, String name, String studentId, String type) {
        this.njuEmail = njuEmail;
        this.password = password;
        this.name = name;
        this.studentId = studentId;
        this.type = type;
        this.isLogout = false;
        this.courses = new HashSet<>();
        this.droppedCourses = new HashSet<>();
        this.grades = new HashSet<>();
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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isLogout() {
        return isLogout;
    }

    public void setLogout(boolean logout) {
        isLogout = logout;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Set<Course> getDroppedCourses() {
        return droppedCourses;
    }

    public void setDroppedCourses(Set<Course> droppedCourses) {
        this.droppedCourses = droppedCourses;
    }

    public Set<CourseGradeBean> getGrades() {
        return grades;
    }

    public void setGrades(Set<CourseGradeBean> grades) {
        this.grades = grades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
