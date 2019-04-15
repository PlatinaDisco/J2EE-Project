package nju.edu.mycourse.repository;

import nju.edu.mycourse.model.Course;
import nju.edu.mycourse.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
    Course findByCourseNameAndSemester(String courseName, String semester);
    List<Course> findBySubject(Subject subject);
}
