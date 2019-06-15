package edu.nju.wsql.controller.vue.student;

import edu.nju.wsql.beans.UserInfoBean;
import edu.nju.wsql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/vue/student/info")
public class VueStudentInfoController {
    @Autowired
    private UserService userService;

    @GetMapping
    @ResponseBody
    public UserInfoBean getInfo(HttpSession session) {
        return userService.getUserInfo((String) session.getAttribute("login"));
    }
}
