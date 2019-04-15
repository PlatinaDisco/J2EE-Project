package nju.edu.mycourse.repository;

import nju.edu.mycourse.model.Post;
import nju.edu.mycourse.model.ReturnPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReturnPostRepository extends JpaRepository<ReturnPost, Long> {
    ReturnPost findByPostAndAuthorAndReplyTime(Post post, String author, String replyTime);
    ReturnPost findByPostAndAuthorAndContent(Post post, String author, String content);
}
