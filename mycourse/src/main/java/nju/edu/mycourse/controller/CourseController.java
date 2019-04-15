package nju.edu.mycourse.controller;

import nju.edu.mycourse.common.resultMessage.CourseResult;
import nju.edu.mycourse.model.Course;
import nju.edu.mycourse.service.CourseService;
import nju.edu.mycourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    private CourseService course;

    @RequestMapping("/startCourse")
    String startCourse(@RequestParam String courseName, @RequestParam String semester, @RequestParam String classes,
                       @RequestParam int limitation, @RequestParam String subjectName) {
        return course.startCourse(courseName, semester, classes, limitation, subjectName).toString();
    }

    @RequestMapping("/approveCourse")
    String approveCourse(@RequestParam String courseName, @RequestParam String semester) {
        return course.approveCourse(courseName, semester).toString();
    }

    @RequestMapping("/uploadTestScoreFile")
    String uploadTestScoreFile(@RequestParam String courseId, @RequestParam MultipartFile multipartFile) {
        return course.uploadTestScoreFile(courseId, multipartFile).toString();
    }

    @RequestMapping("/uploadTotalGradeFile")
    String uploadTotalGradeFile(@RequestParam String courseId, @RequestParam MultipartFile multipartFile) {
        return course.uploadTotalGradeFile(courseId, multipartFile).toString();
    }

    @RequestMapping("/downloadScoreFile")
    void downloadScoreFile(@RequestParam String courseId, @RequestParam String filename, @RequestParam HttpServletResponse response) {
        course.downloadScoreFile(courseId, filename, response);
    }

    @RequestMapping("/selectCourse")
    String selectCourse(@RequestParam String courseId, @RequestParam String studentId) {
        return course.selectCourse(courseId, studentId).toString();
    }

    @RequestMapping("/dropCourse")
    String dropCourse(@RequestParam String courseId, @RequestParam String studentId) {
        return course.dropCourse(courseId, studentId).toString();
    }

    @RequestMapping("/modifyCourseInfo")
    String modifyCourseInfo(@RequestParam String courseId, @RequestParam String courseName, @RequestParam String semester,
                            @RequestParam String classes, @RequestParam int limit) {
        return course.modifyCourseInfo(courseId, courseName, semester, classes, limit).toString();
    }

    @RequestMapping("/showCourseSubjectName")
    public String showCourseSubjectName(@RequestParam String courseId) {
        return course.showCourseSubjectName(courseId);
    }

    @RequestMapping("/showCourse")
    Course showCourse(@RequestParam String courseId) {
        return course.showCourse(courseId);
    }

    @RequestMapping("/showStudentCourse")
    List<Course> showStudentCourse(@RequestParam String studentId) {
        return course.showStudentCourse(studentId);
    }

    @RequestMapping("/showStudentDroppedCourse")
    List<Course> showStudentDroppedCourse(@RequestParam String studentId) {
        return course.showStudentDroppedCourse(studentId);
    }

    @RequestMapping("/showSubjectCourses")
    List<Course> showSubjectCourses(@RequestParam String subjectName) {
        return course.showSubjectCourses(subjectName);
    }

    @RequestMapping("/showSubjectApprovedCourses")
    List<Course> showSubjectApprovedCourses(@RequestParam String subjectName) {
        return course.showSubjectApprovedCourses(subjectName);
    }

    @RequestMapping("/showApprovedCourses")
    public List<Course> showApprovedCourses() {
        return course.showApprovedCourses();
    }

    @RequestMapping("/showUnapprovedCourses")
    List<Course> showUnapprovedCourses() {
        return course.showUnapprovedCourses();
    }

    @RequestMapping("/showAllCourses")
    List<Course> showAllCourses() {
        return course.showAllCourses();
    }

    @RequestMapping("/sendEmailToAllStudentsWhoSelectTheCourse")
    void sendEmailToAllStudentsWhoSelectTheCourse(@RequestParam String courseId, @RequestParam String content) {
        course.sendEmailToAllStudentsWhoSelectTheCourse(courseId, content);
    }
}
