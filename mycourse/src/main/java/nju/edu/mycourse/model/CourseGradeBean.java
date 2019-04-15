package nju.edu.mycourse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import nju.edu.mycourse.model.Student;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "coursegradetbl")
public class CourseGradeBean implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //数据库内id
    @ManyToOne(optional = true)
    @JoinColumn(name = "gradeOwner")
    @JsonIgnore
    private Student student; //课程分数所属学生
    private String courseName;//课程名称
    private String time; //课程时间
    private double grade;//学生总评分

    public CourseGradeBean() {}

    public CourseGradeBean(Student student, String courseName, String time, double grade) {
        this.student = student;
        this.courseName = courseName;
        this.time = time;
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseGradeBean that = (CourseGradeBean) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
