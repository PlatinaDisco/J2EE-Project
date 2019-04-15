package nju.edu.mycourse.service.impl;

import nju.edu.mycourse.model.Forum;
import nju.edu.mycourse.model.Post;
import nju.edu.mycourse.model.ReturnPost;
import nju.edu.mycourse.repository.ForumRepository;
import nju.edu.mycourse.repository.PostRepository;
import nju.edu.mycourse.repository.ReturnPostRepository;
import nju.edu.mycourse.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ForumServiceImpl implements ForumService {
    @Autowired
    private ForumRepository forumRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ReturnPostRepository returnPostRepository;

    @Override
    public Post posting(String forumName, String title, String content, String author) {
        Forum forum = forumRepository.findByForumName(forumName);
        if(null != postRepository.findByTitleAndAuthorAndContent(title, author, content)) {
            return postRepository.findByTitleAndAuthorAndContent(title, author, content);
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time = df.format(new Date());
        Post post = new Post(forum, title, content, author, time);
        forum.getPosts().add(post);
        forumRepository.save(forum);
        return postRepository.findByTitleAndAuthorAndPostTime(title, author, time);
    }

    @Override
    public ReturnPost writeReturnPost(String postId, String content, String author) {
        Post post = postRepository.findById(Long.valueOf(postId)).get();
        if(null != returnPostRepository.findByPostAndAuthorAndContent(post, author, content)) {
            return returnPostRepository.findByPostAndAuthorAndContent(post, author, content);
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time = df.format(new Date());
        ReturnPost returnPost = new ReturnPost(post, content, author, time);
        post.getReturnPosts().add(returnPost);
        postRepository.save(post);
        return returnPostRepository.findByPostAndAuthorAndReplyTime(post, author, time);
    }

    @Override
    public void writeComment(String returnPostId, String content) {
        ReturnPost returnPost = returnPostRepository.findById(Long.valueOf(returnPostId)).get();
        if(!returnPost.getComments().contains(content)) {
            returnPost.getComments().add(content);
            returnPostRepository.save(returnPost);
        }
    }

    @Override
    public Forum showForum(String forumName) {
        return forumRepository.findByForumName(forumName);
    }

    @Override
    public List<Forum> showAllForums() {
        return forumRepository.findAll();
    }

    @Override
    public Post showPost(String postId) {
        return postRepository.findById(Long.valueOf(postId)).get();
    }

    @Override
    public ReturnPost showReturnPost(String returnPostId) {
        return returnPostRepository.findById(Long.valueOf(returnPostId)).get();
    }

    @Override
    public List<Post> showForumPosts(String forumName) {
        Forum forum = forumRepository.findByForumName(forumName);
        return new ArrayList<>(forum.getPosts());
    }

    @Override
    public List<ReturnPost> showPostReturnPosts(String postId) {
        Post post = postRepository.findById(Long.valueOf(postId)).get();
        return new ArrayList<>(post.getReturnPosts());
    }

    @Override
    public List<String> showReturnPostComments(String returnPostId) {
        ReturnPost returnPost = returnPostRepository.findById(Long.valueOf(returnPostId)).get();
        return new ArrayList<>(returnPost.getComments());
    }
}
