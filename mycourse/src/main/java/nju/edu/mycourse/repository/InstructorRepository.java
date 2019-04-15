package nju.edu.mycourse.repository;

import nju.edu.mycourse.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long>{
    Instructor findByNjuEmailAndPassword(String njuEmail, String password);
    Instructor findByNjuEmail(String njuEmail);
}
