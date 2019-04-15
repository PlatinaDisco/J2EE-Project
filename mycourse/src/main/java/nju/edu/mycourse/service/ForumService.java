package nju.edu.mycourse.service;

import nju.edu.mycourse.model.Forum;
import nju.edu.mycourse.model.Post;
import nju.edu.mycourse.model.ReturnPost;

import java.util.List;

public interface ForumService {
    Post posting(String forumName, String title, String content, String author);
    ReturnPost writeReturnPost(String postId, String content, String author);
    void writeComment(String returnPostId, String content);

    Forum showForum(String forumName);
    List<Forum> showAllForums();

    Post showPost(String postId);
    ReturnPost showReturnPost(String returnPostId);
    List<Post> showForumPosts(String forumName);
    List<ReturnPost> showPostReturnPosts(String postId);
    List<String> showReturnPostComments(String returnPostId);
}
