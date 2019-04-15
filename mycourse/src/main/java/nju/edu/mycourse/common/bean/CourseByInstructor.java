package nju.edu.mycourse.common.bean;

import nju.edu.mycourse.model.Course;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CourseByInstructor implements Serializable {
    private String instructorName;
    private List<Course> courseList;

    public CourseByInstructor() {}

    public CourseByInstructor(String instructorName) {
        this.instructorName = instructorName;
        this.courseList = new ArrayList<>();
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}
