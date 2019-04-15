package nju.edu.mycourse.serviceStub;

import nju.edu.mycourse.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatisticsServiceStub {
    @Autowired
    private StatisticsService statisticsService;

    public void test() {
        System.out.println(
                "学生总人数: " + statisticsService.showAllStudentsNumber() + System.lineSeparator() +
                "教师总人数: " + statisticsService.showAllInstructorsNumber() + System.lineSeparator() +
                "科目总数: " + statisticsService.showAllSubjectsNumber() + System.lineSeparator() +
                "课程总数: " + statisticsService.showAllCoursesNumber() +System.lineSeparator()
        );
    }

}
