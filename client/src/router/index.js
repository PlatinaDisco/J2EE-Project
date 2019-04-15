import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import StudentLogin from "@/components/StudentLogin"
import StudentSignUp from "@/components/StudentSignup"
import StudentMain from "@/components/StudentMain"
import StudentInfoUpdate from "@/components/StudentInfoUpdate"
import InstructorLogin from "@/components/InstructorLogin"
import InstructorSignUp from "@/components/InstructorSignUp"
import InstructorMain from "@/components/InstructorMain"
import InstructorInfoUpdate from "@/components/InstructorInfoUpdate"
import AdminLogin from "@/components/AdminLogin"
import AdminMain from "@/components/AdminMain"
import Subjects from "@/components/Subjects"
import SubjectMain from "@/components/SubjectMain"
import CourseMain from "@/components/CourseMain"
import ForumMain from "@/components/ForumMain"
import PostMain from "@/components/PostMain"
import HomeworkMain from "@/components/HomeworkMain"

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: "/",
      name: "Home",
      component: Home
    },
    {
      path: "/student/login",
      name: "StudentLogin",
      component: StudentLogin
    },
    {
      path: "/student/signUp",
      name: "StudentSignUp",
      component: StudentSignUp
    },
    {
      path: "/student/main",
      name: "StudentMain",
      component: StudentMain
    },
    {
      path: "/student/updateInfo",
      name: "StudentInfoUpdate",
      component: StudentInfoUpdate
    },
    {
      path: "/instructor/login",
      name: "InstructorLogin",
      component: InstructorLogin
    },
    {
      path: "/instructor/signUp",
      name: "InstructorSignUp",
      component: InstructorSignUp
    },
    {
      path: "/instructor/main",
      name: "InstructorMain",
      component: InstructorMain
    },
    {
      path: "/instructor/updateInfo",
      name: "InstructorInfoUpdate",
      component: InstructorInfoUpdate
    },
    {
      path: "/admin/login",
      name: "AdminLogin",
      component: AdminLogin
    },
    {
      path: "/admin/main",
      name: "AdminMain",
      component: AdminMain
    },
    {
      path: "/subjects",
      name: "Subjects",
      component: Subjects
    },
    {
      path: "/subject/main",
      name: "SubjectMain",
      component: SubjectMain
    },
    {
      path: "/course/main",
      name: "CourseMain",
      component: CourseMain
    },
    {
      path: "/forum/main",
      name: "ForumMain",
      component: ForumMain
    },
    {
      path: "/post/main",
      name: "PostMain",
      component: PostMain
    },
    {
      path: "/homework/main",
      name: "HomeworkMain",
      component: HomeworkMain
    }
  ]
})
