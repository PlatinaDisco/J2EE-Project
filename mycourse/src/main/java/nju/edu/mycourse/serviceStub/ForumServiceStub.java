package nju.edu.mycourse.serviceStub;

import nju.edu.mycourse.model.Forum;
import nju.edu.mycourse.model.Post;
import nju.edu.mycourse.model.ReturnPost;
import nju.edu.mycourse.repository.ForumRepository;
import nju.edu.mycourse.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ForumServiceStub {
    @Autowired
    private ForumService forumService;

    private static final String forumName_1 = "软件工程与计算二论坛";
    private static final String title_1 = "关于软工二大作业的疑问";
    private static final String content_1 = "大作业是否可以使用现有框架，如springboot，以及数据库设计要求";
    private static final String content_2 = "可以使用现有框架，数据库无特别要求，可以使用mysql等现有数据库，也可以以文本形式存储数据";
    private static final String content_3 = "明白了，谢谢老师";
    private static final String author_1 = "何沐声";
    private static final String author_2 = "刘钦";
    private static Long postId;
    private static Long returnPostId;

    public void test() {
        System.out.println(
                posting() + System.lineSeparator() +
                writeReturnPost() + System.lineSeparator() +
                writeComment() + System.lineSeparator() +
                "论坛: " + showForum() + System.lineSeparator() +
                "主帖: " + showPost() + System.lineSeparator() +
                "回帖: " + showReturnPost() + System.lineSeparator() +
                "评论: " + showReturnPostComments() + System.lineSeparator()
        );
    }

    private String posting() {
        Post post = forumService.posting(forumName_1, title_1, content_1, author_1);
        postId = post.getId();
        return author_1 + "发帖: " + postId;
    }
    private String writeReturnPost() {
        ReturnPost returnPost = forumService.writeReturnPost(String.valueOf(postId), content_2, author_2);
        returnPostId = returnPost.getId();
        return author_2 + "回帖: " + returnPostId;
    }
    private String writeComment() {
        forumService.writeComment(String.valueOf(returnPostId), content_3);
        return author_1 + "对回帖" + returnPostId + "发表评论";
    }
    private Forum showForum() {
        return forumService.showForum(forumName_1);
    }
    private Post showPost() {
        return forumService.showPost(String.valueOf(postId));
    }
    private ReturnPost showReturnPost() {
        return forumService.showReturnPost(String.valueOf(returnPostId));
    }
    private List<String> showReturnPostComments() {
        return forumService.showReturnPostComments(String.valueOf(returnPostId));
    }
}
