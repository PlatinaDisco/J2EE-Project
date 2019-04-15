package nju.edu.mycourse.controller;

import nju.edu.mycourse.model.Instructor;
import nju.edu.mycourse.model.Student;
import nju.edu.mycourse.model.Subject;
import nju.edu.mycourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService user;

    @RequestMapping("/studentRegister")
    public String studentRegister(@RequestParam String njuEmail, @RequestParam String password, @RequestParam String name,
                             @RequestParam String id, @RequestParam String type, @RequestParam String verificationCode){
        return user.studentRegister(njuEmail, password, name, id, type, verificationCode).toString();
    }

    @RequestMapping("/instructorRegister")
    public String instructorRegister(@RequestParam String njuEmail, @RequestParam String password, @RequestParam String name,
                                     @RequestParam String verificationCode){
        return user.instructorRegister(njuEmail, password, name, verificationCode).toString();
    }

    @RequestMapping("/sendVerificationCode")
    public String sendVerificationCode(@RequestParam String email) {
        return user.sendVerificationCode(email).toString();
    }

    @RequestMapping("/login")
    public String login(@RequestParam String njuEmail, @RequestParam String password) {
        return user.login(njuEmail, password).toString();
    }

    @RequestMapping("/logout")
    public String logout(@RequestParam String njuEmail, @RequestParam String password) {
        return user.logout(njuEmail, password).toString();
    }

    @RequestMapping("/updateStudentInfo")
    public String updateStudentInfo(@RequestParam String njuEmail, @RequestParam String password, @RequestParam String name,
                                    @RequestParam String id, @RequestParam String type) {
        return user.updateStudentInfo(njuEmail, password, name, id, type).toString();
    }

    @RequestMapping("/updateInstructorInfo")
    public String updateInstructorInfo(@RequestParam String njuEmail, @RequestParam String password, @RequestParam String name) {
        return user.updateInstructorInfo(njuEmail, password, name).toString();
    }

    @RequestMapping("/showStudentInfo")
    public Student showStudentInfo(@RequestParam String njuEmail) {
        return user.showStudentInfo(njuEmail);
    }

    @RequestMapping("/showInstructorInfo")
    public Instructor showInstructorInfo(@RequestParam String njuEmail) {
        return user.showInstructorInfo(njuEmail);
    }

    @RequestMapping("/showCourseStudents")
    public List<Student> showCourseStudents(@RequestParam String courseId) {
        return user.showCourseStudents(courseId);
    }

    @RequestMapping("/showAllStudents")
    public List<Student> showAllStudents() {
        return user.showAllStudents();
    }

    @RequestMapping("/showAllInstructors")
    public List<Instructor> showAllInstructors() {
        return user.showAllInstructors();
    }
}
