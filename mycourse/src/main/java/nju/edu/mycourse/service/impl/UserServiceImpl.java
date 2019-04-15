package nju.edu.mycourse.service.impl;

import nju.edu.mycourse.common.resultMessage.LoginResult;
import nju.edu.mycourse.common.resultMessage.LogoutResult;
import nju.edu.mycourse.common.resultMessage.ModificationResult;
import nju.edu.mycourse.common.resultMessage.RegisterResult;
import nju.edu.mycourse.model.Course;
import nju.edu.mycourse.model.Instructor;
import nju.edu.mycourse.model.Manager;
import nju.edu.mycourse.model.Student;
import nju.edu.mycourse.repository.CourseRepository;
import nju.edu.mycourse.repository.InstructorRepository;
import nju.edu.mycourse.repository.ManagerRepository;
import nju.edu.mycourse.repository.StudentRepository;
import nju.edu.mycourse.service.UserService;
import nju.edu.mycourse.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public RegisterResult studentRegister(String njuEmail, String password, String name, String id, String type, String verificationCode) {
        if(njuEmail != null && njuEmail.contains("@smail.nju.edu.cn")) {
            if(MailUtil.verify(verificationCode)) {
                if(null != studentRepository.findByNjuEmail(njuEmail)) {
                    if (!studentRepository.findByNjuEmail(njuEmail).isLogout()) {
                        return RegisterResult.AccountHasBeenRegistered;
                    }
                    else {
                        Student student = studentRepository.findByNjuEmail(njuEmail);
                        student.setLogout(false);
                        student.setPassword(password);
                        student.setName(name);
                        student.setStudentId(id);
                        student.setType(type);
                        studentRepository.save(student);
                        return RegisterResult.registerSuccess;
                    }
                }
                else {
                    Student student = new Student(njuEmail, password, name, id, type);
                    studentRepository.save(student);
                    return RegisterResult.registerSuccess;
                }
            }
            else {
                return RegisterResult.wrongOrDueVerification;
            }
        }
        return RegisterResult.nullOrWrongEmailFormat;
    }

    @Override
    public RegisterResult instructorRegister(String njuEmail, String password, String name, String verificationCode) {
        if(njuEmail != null && njuEmail.contains("@nju.edu.cn")) {
            if(null != instructorRepository.findByNjuEmail(njuEmail)) {
                return RegisterResult.AccountHasBeenRegistered;
            }
            //MailUtil.verify(verificationCode);
            if (true) {
                Instructor instructor = new Instructor(njuEmail, password, name);
                instructorRepository.save(instructor);
                return RegisterResult.registerSuccess;
            }
            else {
                return RegisterResult.wrongOrDueVerification;
            }
        }
        return RegisterResult.nullOrWrongEmailFormat;
    }

    @Override
    public LoginResult login(String username, String password) {
        if(null != username) {
            if(username.contains("@smail.nju.edu.cn")) {
                Student student = studentRepository.findByNjuEmailAndPassword(username, password);
                if(null != student && !student.isLogout()) {
                    return LoginResult.loginSuccess;
                }
                else {
                    return LoginResult.loginFail;
                }
            }
            else if(username.contains("@nju.edu.cn")) {
                Instructor instructor = instructorRepository.findByNjuEmailAndPassword(username, password);
                return null != instructor ? LoginResult.loginSuccess : LoginResult.loginFail;
            }
            else {
                Manager manager = managerRepository.findByUsernameAndPassword(username, password);
                return null != manager ? LoginResult.loginSuccess : LoginResult.loginFail;
            }
        }
        else {
            return LoginResult.wrongNjuEmailOrPassword;
        }
    }

    @Override
    public LogoutResult logout(String njuEmail, String password) {
        if(null != njuEmail && njuEmail.contains("@smail.nju.edu.cn")) {
            Student student = studentRepository.findByNjuEmailAndPassword(njuEmail, password);
            if(null != student && !student.isLogout()) {
                student.setLogout(true);
                student.setPassword("");
                studentRepository.save(student);
                return LogoutResult.logoutSuccess;
            }
            else {
                return LogoutResult.logoutFail;
            }
        }
        else {
            return LogoutResult.wrongNjuEmailOrPassword;
        }
    }

    @Override
    public RegisterResult sendVerificationCode(String email) {
        if(email !=null && (email.contains("@smail.nju.edu.cn") || email.contains("@nju.edu.cn"))) {
            boolean isSent = MailUtil.sendVerificationCode(email);
            return isSent ? RegisterResult.sendVerificationCodeSuccess : RegisterResult.sendVerificationCodeFail;
        }
        return RegisterResult.nullOrWrongEmailFormat;
    }

    @Override
    public ModificationResult updateStudentInfo(String njuEmail, String password, String name, String id, String type) {
        Student student = studentRepository.findByNjuEmail(njuEmail);
        if(null != student) {
            student.setPassword(password);
            student.setName(name);
            student.setStudentId(id);
            student.setType(type);
            studentRepository.save(student);
            return ModificationResult.userInfoModificationSuccess;
        }
        return ModificationResult.userInfoModificationFail;
    }

    @Override
    public ModificationResult updateInstructorInfo(String njuEmail, String password, String name) {
        Instructor instructor = instructorRepository.findByNjuEmail(njuEmail);
        if(null != instructor) {
            instructor.setPassword(password);
            instructor.setName(name);
            instructorRepository.save(instructor);
            return ModificationResult.userInfoModificationSuccess;
        }
        return ModificationResult.userInfoModificationFail;
    }

    @Override
    public Student showStudentInfo(String njuEmail) {
        return studentRepository.findByNjuEmail(njuEmail);
    }

    @Override
    public Instructor showInstructorInfo(String njuEmail) {
        return instructorRepository.findByNjuEmail(njuEmail);
    }

    @Override
    public List<Student> showCourseStudents(String courseId) {
        Course course = courseRepository.findById(Long.valueOf(courseId)).get();
        return new ArrayList<>(course.getStudents());
    }

    @Override
    public List<Student> showAllStudents() {
        List<Student> students = new ArrayList<>();
        for (Student student : studentRepository.findAll()) {
            if(!student.isLogout()) {
                students.add(student);
            }
        }
        return students;
    }

    @Override
    public List<Instructor> showAllInstructors() {
        return instructorRepository.findAll();
    }
}
