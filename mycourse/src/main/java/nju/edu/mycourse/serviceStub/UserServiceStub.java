package nju.edu.mycourse.serviceStub;

import nju.edu.mycourse.common.type.StudentType;
import nju.edu.mycourse.model.Instructor;
import nju.edu.mycourse.model.Student;
import nju.edu.mycourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceStub {
    @Autowired
    private UserService userService;
    private static final String studentEmail = "161250038@smail.nju.edu.cn";
    private static final String instructorEmail_1 = "123456@nju.edu.cn";
    private static final String instructorEmail_2 = "123@nju.edu.cn";
    private static final String instructorEmail_3 = "168@nju.edu.cn";
    private static final String password = "123456";
    private static final String studentName = "何沐声";
    private static final String studentId = "161250038";
    private static final String instructorName_1 = "刘钦";
    private static final String instructorName_2 = "刘峰";
    private static final String instructorName_3 = "刘嘉";
    private static final String studentType = StudentType.undergraduate.toString();

    public void test() {
        System.out.println(
//                studentRegister() + System.lineSeparator() +
                instructorRegister_1() + System.lineSeparator() +
                instructorRegister_2() + System.lineSeparator() +
                instructorRegister_3() + System.lineSeparator()
//                login_1() + System.lineSeparator() +
//                login_2() + System.lineSeparator() +
//                login_3() + System.lineSeparator() +
//                login_4() + System.lineSeparator() +
//                sendVerificationCodes() + System.lineSeparator() +
//                updateStudentInfo() + System.lineSeparator() +
//                updateInstructorInfo() + System.lineSeparator() +
//                studentName + ": " + showStudentInfo() + System.lineSeparator() +
//                instructorName_1 + ": " + showInstructorInfo() + System.lineSeparator()
        );

    }

    private String studentRegister() {
        return studentName + userService.studentRegister(studentEmail, password, studentName, studentId, studentType, "").toString();
    }

    private String instructorRegister_1() {
        return instructorName_1 + userService.instructorRegister(instructorEmail_1, password, instructorName_1, "").toString();
    }

    private String instructorRegister_2() {
        return instructorName_2 + userService.instructorRegister(instructorEmail_2, password, instructorName_2, "").toString();
    }

    private String instructorRegister_3() {
        return instructorName_3 + userService.instructorRegister(instructorEmail_3, password, instructorName_3, "").toString();
    }

    private String login_1() {
        return studentName + userService.login(studentEmail, password).toString();
    }

    private String login_2() {
        return instructorName_1 + userService.login(instructorEmail_1, password).toString();
    }

    private String login_3() {
        return instructorName_2 + userService.login(instructorEmail_2, password).toString();
    }

    private String login_4() {
        return instructorName_3 + userService.login(instructorEmail_3, password).toString();
    }

    private String sendVerificationCodes() {
        return userService.sendVerificationCode(studentEmail).toString();
    }

    private String updateStudentInfo() {
        return studentName + userService.updateStudentInfo(studentEmail, "123456", "何沐声", "161250038", studentType).toString();
    }

    private String updateInstructorInfo() {
        return instructorName_1 + userService.updateInstructorInfo(instructorEmail_1, "123456", "刘钦").toString();
    }

    private Student showStudentInfo() {
        return userService.showStudentInfo(studentEmail);
    }

    private Instructor showInstructorInfo() {
        return userService.showInstructorInfo(instructorEmail_1);
    }
}
