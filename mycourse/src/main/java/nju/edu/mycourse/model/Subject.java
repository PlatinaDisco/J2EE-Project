package nju.edu.mycourse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import nju.edu.mycourse.common.resultMessage.ApprovalResult;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "subjecttbl")
public class Subject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //数据库内id
    @Column(nullable = false, unique = true)
    private String subjectName; //课程名称
    @Column(nullable = false)
    private String approvedResult; //是否审批通过
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy(value="id ASC")
    private Set<Course> courses; //每学期的课程
    @OneToOne(cascade = CascadeType.ALL)//Subject是关系的维护端，当删除 subject，会级联删除 forum
    @JoinColumn(name = "forum_id", referencedColumnName = "id")//subject中的forum_id字段参考forum表中的id字段
    private Forum forum; //论坛
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private Set<String> materialUrls; //课件存储的url
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "subjectOwner")
    @JsonIgnore
    private Instructor instructor;

    public Subject() {}

    public Subject(String subjectName, Instructor instructor) {
        this.subjectName = subjectName;
        this.approvedResult = ApprovalResult.disapprove.toString();
        this.courses = new HashSet<>();
        this.materialUrls = new HashSet<>();
        this.instructor = instructor;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getApprovedResult() {
        return approvedResult;
    }

    public void setApprovedResult(String approved) {
        approvedResult = approved;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public Set<String> getMaterialUrls() {
        return materialUrls;
    }

    public void setMaterialUrls(Set<String> materialUrls) {
        this.materialUrls = materialUrls;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(id, subject.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
