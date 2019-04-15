package nju.edu.mycourse.service;

import nju.edu.mycourse.common.bean.CourseByInstructor;
import nju.edu.mycourse.common.bean.CourseBySemester;
import nju.edu.mycourse.model.Course;

import java.util.List;

public interface StatisticsService {
    List<CourseBySemester> showInstructorCoursesBySemester(String instructorEmail);
    List<CourseBySemester> showStudentCoursesBySemester(String studentId);
    List<CourseByInstructor> showStudentCoursesByInstructor(String studentId);

    int showAllStudentsNumber();
    int showAllInstructorsNumber();
    int showAllSubjectsNumber();
    int showAllCoursesNumber();

}
