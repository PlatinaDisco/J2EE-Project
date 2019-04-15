<template>
  <el-container>
    <el-header style="text-align: left">
      <b>学生界面</b>
      <el-form :inline="true">
        <el-form-item>
          <b>邮箱：</b><span>{{student.njuEmail}}</span>
        </el-form-item>
        <el-form-item>
          <b>姓名：</b><span>{{student.name}}</span>
        </el-form-item>
        <el-form-item>
          <b>学号：</b><span>{{student.studentId}}</span>
        </el-form-item>
        <el-form-item>
          <b>学生类型：</b><span>{{student.type}}</span>
        </el-form-item>
      </el-form>
    </el-header>
    <el-main style="text-align: center">
      <template>
      <b>我的选课</b>
        <el-table :data="student.courses" style="width: 100%" max-height="500">
          <el-table-column
            fixed
            prop="id"
            label="课程编号"
            width="120">
          </el-table-column>
          <el-table-column
            prop="courseName"
            label="课程名称"
            width="200">
          </el-table-column>
          <el-table-column
            prop="semester"
            label="学期"
            width="150">
          </el-table-column>
          <el-table-column
            prop="classes"
            label="班次"
            width="120">
          </el-table-column>
          <el-table-column
            prop="limitation"
            label="限选人数"
            width="120">
          </el-table-column>
          <el-table-column
            prop="instructorName"
            label="教师"
            width="120">
          </el-table-column>
          <el-table-column
            label="操作"
            width="100">
            <template slot-scope="scope">
              <el-button @click="showCourse(scope.row)" type="text" size="small">查看</el-button>
              <el-button :disabled="isNotAdmin" @click.native.prevent="dropCourse(scope.$index, scope.row)" type="text" size="small">退课</el-button>
            </template>
          </el-table-column>
        </el-table>
      </template>
      <b>我的退课</b>
      <el-table :data="student.droppedCourses" style="width: 85%" max-height="500">
        <el-table-column
          fixed
          prop="id"
          label="课程编号"
          width="120">
        </el-table-column>
        <el-table-column
          prop="courseName"
          label="课程名称"
          width="200">
        </el-table-column>
        <el-table-column
          prop="semester"
          label="学期"
          width="150">
        </el-table-column>
        <el-table-column
          prop="classes"
          label="班次"
          width="120">
        </el-table-column>
        <el-table-column
          prop="limitation"
          label="限选人数"
          width="120">
        </el-table-column>
        <el-table-column
          prop="instructorName"
          label="教师"
          width="120">
        </el-table-column>
      </el-table>
      <div style="text-align: center">
        <b>我的成绩</b>
      </div>
      <el-table :data="student.grades" style="text-align: center" max-height="500">
        <el-table-column
          prop="courseName"
          label="课程名称"
          width="200">
        </el-table-column>
        <el-table-column
          prop="time"
          label="学期"
          width="150">
        </el-table-column>
        <el-table-column
          prop="grade"
          label="分数"
          width="120">
        </el-table-column>
      </el-table>
    </el-main>
    <el-footer>
      <el-form :inline="true">
        <el-button :disabled="isNotAdmin" type="primary" @click="gotoAllSubjects">前往选课</el-button>
        <el-button :disabled="isNotAdmin" type="primary" @click="updateStudentInfo">修改信息</el-button>
        <el-button :disabled="isNotAdmin" type="primary" @click="logout">注销</el-button>
        <el-button :disabled="isNotAdmin" type="primary" @click="gotoLogin">登出</el-button>
      </el-form>
    </el-footer>
  </el-container>
</template>

<script>
    import App from "../App.vue"
    export default {
        name: "StudentMain",
        data() {
          return {
            isNotAdmin: false,
            email: "",
            result: "",
            student: {
              njuEmail: "",
              password: "",
              name: "",
              studentId: "",
              type: "",
              isLogout: "",
              courses: [],
              droppedCourses: [],
              grades: []
            },
            course: {
              id: "",
              courseName: "",
              semester: "",
              classes: "",
              limitation: "",
              approveResult: "",
              homeworkList: [],
              testScore: {},
              grades: {},
              instructorName: ""
            },
            courseBySemester: {
              semester: "",
              courseList: []
            },
          }
        },
        methods: {
          updateStudentInfo() {
            this.$router.push("/student/updateInfo")
          },
          logout() {
            let params = new URLSearchParams()
            params.append("njuEmail", this.student.njuEmail)
            params.append("password", this.student.password)
            this.axios.post(App.path() + "/api/user/logout", params)
              .then((res) => {
                this.result = res.data
                if(this.result === "注销成功") {
                  this.$router.push("/student/login")
                  alert(this.result)
                }
                else {
                  alert(this.result)
                }
              })
          },
          gotoAllSubjects() {
            this.$router.push("/subjects")
          },
          gotoLogin() {
            this.$router.push("/student/login")
          },
          dropCourse(index,row) {
            this.student.courses.splice(index,1)
            this.course = row
            let params = new URLSearchParams()
            params.append("courseId", this.course.id)
            params.append("studentId", this.student.studentId)
            this.axios.post(App.path() + "/api/course/dropCourse", params)
              .then((res) => {
                this.result = res.data
                this.showStudentInfo()
                alert(this.result)
              })
          },
          showCourse(row) {
            this.course = row
            localStorage.setItem("courseId", this.course.id)
            this.$router.push("/course/main")
          },
          showStudentInfo() {
            this.email = localStorage.getItem("email")
            let param = new URLSearchParams()
            param.append("njuEmail", this.email)
            this.axios.post(App.path() + "/api/user/showStudentInfo", param)
              .then((res) => {
                this.student = res.data
                localStorage.setItem("name", this.student.name)
                console.log(this.student)
              })
          }
        },
        mounted: function () {
          if(localStorage.getItem("isAdmin") === "true") {
            this.isNotAdmin = true
          }
          this.showStudentInfo()
        }
    }
</script>

<style scoped>

</style>
