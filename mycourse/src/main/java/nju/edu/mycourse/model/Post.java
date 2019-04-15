package nju.edu.mycourse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "posttbl")
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "postOwner")
    @JsonIgnore
    private Forum forum; //所属论坛
    @Column(nullable = false)
    private String title; //帖子主题
    @Column(nullable = false)
    private String content; //帖子内容
    @Column(nullable = false)
    private String author; //发帖人
    @Column(nullable = false)
    private String postTime; //发帖时间
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy(value="id ASC")
    private Set<ReturnPost> returnPosts; //回复帖

    public Post() {}

    public Post(Forum forum, String title, String content, String author, String postTime) {
        this.forum = forum;
        this.title = title;
        this.content = content;
        this.author = author;
        this.postTime = postTime;
        this.returnPosts = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public Set<ReturnPost> getReturnPosts() {
        return returnPosts;
    }

    public void setReturnPosts(Set<ReturnPost> returnPosts) {
        this.returnPosts = returnPosts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) &&
                Objects.equals(title, post.title) &&
                Objects.equals(content, post.content) &&
                Objects.equals(author, post.author);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, content, author);
    }
}
