package nju.edu.mycourse.service.impl;

import nju.edu.mycourse.common.resultMessage.ApprovalResult;
import nju.edu.mycourse.common.resultMessage.SubjectResult;
import nju.edu.mycourse.model.Course;
import nju.edu.mycourse.model.Forum;
import nju.edu.mycourse.model.Instructor;
import nju.edu.mycourse.model.Subject;
import nju.edu.mycourse.repository.ForumRepository;
import nju.edu.mycourse.repository.InstructorRepository;
import nju.edu.mycourse.repository.SubjectRepository;
import nju.edu.mycourse.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    private static final String SUBJECT_ROOT = "static/subject/";

    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public SubjectResult startSubject(String instructorEmail, String subjectName) {
        Instructor instructor = instructorRepository.findByNjuEmail(instructorEmail);
        if(null != instructor) {
            if(null != subjectRepository.findBySubjectName(subjectName)) {
                return SubjectResult.subjectHasBeenStarted;
            }
            Subject subject = new Subject(subjectName, instructor);
            instructor.getSubjects().add(subject);
            instructorRepository.save(instructor);
            return SubjectResult.subjectStartSuccess;
        }
        return SubjectResult.subjectStartFail;
    }

    @Override
    public SubjectResult approveSubject(String subjectName) {
        final String forumName = "论坛";
        Subject subject = subjectRepository.findBySubjectName(subjectName);
        if(null != subject && subject.getApprovedResult().equals(ApprovalResult.disapprove.toString())) {
            subject.setApprovedResult(ApprovalResult.approve.toString());
            Forum forum = new Forum(subject, subjectName + forumName);
            subject.setForum(forum);
            subjectRepository.save(subject);
            return SubjectResult.subjectApproved;
        }
        return SubjectResult.subjectApprovedFail;
    }

    @Override
    public SubjectResult uploadSubjectMaterial(String subjectName, MultipartFile multipartFile) {
        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            File subjectDir = new File(path.getAbsolutePath(), SUBJECT_ROOT + subjectName);
            if (!subjectDir.exists()) subjectDir.mkdirs();
            String uploadPath = subjectDir + "\\";
            File file = new File(uploadPath + multipartFile.getOriginalFilename());
            multipartFile.transferTo(file);
            Subject subject = subjectRepository.findBySubjectName(subjectName);
            int index = file.getPath().indexOf("subject");
            String filePath = file.getPath().substring(index-1);
            subject.getMaterialUrls().add(filePath);
            subjectRepository.save(subject);
            return SubjectResult.subjectMaterialUploadSuccess;
        } catch (IOException ex) {
            ex.printStackTrace();
            return SubjectResult.subjectMaterialUploadFail;
        }
    }

    @Override
    public void downloadSubjectMaterial(String filePath, HttpServletResponse response) {
        if(null != filePath&& !filePath.equals("")) {
            try {
                File file = ResourceUtils.getFile(filePath);
                byte[] buffer = new byte[1024];
                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                System.out.println("下载成功");
                bis.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("课程名称为空或者课件名称为空");
    }

    @Override
    public String showSubjectInstructorName(String subjectName) {
        Subject subject = subjectRepository.findBySubjectName(subjectName);
        return subject.getInstructor().getName();
    }

    @Override
    public Subject showSubject(String subjectName) {
        return subjectRepository.findBySubjectName(subjectName);
    }

    @Override
    public List<Subject> showInstructorSubjects(String instructorEmail) {
        Instructor instructor = instructorRepository.findByNjuEmail(instructorEmail);
        if(null != instructor) {
            return subjectRepository.findByInstructor(instructor);
        }
        return null;
    }

    @Override
    public List<Subject> showApprovedSubjects() {
        List<Subject> approvedSubjects = new ArrayList<>();
        List<Subject> allSubjects = subjectRepository.findAll();
        for(Subject subject : allSubjects) {
            if(subject.getApprovedResult().equals(ApprovalResult.approve.toString())) {
                approvedSubjects.add(subject);
            }
        }
        return approvedSubjects;
    }

    @Override
    public List<Subject> showUnapprovedSubjects() {
        List<Subject> unapprovedSubjects = new ArrayList<>();
        List<Subject> allSubjects = subjectRepository.findAll();
        for(Subject subject : allSubjects) {
            if(subject.getApprovedResult().equals(ApprovalResult.disapprove.toString())) {
                unapprovedSubjects.add(subject);
            }
        }
        return unapprovedSubjects;
    }

    @Override
    public List<Subject> showAllSubjects() {
        return subjectRepository.findAll();
    }
}
