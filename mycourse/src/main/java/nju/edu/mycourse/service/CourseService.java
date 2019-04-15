package nju.edu.mycourse.service;

import nju.edu.mycourse.common.resultMessage.CourseResult;
import nju.edu.mycourse.model.Course;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CourseService {
    CourseResult startCourse(String courseName, String semester, String classes, int limitation, String subjectName);
    CourseResult approveCourse(String courseName, String semester);

    CourseResult uploadTestScoreFile(String courseId, MultipartFile multipartFile);
    CourseResult uploadTotalGradeFile(String courseId, MultipartFile multipartFile);
    void downloadScoreFile(String courseId, String filename, HttpServletResponse response);

    CourseResult selectCourse(String courseId, String studentId);
    CourseResult dropCourse(String courseId, String studentId);

    CourseResult modifyCourseInfo(String courseId, String courseName, String semester, String classes, int limit);

    String showCourseSubjectName(String courseId);
    Course showCourse(String courseId);
    List<Course> showStudentCourse(String studentId);
    List<Course> showStudentDroppedCourse(String studentId);
    List<Course> showSubjectCourses(String subjectName);
    List<Course> showSubjectApprovedCourses(String subjectName);
    List<Course> showApprovedCourses();
    List<Course> showUnapprovedCourses();
    List<Course> showAllCourses();

    void sendEmailToAllStudentsWhoSelectTheCourse(String courseId, String content);
}
