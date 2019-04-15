package nju.edu.mycourse.serviceStub;

import nju.edu.mycourse.model.Subject;
import nju.edu.mycourse.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

@Component
public class SubjectServiceStub {
    @Autowired
    private SubjectService subjectService;

    private static final String instructorEmail_1 = "123456@nju.edu.cn";
    private static final String instructorEmail_2 = "123@nju.edu.cn";
    private static final String instructorEmail_3 = "168@nju.edu.cn";
    private static final String instructorName_1 = "刘钦";
    private static final String instructorName_2 = "刘峰";
    private static final String instructorName_3 = "刘嘉";
    private static final String subjectName_1 = "软件工程与计算二";
    private static final String subjectName_2 = "移动互联网应用开发";
    private static final String subjectName_3 = "应用集成原理与工具";
    private static final String subjectName_4 = "计算机网络";
    private static final String subjectName_5 = "数据库设计与开发";
    private static final String subjectName_6 = "软件工程与计算三";

    public void test() {
        System.out.println(
                startSubject_1() + System.lineSeparator() +
                startSubject_2() + System.lineSeparator() +
                startSubject_3() + System.lineSeparator() +
                startSubject_4() + System.lineSeparator() +
                startSubject_5() + System.lineSeparator() +
                startSubject_6() + System.lineSeparator() +
                approveSubject_1() + System.lineSeparator() +
                approveSubject_2() + System.lineSeparator() +
                approveSubject_3() + System.lineSeparator() +
                approveSubject_4() + System.lineSeparator() +
                approveSubject_5() + System.lineSeparator() +
                approveSubject_6() + System.lineSeparator() +
                subjectName_1 + ": " + showSubject() + System.lineSeparator() +
                instructorName_1 + ": " + showInstructorSubjects() + System.lineSeparator()
        );
    }

    private String startSubject_1() {
        return instructorName_1 + subjectService.startSubject(instructorEmail_1, subjectName_1).toString();
    }

    private String startSubject_2() { return instructorName_1 + subjectService.startSubject(instructorEmail_1, subjectName_2).toString(); }

    private String startSubject_3() { return instructorName_2 + subjectService.startSubject(instructorEmail_2, subjectName_3).toString(); }

    private String startSubject_4() { return instructorName_2 + subjectService.startSubject(instructorEmail_2, subjectName_4).toString(); }

    private String startSubject_5() { return instructorName_3 + subjectService.startSubject(instructorEmail_3, subjectName_5).toString(); }

    private String startSubject_6() { return instructorName_3 + subjectService.startSubject(instructorEmail_3, subjectName_6).toString(); }

    private String approveSubject_1() {
        return subjectName_1 + subjectService.approveSubject(subjectName_1).toString();
    }

    private String approveSubject_2() { return subjectName_2 + subjectService.approveSubject(subjectName_2).toString(); }

    private String approveSubject_3() { return subjectName_3 + subjectService.approveSubject(subjectName_3).toString(); }

    private String approveSubject_4() { return subjectName_4 + subjectService.approveSubject(subjectName_4).toString(); }

    private String approveSubject_5() { return subjectName_5 + subjectService.approveSubject(subjectName_5).toString(); }

    private String approveSubject_6() { return subjectName_6 + subjectService.approveSubject(subjectName_6).toString(); }

    private Subject showSubject() { return subjectService.showSubject(subjectName_1); }

    private List<Subject> showInstructorSubjects() { return subjectService.showInstructorSubjects(instructorEmail_1); }

}
