package nju.edu.mycourse.controller;

import nju.edu.mycourse.model.Subject;
import nju.edu.mycourse.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @RequestMapping("/startSubject")
    public String startSubject(@RequestParam String instructorEmail, @RequestParam String subjectName) {
        return subjectService.startSubject(instructorEmail, subjectName).toString();
    }

    @RequestMapping("/approveSubject")
    public String approveSubject(@RequestParam String subjectName) {
        return subjectService.approveSubject(subjectName).toString();
    }

    @RequestMapping("/uploadSubjectMaterial")
    public String uploadSubjectMaterial(@RequestParam String subjectName, @RequestParam MultipartFile multipartFile) {
        return subjectService.uploadSubjectMaterial(subjectName, multipartFile).toString();
    }

    @RequestMapping(value = {"/downloadSubjectMaterial/{filePath}",},method=RequestMethod.GET)
    public void downloadSubjectMaterial(@PathVariable("filePath") String filePath, @RequestParam HttpServletResponse response) {
        subjectService.downloadSubjectMaterial(filePath, response);
    }

    @RequestMapping("/showSubjectInstructorName")
    public String showSubjectInstructorName(@RequestParam String subjectName) {
        return subjectService.showSubjectInstructorName(subjectName);
    }

    @RequestMapping("/showSubject")
    public Subject showSubject(@RequestParam String subjectName) {
        return subjectService.showSubject(subjectName);
    }

    @RequestMapping("/showInstructorSubjects")
    public List<Subject> showInstructorSubjects(@RequestParam String instructorEmail) {
        return subjectService.showInstructorSubjects(instructorEmail);
    }

    @RequestMapping("/showApprovedSubjects")
    public List<Subject> showApprovedSubjects() {
        return subjectService.showApprovedSubjects();
    }

    @RequestMapping("/showUnapprovedSubjects")
    public List<Subject> showUnapprovedSubjects() {
        return subjectService.showUnapprovedSubjects();
    }

    @RequestMapping("/showAllSubjects")
    public List<Subject> showAllSubjects() {
        return subjectService.showAllSubjects();
    }
}
