package nju.edu.mycourse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "forumtbl")
public class Forum implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "forum", cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JsonIgnore
    private Subject subject; //论坛所属课程
    private String forumName; //论坛名称
    @OneToMany(mappedBy = "forum", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy(value="id ASC")
    private Set<Post> posts; //主帖

    public Forum() {}

    public Forum(Subject subject, String forumName) {
        this.subject = subject;
        this.forumName = forumName;
        this.posts = new HashSet<>();
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getForumName() {
        return forumName;
    }

    public void setForumName(String forumName) {
        this.forumName = forumName;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
