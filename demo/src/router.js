import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import DemoPage from './components/DemoPage'
import LoginRegister from './components/LoginRegister'
import RegisterValidate from './components/RegisterValidate'
import StudentMain from './components/StudentMain'
import StudentInfo from './components/StudentInfo'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import(/* webpackChunkName: "about" */ './views/About.vue')
    },
    {
      path: '/demo_page',
      name: 'DemoPage',
      component: DemoPage
    },
    {
      path: '/login_register',
      name: 'login_register',
      component: LoginRegister
    },
    {
      path: '/register/validate',
      name: 'register_validate',
      component: RegisterValidate
    },
    {
      path: '/student_main',
      name: 'student_main',
      component: StudentMain
    },
    {
      path: '/student_info',
      name: 'student_info',
      component: StudentInfo
    }
  ]
})
