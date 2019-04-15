package nju.edu.mycourse.controller;

import nju.edu.mycourse.common.bean.CourseByInstructor;
import nju.edu.mycourse.common.bean.CourseBySemester;
import nju.edu.mycourse.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {
    @Autowired
    private StatisticsService statistics;

    @RequestMapping("/showInstructorCoursesBySemester")
    public List<CourseBySemester> showInstructorCoursesBySemester(@RequestParam String instructorEmail) {
        return statistics.showInstructorCoursesBySemester(instructorEmail);
    }

    @RequestMapping("/showStudentCoursesBySemester")
    public List<CourseBySemester> showStudentCoursesBySemester(@RequestParam String studentId) {
        return statistics.showStudentCoursesBySemester(studentId);
    }

    @RequestMapping("/showStudentCoursesByInstructor")
    public List<CourseByInstructor> showStudentCoursesByInstructor(@RequestParam String studentId) {
        return statistics.showStudentCoursesByInstructor(studentId);
    }

    @RequestMapping("/showAllStudentsNumber")
    public int showAllStudentsNumber() {
        return statistics.showAllStudentsNumber();
    }

    @RequestMapping("/showAllInstructorsNumber")
    public int showAllInstructorsNumber() {
        return statistics.showAllInstructorsNumber();
    }

    @RequestMapping("/showAllSubjectsNumber")
    public int showAllSubjectsNumber() {
        return statistics.showAllSubjectsNumber();
    }

    @RequestMapping("/showAllCoursesNumber")
    public int showAllCoursesNumber() {
        return statistics.showAllCoursesNumber();
    }
}
