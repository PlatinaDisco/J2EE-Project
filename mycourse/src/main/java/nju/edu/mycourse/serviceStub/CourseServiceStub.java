package nju.edu.mycourse.serviceStub;

import nju.edu.mycourse.model.Course;
import nju.edu.mycourse.repository.CourseRepository;
import nju.edu.mycourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseServiceStub {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseRepository courseRepository;

    private static final String courseName_1 = "软件工程与计算二";
    private static final String courseName_2 = "移动互联网应用开发";
    private static final String courseName_3 = "应用集成原理与工具";
    private static final String courseName_4 = "计算机网络";
    private static final String courseName_5 = "数据库设计与开发";
    private static final String courseName_6 = "软件工程与计算三";
    private static final String subjectName_1 = "软件工程与计算二";
    private static final String subjectName_2 = "移动互联网应用开发";
    private static final String subjectName_3 = "应用集成原理与工具";
    private static final String subjectName_4 = "计算机网络";
    private static final String subjectName_5 = "数据库设计与开发";
    private static final String subjectName_6 = "软件工程与计算三";
    private static final String instructorName_1 = "刘钦";
    private static final String instructorName_2 = "刘峰";
    private static final String instructorName_3 = "刘嘉";
    private static final String semester_1 = "2018-summer";
    private static final String semester_2 = "2018-autumn";
    private static final String semester_3 = "2018-winter";
    private static final String semester_4 = "2019-spring";
    private static final String semester_5 = "2019-summer";
    private static final String classes_1 = "大一班";
    private static final String classes_2 = "大二班";
    private static final String classes_3 = "大班";
    private static final int limitation_1 = 100;
    private static final int limitation_2 = 200;
    private static final String studentId = "161250038";
    private static final String studentName = "何沐声";

    public void test() {
        System.out.println(
                startCourse_1() + System.lineSeparator() +
                startCourse_2() + System.lineSeparator() +
                startCourse_3() + System.lineSeparator() +
                startCourse_4() + System.lineSeparator() +
                startCourse_5() + System.lineSeparator() +
                startCourse_6() + System.lineSeparator() +
                approveCourse_1() + System.lineSeparator() +
                approveCourse_2() + System.lineSeparator() +
                approveCourse_3() + System.lineSeparator() +
                approveCourse_4() + System.lineSeparator() +
                approveCourse_5() + System.lineSeparator() +
                approveCourse_6() + System.lineSeparator() +
                modifyCourse() + System.lineSeparator() +
                selectCourse() + System.lineSeparator() +
                dropCourse_1() + System.lineSeparator() +
                dropCourse_2() + System.lineSeparator() +
                sendEmailToAllStudentWhoSelectTheCourse() + System.lineSeparator() +
                "全部课程: " + showAllCourses() + System.lineSeparator()
        );
    }

    private String startCourse_1() {
        return instructorName_1 + courseService.startCourse(courseName_1, semester_1, classes_1, limitation_1, subjectName_1).toString();
    }
    private String startCourse_2() {
        return instructorName_1 + courseService.startCourse(courseName_2, semester_5, classes_3, limitation_1, subjectName_2).toString();
    }
    private String startCourse_3() {
        return instructorName_2 + courseService.startCourse(courseName_3, semester_5, classes_3, limitation_1, subjectName_3).toString();
    }
    private String startCourse_4() {
        return instructorName_2 + courseService.startCourse(courseName_4, semester_1, classes_1, limitation_1, subjectName_4).toString();
    }
    private String startCourse_5() {
        return instructorName_3 + courseService.startCourse(courseName_5, semester_3, classes_3, limitation_1, subjectName_5).toString();
    }
    private String startCourse_6() {
        return instructorName_3 + courseService.startCourse(courseName_6, semester_2, classes_3, limitation_2, subjectName_6).toString();
    }

    private String approveCourse_1() {
        return courseName_1 + courseService.approveCourse(courseName_1, semester_1).toString();
    }
    private String approveCourse_2() {
        return courseName_2 + courseService.approveCourse(courseName_2, semester_5).toString();
    }
    private String approveCourse_3() {
        return courseName_3 + courseService.approveCourse(courseName_3, semester_5).toString();
    }
    private String approveCourse_4() {
        return courseName_4 + courseService.approveCourse(courseName_4, semester_1).toString();
    }
    private String approveCourse_5() {
        return courseName_5 + courseService.approveCourse(courseName_5, semester_3).toString();
    }
    private String approveCourse_6() {
        return courseName_6 + courseService.approveCourse(courseName_6, semester_2).toString();
    }

    private String modifyCourse() {
        Course course = courseRepository.findByCourseNameAndSemester(courseName_1, semester_1);
        return instructorName_1 + courseService.modifyCourseInfo(String.valueOf(course.getId()), courseName_1, semester_1, classes_1, limitation_1).toString();
    }

    private String selectCourse() {
        StringBuilder result = new StringBuilder();
        for (Course course : courseRepository.findAll()) {
            result.append(studentName).append(courseService.selectCourse(String.valueOf(course.getId()), studentId)).append(System.lineSeparator());
        }
        return result.toString();
    }

    private String dropCourse_1() {
        Course course = courseRepository.findByCourseNameAndSemester(courseName_2, semester_5);
        return studentName + courseService.dropCourse(String.valueOf(course.getId()), studentId).toString();
    }
    private String dropCourse_2() {
        Course course = courseRepository.findByCourseNameAndSemester(courseName_3, semester_5);
        return studentName + courseService.dropCourse(String.valueOf(course.getId()), studentId).toString();
    }

    private String sendEmailToAllStudentWhoSelectTheCourse() {
        for (Course course : courseRepository.findAll()) {
            courseService.sendEmailToAllStudentsWhoSelectTheCourse(String.valueOf(course.getId()), "欢迎选课");
        }
        return "";
    }

    private List<Course> showAllCourses() {
        return courseService.showAllCourses();
    }
}
