package nju.edu.mycourse.service;

import nju.edu.mycourse.common.resultMessage.LoginResult;
import nju.edu.mycourse.common.resultMessage.LogoutResult;
import nju.edu.mycourse.common.resultMessage.ModificationResult;
import nju.edu.mycourse.common.resultMessage.RegisterResult;
import nju.edu.mycourse.model.Instructor;
import nju.edu.mycourse.model.Student;

import java.util.List;

public interface UserService {
    RegisterResult studentRegister(String njuEmail, String password, String name, String id, String type, String verificationCode);
    RegisterResult instructorRegister(String njuEmail, String password, String name, String verificationCode);
    LoginResult login(String username, String password);
    LogoutResult logout(String njuEmail, String password);
    RegisterResult sendVerificationCode(String email);

    ModificationResult updateStudentInfo(String njuEmail, String password, String name, String id, String type);
    ModificationResult updateInstructorInfo(String njuEmail, String password, String name);

    Student showStudentInfo(String njuEmail);
    Instructor showInstructorInfo(String njuEmail);

    List<Student> showCourseStudents(String courseId);

    List<Student> showAllStudents();
    List<Instructor> showAllInstructors();
}
