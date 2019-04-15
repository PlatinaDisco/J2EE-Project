package nju.edu.mycourse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "returnposttbl")
public class ReturnPost implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String content; //回复帖内容
    @Column(nullable = false)
    private String replyTime; //恢复时间
    @Column(nullable = false)
    private String author; //回复者
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private Set<String> comments; //回复帖评论
    @ManyToOne(optional = true)
    @JoinColumn(name = "returnPostOwner")
    @JsonIgnore
    private Post post; //回复帖所对应的主帖

    public ReturnPost() {}

    public ReturnPost(Post post, String content, String author, String replyTime) {
        this.post = post;
        this.content = content;
        this.replyTime = replyTime;
        this.author = author;
        this.comments = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Set<String> getComments() {
        return comments;
    }

    public void setComments(Set<String> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReturnPost that = (ReturnPost) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(content, that.content) &&
                Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, content, author);
    }
}
