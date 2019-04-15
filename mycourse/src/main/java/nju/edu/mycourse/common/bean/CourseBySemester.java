package nju.edu.mycourse.common.bean;

import nju.edu.mycourse.model.Course;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CourseBySemester implements Serializable {
    private String semester;
    private List<Course> courseList;

    public CourseBySemester() {}

    public CourseBySemester(String semester) {
        this.semester = semester;
        this.courseList = new ArrayList<>();
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}
