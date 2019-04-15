package nju.edu.mycourse.controller;

import nju.edu.mycourse.common.resultMessage.HomeworkResult;
import nju.edu.mycourse.model.Homework;
import nju.edu.mycourse.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/homework")
public class HomeworkController {
    @Autowired
    private HomeworkService homework;

    @RequestMapping("/publishHomework")
    public String publishHomework(@RequestParam String homeworkTitle, @RequestParam String assignmentTime,
                                  @RequestParam String deadline, @RequestParam String homeworkSize,
                                  @RequestParam String homeworkContent, @RequestParam String submitFileType,
                                  @RequestParam String courseId) {
        return homework.publishHomework(homeworkTitle, assignmentTime, deadline, homeworkSize, homeworkContent,
                submitFileType, courseId).toString();
    }

    @RequestMapping("/modifyHomework")
    public String modifyHomework(@RequestParam String homeworkTitle, @RequestParam String assignmentTime,
                                 @RequestParam String deadline, @RequestParam String homeworkSize,
                                 @RequestParam String homeworkContent, @RequestParam String submitFileType,
                                 @RequestParam String homeworkId) {
        return homework.modifyHomework(homeworkTitle, assignmentTime, deadline, homeworkSize, homeworkContent,
                submitFileType, homeworkId).toString();
    }

    @RequestMapping("/submitHomework")
    public String submitHomework(@RequestParam String homeworkId, @RequestParam MultipartFile multipartFile) {
        return homework.submitHomework(homeworkId, multipartFile).toString();
    }

    @RequestMapping("/downloadAllHomework")
    public void downloadAllHomework(@RequestParam String homeworkId, @RequestParam HttpServletResponse response) {
        homework.downloadAllHomework(homeworkId, response);
    }

    @RequestMapping("/uploadHomeworkScore")
    public String uploadHomeworkScore(@RequestParam String homeworkId, @RequestParam CommonsMultipartFile multipartFile,
                                      @RequestParam boolean isOpen) {
        return homework.uploadHomeworkScore(homeworkId, multipartFile, isOpen).toString();
    }

    @RequestMapping("/showHomework")
    public Homework showHomework(@RequestParam String homeworkId) {
        return homework.showHomework(homeworkId);
    }

    @RequestMapping("/showCourseHomework")
    public List<Homework> showCourseHomework(@RequestParam String courseId) {
        return homework.showCourseHomework(courseId);
    }

    @RequestMapping("/showAllHomework")
    public List<Homework> showAllHomework() {
        return homework.showAllHomework();
    }
}
