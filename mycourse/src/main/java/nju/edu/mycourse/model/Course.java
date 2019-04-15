package nju.edu.mycourse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import nju.edu.mycourse.common.resultMessage.ApprovalResult;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "coursetbl")
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //数据库内id
    @Column(nullable = false)
    private String courseName;//课程名称
    @Column(nullable = false)
    private String semester; //年份+学期
    @Column(nullable = false)
    private String classes;  //班次
    @Column(nullable = false)
    private int limitation;  //限定人数
    @Column(nullable = false)
    private String approveResult; //是否审批通过
    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER)
    @OrderBy(value="id ASC")
    @JsonIgnore
    private Set<Student> students; //选课学生列表
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy(value="id ASC")
    private Set<Homework> homeworkList; //该课程作业列表
    private String testScoreFileUrl; //学生考试成绩文件存储url
    private String totalGradeFileUrl; //学生总评成绩文件存储url
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="test_scores")
    @MapKeyColumn(name="studentid")
    @Column(name="score")
    private Map<String, String> testScore; //学生考试成绩 id : score
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="total_scores")
    @MapKeyColumn(name="studentid")
    @Column(name="grade")
    private Map<String, String> grades; //学生总评成绩 id : grade
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "courseOwner")
    @JsonIgnore
    private Subject subject;
    @Column(nullable = false)
    private String instructorName;

    public Course() {}

    public Course(String courseName, String semester, String classes, int limitation, Subject subject, String instructorName) {
        this.courseName = courseName;
        this.semester = semester;
        this.classes = classes;
        this.limitation = limitation;
        this.approveResult = ApprovalResult.disapprove.toString();
        this.students = new HashSet<>();
        this.homeworkList = new HashSet<>();
        this.testScore = new HashMap<>();
        this.grades = new HashMap<>();
        this.subject = subject;
        this.instructorName = instructorName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public int getLimitation() {
        return limitation;
    }

    public void setLimitation(int limitation) {
        this.limitation = limitation;
    }

    public String getApproveResult() {
        return approveResult;
    }

    public void setApproveResult(String approved) {
        approveResult = approved;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudentIds(Set<Student> students) {
        this.students = students;
    }

    public Set<Homework> getHomeworkList() {
        return homeworkList;
    }

    public void setHomeworkList(Set<Homework> homeworkList) {
        this.homeworkList = homeworkList;
    }

    public String getTestScoreFileUrl() {
        return testScoreFileUrl;
    }

    public void setTestScoreFileUrl(String testScoreFileUrl) {
        this.testScoreFileUrl = testScoreFileUrl;
    }

    public String getTotalGradeFileUrl() {
        return totalGradeFileUrl;
    }

    public void setTotalGradeFileUrl(String totalGradeFileUrl) {
        this.totalGradeFileUrl = totalGradeFileUrl;
    }

    public Map<String, String> getTestScore() {
        return testScore;
    }

    public void setTestScore(Map<String, String> testScore) {
        this.testScore = testScore;
    }

    public Map<String, String> getGrades() {
        return grades;
    }

    public void setGrades(Map<String, String> grades) {
        this.grades = grades;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
