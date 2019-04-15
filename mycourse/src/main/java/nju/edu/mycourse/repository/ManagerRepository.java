package nju.edu.mycourse.repository;

import nju.edu.mycourse.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long>{
    Manager findByUsernameAndPassword(String username, String password);
}
