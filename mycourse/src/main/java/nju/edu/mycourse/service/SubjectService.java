package nju.edu.mycourse.service;

import nju.edu.mycourse.common.resultMessage.SubjectResult;
import nju.edu.mycourse.model.Subject;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface SubjectService {
    SubjectResult startSubject(String instructorEmail, String subjectName);
    SubjectResult approveSubject(String subjectName);

    SubjectResult uploadSubjectMaterial(String subjectName, MultipartFile multipartFile);
    void downloadSubjectMaterial(String filePath, HttpServletResponse response);

    String showSubjectInstructorName(String subjectName);
    Subject showSubject(String subjectName);
    List<Subject> showInstructorSubjects(String instructorEmail);
    List<Subject> showApprovedSubjects();
    List<Subject> showUnapprovedSubjects();
    List<Subject> showAllSubjects();
}
