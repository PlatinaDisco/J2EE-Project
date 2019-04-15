package nju.edu.mycourse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import nju.edu.mycourse.common.type.HomeworkType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "homeworktbl")
public class Homework implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //数据库内id
    @Column(nullable = false)
    private String homeworkTitle; // 作业标题
    @Column(nullable = false)
    private String assignmentTime; //作业发布时间
    @Column(nullable = false)
    private String deadline;     //作业截止时间
    @Column(nullable = false)
    private String homeworkSize; //作业大小：数字+M/G
    @Column(nullable = false)
    private String homeworkContent; //作业说明
    @Column(nullable = false)
    private String submitFileType;  //要求提交作业类型
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private Set<String> homeworkUrls; //学生作业存储url
    private String homeworkScoreFileUrl; //作业成绩文件存储url
    @ElementCollection
    @CollectionTable(name="student_scores")
    @MapKeyColumn(name="studentid")
    @Column(name="score")
    private Map<String, String> studentScores; //学生作业成绩 id : score
    @ManyToOne(optional = true)
    @JoinColumn(name = "homeworkOwner")
    @JsonIgnore
    private Course course; //作业所属的课程

    public Homework() {}

    public Homework(String homeworkTitle, String assignmentTime, String deadline, String homeworkSize, String homeworkContent,
                    String submitFileType, Course course) {
        this.homeworkTitle = homeworkTitle;
        this.assignmentTime = assignmentTime;
        this.deadline = deadline;
        this.homeworkSize = homeworkSize;
        this.homeworkContent = homeworkContent;
        this.submitFileType = submitFileType;
        this.homeworkUrls = new HashSet<>();
        this.homeworkScoreFileUrl = "";
        this.studentScores = new HashMap<>();
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHomeworkTitle() {

        return homeworkTitle;
    }

    public void setHomeworkTitle(String homeworkTitle) {
        this.homeworkTitle = homeworkTitle;
    }

    public String getAssignmentTime() {
        return assignmentTime;
    }

    public void setAssignmentTime(String assignmentTime) {
        this.assignmentTime = assignmentTime;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getHomeworkSize() {
        return homeworkSize;
    }

    public void setHomeworkSize(String homeworkSize) {
        this.homeworkSize = homeworkSize;
    }

    public String getHomeworkContent() {
        return homeworkContent;
    }

    public void setHomeworkContent(String homeworkContent) {
        this.homeworkContent = homeworkContent;
    }

    public String getSubmitFileType() {
        return submitFileType;
    }

    public void setSubmitFileType(String submitFileType) {
        this.submitFileType = submitFileType;
    }

    public Set<String> getHomeworkUrls() {
        return homeworkUrls;
    }

    public void setHomeworkUrls(Set<String> homeworkUrls) {
        this.homeworkUrls = homeworkUrls;
    }

    public String getHomeworkScoreFileUrl() {
        return homeworkScoreFileUrl;
    }

    public void setHomeworkScoreFileUrl(String homeworkScoreFileUrl) {
        this.homeworkScoreFileUrl = homeworkScoreFileUrl;
    }

    public Map<String, String> getStudentScores() {
        return studentScores;
    }

    public void setStudentScores(Map<String, String> studentScores) {
        this.studentScores = studentScores;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Homework homework = (Homework) o;
        return Objects.equals(id, homework.id) &&
                Objects.equals(assignmentTime, homework.assignmentTime) &&
                Objects.equals(deadline, homework.deadline) &&
                Objects.equals(homeworkSize, homework.homeworkSize) &&
                Objects.equals(homeworkContent, homework.homeworkContent) &&
                Objects.equals(submitFileType, homework.submitFileType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, assignmentTime, deadline, homeworkSize, homeworkContent, submitFileType);
    }
}
