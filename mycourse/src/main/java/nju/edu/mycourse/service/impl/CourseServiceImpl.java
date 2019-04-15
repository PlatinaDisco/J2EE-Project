package nju.edu.mycourse.service.impl;

import nju.edu.mycourse.common.resultMessage.ApprovalResult;
import nju.edu.mycourse.common.resultMessage.CourseResult;
import nju.edu.mycourse.common.resultMessage.SubjectResult;
import nju.edu.mycourse.model.Course;
import nju.edu.mycourse.model.Instructor;
import nju.edu.mycourse.model.Student;
import nju.edu.mycourse.model.Subject;
import nju.edu.mycourse.repository.CourseRepository;
import nju.edu.mycourse.repository.InstructorRepository;
import nju.edu.mycourse.repository.StudentRepository;
import nju.edu.mycourse.repository.SubjectRepository;
import nju.edu.mycourse.service.CourseService;
import nju.edu.mycourse.util.MailUtil;
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
public class CourseServiceImpl implements CourseService {
    private static final String COURSE_ROOT = "static/course/";

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public CourseResult startCourse(String courseName, String semester, String classes, int limitation, String subjectName) {
        Subject subject = subjectRepository.findBySubjectName(subjectName);
        if(null != subject) {
            if(null != courseRepository.findByCourseNameAndSemester(courseName, semester)) {
                return CourseResult.courseHasBeenStarted;
            }
            Course course = new Course(courseName, semester, classes, limitation, subject, subject.getInstructor().getName());
            subject.getCourses().add(course);
            subjectRepository.save(subject);
            return CourseResult.courseStartSuccess;
        }
        return CourseResult.courseStartFail;
    }

    @Override
    public CourseResult approveCourse(String courseName, String semester) {
        Course course = courseRepository.findByCourseNameAndSemester(courseName, semester);
        if(null != course && course.getApproveResult().equals(ApprovalResult.disapprove.toString())) {
            course.setApproveResult(ApprovalResult.approve.toString());
            courseRepository.save(course);
            return CourseResult.courseApproved;
        }
        return CourseResult.courseApprovedFail;
    }

    @Override
    public CourseResult uploadTestScoreFile(String courseId, MultipartFile multipartFile) {
        Course course = courseRepository.findById(Long.valueOf(courseId)).get();
        String courseNameAndSemester = course.getCourseName() + "_" + course.getSemester();
        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            File courseDir = new File(path.getAbsolutePath(), COURSE_ROOT + courseNameAndSemester);
            if (!courseDir.exists()) courseDir.mkdirs();
            String uploadPath = courseDir + "\\";
            File file = new File(uploadPath + multipartFile.getOriginalFilename());
            multipartFile.transferTo(file);
            int index = file.getPath().indexOf("course");
            String filePath = file.getPath().substring(index-1);
            course.setTestScoreFileUrl(filePath);
            courseRepository.save(course);
            return CourseResult.testScoreFileUploadSuccess;
        } catch (IOException ex) {
            ex.printStackTrace();
            return CourseResult.testScoreFileUploadFail;
        }
    }

    @Override
    public CourseResult uploadTotalGradeFile(String courseId, MultipartFile multipartFile) {
        Course course = courseRepository.findById(Long.valueOf(courseId)).get();
        String courseNameAndSemester = course.getCourseName() + "_" + course.getSemester();
        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            File subjectDir = new File(path.getAbsolutePath(), COURSE_ROOT + courseNameAndSemester);
            if (!subjectDir.exists()) subjectDir.mkdirs();
            String uploadPath = subjectDir + "\\";
            File file = new File(uploadPath + multipartFile.getOriginalFilename());
            multipartFile.transferTo(file);
            int index = file.getPath().indexOf("course");
            String filePath = file.getPath().substring(index-1);
            course.setTotalGradeFileUrl(filePath);
            courseRepository.save(course);
            return CourseResult.testScoreFileUploadSuccess;
        } catch (IOException ex) {
            ex.printStackTrace();
            return CourseResult.testScoreFileUploadFail;
        }
    }

    @Override
    public void downloadScoreFile(String courseId, String filename, HttpServletResponse response) {
        Course course = courseRepository.findById(Long.valueOf(courseId)).get();
        String courseNameAndSemester = course.getCourseName() + "_" + course.getSemester();
        try {
            File file = ResourceUtils.getFile("classpath:static/course/" + courseNameAndSemester + "/" + filename);
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
            System.out.println("课程名称为空或者课件名称为空");
        }
    }

    @Override
    public CourseResult selectCourse(String courseId, String studentId) {
        Student student = studentRepository.findByStudentId(studentId);
        Course course = courseRepository.findById(Long.valueOf(courseId)).get();
        if(course.getStudents().size() == course.getLimitation()) {
            return CourseResult.courseLimitationReachesMax;
        }
        if(null != student) {
            if(course.getStudents().contains(student)) {
                return CourseResult.courseHasBeenSelected;
            }
            else {
                student.getCourses().add(course);
                course.getStudents().add(student);
                studentRepository.save(student);
                return CourseResult.courseSelectSuccess;
            }
        }
        return CourseResult.courseSelectFail;
    }

    @Override
    public CourseResult dropCourse(String courseId, String studentId) {
        Student student = studentRepository.findByStudentId(studentId);
        Course course = courseRepository.findById(Long.valueOf(courseId)).get();
        if(null != student && student.getCourses().contains(course)) {
            student.getCourses().remove(course);
            student.getDroppedCourses().add(course);
            course.getStudents().remove(student);
            studentRepository.save(student);
            return CourseResult.courseDropSuccess;
        }
        return CourseResult.courseDropFail;
    }

    @Override
    public CourseResult modifyCourseInfo(String courseId, String courseName, String semester, String classes, int limit) {
        Course course = courseRepository.findById(Long.valueOf(courseId)).get();
        course.setCourseName(courseName);
        course.setSemester(semester);
        course.setClasses(classes);
        course.setLimitation(limit);
        courseRepository.save(course);
        return CourseResult.courseInfoModificationSuccess;
    }

    @Override
    public String showCourseSubjectName(String courseId) {
        Course course = courseRepository.findById(Long.valueOf(courseId)).get();
        return course.getSubject().getSubjectName();
    }

    @Override
    public Course showCourse(String courseId) {
        return courseRepository.findById(Long.valueOf(courseId)).get();
    }

    @Override
    public List<Course> showStudentCourse(String studentId) {
        Student student = studentRepository.findByStudentId(studentId);
        return new ArrayList<>(student.getCourses());
    }

    @Override
    public List<Course> showStudentDroppedCourse(String studentId) {
        Student student = studentRepository.findByStudentId(studentId);
        return new ArrayList<>(student.getDroppedCourses());
    }

    @Override
    public List<Course> showSubjectCourses(String subjectName) {
        Subject subject = subjectRepository.findBySubjectName(subjectName);
        if(null != subject) {
            return courseRepository.findBySubject(subject);
        }
        return null;
    }

    @Override
    public List<Course> showSubjectApprovedCourses(String subjectName) {
        Subject subject = subjectRepository.findBySubjectName(subjectName);
        List<Course> approvedCourses = new ArrayList<>();
        for(Course course : subject.getCourses()) {
            if(course.getApproveResult().equals(ApprovalResult.approve.toString())) {
                approvedCourses.add(course);
            }
        }
        return approvedCourses;
    }

    @Override
    public List<Course> showApprovedCourses() {
        List<Course> allCourses = courseRepository.findAll();
        List<Course> unapprovedCourses = new ArrayList<>();
        for(Course course : allCourses) {
            if(course.getApproveResult().equals(ApprovalResult.approve.toString())) {
                unapprovedCourses.add(course);
            }
        }
        return unapprovedCourses;
    }

    @Override
    public List<Course> showUnapprovedCourses() {
        List<Course> allCourses = courseRepository.findAll();
        List<Course> unapprovedCourses = new ArrayList<>();
        for(Course course : allCourses) {
            if(course.getApproveResult().equals(ApprovalResult.disapprove.toString())) {
                unapprovedCourses.add(course);
            }
        }
        return unapprovedCourses;
    }

    @Override
    public List<Course> showAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public void sendEmailToAllStudentsWhoSelectTheCourse(String courseId, String content) {
        Course course = courseRepository.findById(Long.valueOf(courseId)).get();
        List<Student> studentList = new ArrayList<>(course.getStudents());
        String author = course.getSubject().getInstructor().getName();
        for (Student student : studentList) {
            MailUtil.sendEmail(student.getNjuEmail(), author, student.getName(), content);
        }
    }
}
