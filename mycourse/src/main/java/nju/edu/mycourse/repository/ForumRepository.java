package nju.edu.mycourse.repository;

import nju.edu.mycourse.model.Forum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumRepository extends JpaRepository<Forum, Long>{
    Forum findByForumName(String forumName);
}
