package nju.edu.mycourse.service.impl;

import nju.edu.mycourse.common.resultMessage.CourseResult;
import nju.edu.mycourse.common.resultMessage.HomeworkResult;
import nju.edu.mycourse.model.Course;
import nju.edu.mycourse.model.Homework;
import nju.edu.mycourse.repository.CourseRepository;
import nju.edu.mycourse.repository.HomeworkRepository;
import nju.edu.mycourse.service.HomeworkService;
import nju.edu.mycourse.util.ZipUtil;
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
public class HomeworkServiceImpl implements HomeworkService {
    private static final String HOMEWORK_ROOT = "static/homework";

    @Autowired
    private HomeworkRepository homeworkRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public HomeworkResult publishHomework(String homeworkTitle, String assignmentTime, String deadline, String homeworkSize,
                                          String homeworkContent, String submitFileType, String courseId) {
        if(null != assignmentTime && null != deadline && null != homeworkSize && null != homeworkContent
                && null != submitFileType && null != courseId) {
            Course course = courseRepository.findById(Long.valueOf(courseId)).get();
            Homework hwk = new Homework(homeworkTitle, assignmentTime, deadline, homeworkSize, homeworkContent, submitFileType, course);
            course.getHomeworkList().add(hwk);
            courseRepository.save(course);
            return HomeworkResult.homeworkPublishSuccess;
        }
        return HomeworkResult.homeworkPublishFail;
    }

    @Override
    public HomeworkResult modifyHomework(String homeworkTitle, String assignmentTime, String deadline, String homeworkSize,
                                         String homeworkContent, String submitFileType, String homeworkId) {
        if(null != assignmentTime && null != deadline && null != homeworkSize && null != homeworkContent
                && null != submitFileType && null != homeworkId) {
            Homework hwk = homeworkRepository.findById(Long.valueOf(homeworkId)).get();
            hwk.setHomeworkTitle(homeworkTitle);
            hwk.setAssignmentTime(assignmentTime);
            hwk.setDeadline(deadline);
            hwk.setHomeworkSize(homeworkSize);
            hwk.setHomeworkContent(homeworkContent);
            hwk.setSubmitFileType(submitFileType);
            homeworkRepository.save(hwk);
            return HomeworkResult.homeworkModifySuccess;
        }
        return HomeworkResult.homeworkModifyFail;
    }

    @Override
    public HomeworkResult submitHomework(String homeworkId, MultipartFile multipartFile) {
        Homework homework = homeworkRepository.findById(Long.valueOf(homeworkId)).get();
        Course course = homework.getCourse();
        String hwkDirName = course.getCourseName() + "_" + course.getSemester() + "_" + homework.getHomeworkTitle();
        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            File hwkDir = new File(path.getAbsolutePath(), HOMEWORK_ROOT + hwkDirName);
            if (!hwkDir.exists()) hwkDir.mkdirs();
            String uploadPath = hwkDir + "\\";
            File file = new File(uploadPath + multipartFile.getOriginalFilename());
            multipartFile.transferTo(file);
            int index = file.getPath().indexOf("homework");
            String filePath = file.getPath().substring(index-1);
            homework.getHomeworkUrls().add(filePath);
            homeworkRepository.save(homework);
            return HomeworkResult.homeworkSubmitSuccess;
        } catch (IOException ex) {
            ex.printStackTrace();
            return HomeworkResult.homeworkSubmitFail;
        }
    }

    @Override
    public void downloadAllHomework(String homeworkId, HttpServletResponse response) {
        Homework homework = homeworkRepository.findById(Long.valueOf(homeworkId)).get();
        Course course = homework.getCourse();
        String hwkDirName = course.getCourseName() + "_" + course.getSemester() + "_" + homework.getHomeworkTitle();
        try {
            File base = new File(ResourceUtils.getURL("classpath:").getPath());
            File hwkDir = new File(base.getAbsolutePath(), HOMEWORK_ROOT + hwkDirName + ".zip");
            if (!hwkDir.exists()) hwkDir.mkdirs();
            File hwkZip = new File(hwkDir + ".zip");
            if(!hwkZip.exists()) {
                FileOutputStream fos = new FileOutputStream(hwkZip);
                ZipUtil.toZip(hwkDir.getAbsolutePath(), fos, true);
            }
            if(!hwkZip.exists()) {
                System.out.println("压缩失败");
                return;
            }
            responseFile(response, hwkZip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
     * 返回文件
     * */
    private void responseFile(HttpServletResponse response, File file) {
        try (InputStream inputStream = new FileInputStream(file);
             OutputStream outputStream = response.getOutputStream();) {
            byte[] buffer = new byte[1024]; // 图片文件流缓存池
            while (inputStream.read(buffer) != -1) {
                outputStream.write(buffer);
            }
            //System.out.println("Demo.responseFile");
            outputStream.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public HomeworkResult uploadHomeworkScore(String homeworkId, CommonsMultipartFile multipartFile, boolean isOpen) {
        Homework homework = homeworkRepository.findById(Long.valueOf(homeworkId)).get();
        Course course = homework.getCourse();
        String hwkDirName = course.getCourseName() + "_" + course.getSemester() + "_" + homework.getHomeworkTitle();
        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            File hwkDir = new File(path.getAbsolutePath(), HOMEWORK_ROOT + hwkDirName);
            if (!hwkDir.exists()) hwkDir.mkdirs();
            String uploadPath = hwkDir + "\\";
            File file = new File(uploadPath + multipartFile.getOriginalFilename());
            multipartFile.transferTo(file);
            homework.setHomeworkScoreFileUrl(file.getAbsolutePath());
            homeworkRepository.save(homework);
            return HomeworkResult.homeworkScoreUploadSuccess;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return HomeworkResult.homeworkScoreUploadFail;
        }
    }

    @Override
    public Homework showHomework(String homeworkId) {
        return homeworkRepository.findById(Long.valueOf(homeworkId)).get();
    }

    @Override
    public List<Homework> showCourseHomework(String courseId) {
        Course course = courseRepository.findById(Long.valueOf(courseId)).get();
        List<Homework> homeworkList = new ArrayList<>(course.getHomeworkList());
        return homeworkList;
    }

    @Override
    public List<Homework> showAllHomework() {
        return homeworkRepository.findAll();
    }
}
