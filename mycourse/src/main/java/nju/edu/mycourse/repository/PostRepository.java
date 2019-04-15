package nju.edu.mycourse.repository;

import javafx.geometry.Pos;
import nju.edu.mycourse.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.StringContent;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByTitleAndAuthorAndPostTime(String title, String author, String postTime);
    Post findByTitleAndAuthorAndContent(String title, String author, String content);
}
