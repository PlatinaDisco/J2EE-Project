package nju.edu.mycourse.service;

import nju.edu.mycourse.common.resultMessage.HomeworkResult;
import nju.edu.mycourse.model.Homework;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface HomeworkService {
    HomeworkResult publishHomework(String homeworkTitle, String assignmentTime, String deadline, String homeworkSize, String homeworkContent,
                                   String submitFileType, String courseId);

    HomeworkResult modifyHomework(String homeworkTitle, String assignmentTime, String deadline, String homeworkSize, String homeworkContent,
                                  String submitFileType, String homeworkId);

    HomeworkResult submitHomework(String homeworkId, MultipartFile multipartFile);

    void downloadAllHomework(String homeworkId, HttpServletResponse response);

    HomeworkResult uploadHomeworkScore(String homeworkId, CommonsMultipartFile multipartFile, boolean isOpen);

    Homework showHomework(String homeworkId);
    List<Homework> showCourseHomework(String courseId);
    List<Homework> showAllHomework();

}
