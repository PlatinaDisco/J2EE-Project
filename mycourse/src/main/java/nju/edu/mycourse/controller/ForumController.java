package nju.edu.mycourse.controller;

import nju.edu.mycourse.model.Forum;
import nju.edu.mycourse.model.Post;
import nju.edu.mycourse.model.ReturnPost;
import nju.edu.mycourse.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/forum")
public class ForumController {
    @Autowired
    private ForumService forumService;

    @RequestMapping("/posting")
    public Post posting(@RequestParam String forumName, @RequestParam String title, @RequestParam String content, @RequestParam String author) {
        return forumService.posting(forumName,title,content,author);
    }

    @RequestMapping("/writeReturnPost")
    public ReturnPost writeReturnPost(@RequestParam String postId, @RequestParam String content, @RequestParam String author) {
        return forumService.writeReturnPost(postId,content,author);
    }

    @RequestMapping("/writeComment")
    public void writeComment(@RequestParam String returnPostId, @RequestParam String content) {
        forumService.writeComment(returnPostId, content);
    }

    @RequestMapping("/showForum")
    public Forum showForum(@RequestParam String forumName) {
        return forumService.showForum(forumName);
    }

    @RequestMapping("/showAllForums")
    public List<Forum> showAllForums() {
        return forumService.showAllForums();
    }

    @RequestMapping("/showPost")
    public Post showPost(@RequestParam String postId) {
        return forumService.showPost(postId);
    }

    @RequestMapping("/showReturnPost")
    public ReturnPost showReturnPost(@RequestParam String returnPostId) {
        return forumService.showReturnPost(returnPostId);
    }

    @RequestMapping("/showForumPosts")
    public List<Post> showForumPosts(@RequestParam String forumName) {
        return forumService.showForumPosts(forumName);
    }

    @RequestMapping("/showPostReturnPosts")
    public List<ReturnPost> showPostReturnPosts(@RequestParam String postId) {
        return forumService.showPostReturnPosts(postId);
    }

    @RequestMapping("/showReturnPostComments")
    public List<String> showReturnPostComments(@RequestParam String returnPostId) {
        return forumService.showReturnPostComments(returnPostId);
    }
}
