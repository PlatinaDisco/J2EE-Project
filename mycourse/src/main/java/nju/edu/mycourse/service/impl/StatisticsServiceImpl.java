package nju.edu.mycourse.service.impl;

import nju.edu.mycourse.common.bean.CourseByInstructor;
import nju.edu.mycourse.common.bean.CourseBySemester;
import nju.edu.mycourse.model.Course;
import nju.edu.mycourse.model.Instructor;
import nju.edu.mycourse.model.Student;
import nju.edu.mycourse.model.Subject;
import nju.edu.mycourse.repository.InstructorRepository;
import nju.edu.mycourse.repository.StudentRepository;
import nju.edu.mycourse.service.CourseService;
import nju.edu.mycourse.service.StatisticsService;
import nju.edu.mycourse.service.SubjectService;
import nju.edu.mycourse.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.soap.Addressing;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private SubjectService subjectService;

    @Override
    public List<CourseBySemester> showInstructorCoursesBySemester(String instructorEmail) {
        Instructor instructor = instructorRepository.findByNjuEmail(instructorEmail);
        Set<String> semesters = new HashSet<>();
        for (Subject subject : instructor.getSubjects()) {
            for (Course course : subject.getCourses()) {
                semesters.add(course.getSemester());
            }
        }
        List<CourseBySemester> courseBySemesterList = new ArrayList<>();
        for (String semester : semesters) {
            CourseBySemester courseBySemester = new CourseBySemester(semester);
            for (Subject subject : instructor.getSubjects()) {
                for(Course course : subject.getCourses()) {
                    if(course.getSemester().equals(semester)) {
                        courseBySemester.getCourseList().add(course);
                    }
                }
            }
            courseBySemesterList.add(courseBySemester);
        }
        return courseBySemesterList;
    }

    @Override
    public List<CourseBySemester> showStudentCoursesBySemester(String studentId) {
        Student student = studentRepository.findByStudentId(studentId);
        Set<String> semesters = new HashSet<>();
        for (Course course : student.getCourses()) {
            semesters.add(course.getSemester());
        }
        List<CourseBySemester> courseBySemesterList = new ArrayList<>();
        for (String semester : semesters) {
            CourseBySemester courseBySemester = new CourseBySemester(semester);
            for(Course course : student.getCourses()) {
                if(course.getSemester().equals(semester)) {
                    courseBySemester.getCourseList().add(course);
                }
            }
            courseBySemesterList.add(courseBySemester);
        }
        return courseBySemesterList;
    }

    @Override
    public List<CourseByInstructor> showStudentCoursesByInstructor(String studentId) {
        Student student = studentRepository.findByStudentId(studentId);
        Set<String> instructors = new HashSet<>();
        for (Course course : student.getCourses()) {
            instructors.add(course.getInstructorName());
        }
        List<CourseByInstructor> courseByInstructorList = new ArrayList<>();
        for (String instructor : instructors) {
            CourseByInstructor courseByInstructor = new CourseByInstructor(instructor);
            for(Course course : student.getCourses()) {
                if(course.getInstructorName().equals(instructor)) {
                    courseByInstructor.getCourseList().add(course);
                }
            }
            courseByInstructorList.add(courseByInstructor);
        }
        return courseByInstructorList;
    }

    @Override
    public int showAllStudentsNumber() {
        return userService.showAllStudents().size();
    }

    @Override
    public int showAllInstructorsNumber() {
        return userService.showAllInstructors().size();
    }

    @Override
    public int showAllSubjectsNumber() {
        return subjectService.showAllSubjects().size();
    }

    @Override
    public int showAllCoursesNumber() {
        return courseService.showAllCourses().size();
    }
}
