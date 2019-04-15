package nju.edu.mycourse.repository;

import nju.edu.mycourse.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
    Student findByNjuEmailAndPassword(String njuEmail, String password);
    Student findByNjuEmail(String njuEmail);
    Student findByStudentId(String studentId);
}
