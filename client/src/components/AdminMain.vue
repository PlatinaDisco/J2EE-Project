<template>
  <el-container>
    <el-main>
      <b>待审核科目</b>
      <div style="text-align: right">
        <b>数量：</b><span>{{unapprovedSubjects.length}}</span>
      </div>
      <el-table :data="unapprovedSubjects" style="width: 100%" max-height="500">
        <el-table-column fixed prop="subjectName" label="科目名称" width="200"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button @click="approveSubject(scope.$index)" type="text" size="small">通过</el-button>
          </template>
        </el-table-column>
      </el-table>
      <b>待审核课程</b>
      <div style="text-align: right">
        <b>数量：</b><span>{{unapprovedCourses.length}}</span>
      </div>
      <el-table :data="unapprovedCourses" style="width: 100%" max-height="5500">
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
            <el-button @click="approveCourse(scope.$index)" type="text" size="small">通过</el-button>
          </template>
        </el-table-column>
      </el-table>
      <b>全部科目</b>
      <div style="text-align: right">
        <b>数量：</b><span>{{approvedSubjects.length}}</span>
      </div>
      <el-table :data="approvedSubjects" style="width: 100%" max-height="500">
        <el-table-column
          fixed
          prop="subjectName"
          label="科目名称"
          width="200">
        </el-table-column>
        <el-table-column
          prop="approvedResult"
          label="是否通过审核"
          width="120">
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button @click="showSubject(scope.$index)" type="text" size="small">查看</el-button>
            <el-button @click="showForum(scope.$index)" type="text" size="small">访问论坛</el-button>
          </template>
        </el-table-column>
      </el-table>
      <b>全部课程</b>
      <div style="text-align: right">
        <b>数量：</b><span>{{approvedCourses.length}}</span>
      </div>
      <el-table :data="approvedCourses" style="width: 100%" max-height="500">
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
            <el-button @click="showCourse(scope.$index)" type="text" size="small">查看</el-button>
          </template>
        </el-table-column>
      </el-table>
      <b>学生列表</b>
      <div style="text-align: right">
        <b>数量：</b><span>{{students.length}}</span>
      </div>
      <el-table :data="students" style="width: 100%" max-height="500">
        <el-table-column prop="njuEmail" label="邮箱"></el-table-column>
        <el-table-column prop="name" label="学生姓名"></el-table-column>
        <el-table-column prop="studentId" label="学号"></el-table-column>
        <el-table-column prop="type" label="学生类型"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
          <el-button @click="showStudent(scope.$index)" type="text" size="small">查看</el-button>
          </template>
        </el-table-column>
      </el-table>
      <b>教师列表</b>
      <div style="text-align: right">
        <b>数量：</b><span>{{instructors.length}}</span>
      </div>
      <el-table :data="instructors">
        <el-table-column prop="njuEmail" label="邮箱"></el-table-column>
        <el-table-column prop="name" label="教师姓名"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button @click="showInstructor(scope.$index)" type="text" size="small">查看</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-main>
  </el-container>
</template>

<script>
    import App from "../App.vue"
    export default {
        name: "AdminMain",
        data() {
          return {
              result: "",
              subject: {
                subjectName: "",
                approvedResult: "",
                courses: [],
                forum: {},
                materialUrls: []
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
              unapprovedSubjects: [],
              unapprovedCourses: [],
              approvedSubjects: [],
              approvedCourses: [],
              students: [],
              instructors: []
          }
        },
        methods: {
          approveSubject(index) {
            let subjectName = this.unapprovedSubjects[index].subjectName
            let param = new URLSearchParams()
            param.append("subjectName", subjectName)
            this.axios.post(App.path() + "/api/subject/approveSubject", param)
              .then((res) => {
                this.result = res.data
                if(this.result === "课程通过") {
                  this.getAllUnapprovedSubjects()
                  this.getAllApprovedSubjects()
                }
                else {
                  alert(this.result)
                }
              })
          },
          approveCourse(index) {
            let courseName = this.unapprovedCourses[index].courseName
            let semester = this.unapprovedCourses[index].semester
            let params = new URLSearchParams()
            params.append("courseName", courseName)
            params.append("semester", semester)
            this.axios.post(App.path() + "/api/course/approveCourse", params)
              .then((res) => {
                this.result = res.data
                if(this.result === "课程通过") {
                  this.getAllUnapprovedCourses()
                  this.getAllApprovedCourses()
                }
                else {
                  alert(this.result)
                }
              })
          },
          showSubject(index) {
            let subjectName = this.approvedSubjects[index].subjectName
            localStorage.setItem("subjectName", subjectName)
            this.$router.push("/subject/main")
          },
          showForum(index) {
            let forumName = this.approvedSubjects[index].forum.forumName
            localStorage.setItem("forumName", forumName)
            this.$router.push("/forum/main")
          },
          showCourse(index) {
            let id = this.approvedCourses[index].id
            localStorage.setItem("courseId", id)
            this.$router.push("/course/main")
          },
          showStudent(index) {
            let email = this.students[index].njuEmail
            localStorage.setItem("email", email)
            this.$router.push("/student/main")
          },
          showInstructor(index) {
            let email = this.instructors[index].njuEmail
            localStorage.setItem("email", email)
            this.$router.push("/instructor/main")
          },
          getAllUnapprovedSubjects() {
            this.axios.post(App.path() + "/api/subject/showUnapprovedSubjects")
              .then((res) => {
                this.unapprovedSubjects = res.data
              })
          },
          getAllUnapprovedCourses() {
            this.axios.post(App.path() + "/api/course/showUnapprovedCourses")
              .then((res) => {
                this.unapprovedCourses = res.data
              })
          },
          getAllApprovedSubjects() {
            this.axios.post(App.path() + "/api/subject/showApprovedSubjects")
              .then((res) => {
                this.approvedSubjects = res.data
              })
          },
          getAllApprovedCourses() {
            this.axios.post(App.path() + "/api/course/showApprovedCourses")
              .then((res) => {
                this.approvedCourses = res.data
              })
          },
          getAllStudents() {
            this.axios.post(App.path() + "/api/user/showAllStudents")
              .then((res) => {
                this.students = res.data
              })
          },
          getAllInstructors() {
            this.axios.post(App.path() + "/api/user/showAllInstructors")
              .then((res) => {
                this.instructors = res.data
              })
          }
        },
        mounted: function () {
          this.getAllUnapprovedSubjects()
          this.getAllUnapprovedCourses()
          this.getAllApprovedSubjects()
          this.getAllApprovedCourses()
          this.getAllStudents()
          this.getAllInstructors()
        }
    }
</script>

<style scoped>

</style>
