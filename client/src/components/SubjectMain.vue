<template>
  <el-container>
    <el-header style="text-align: left">
      <b>{{subject.subjectName}}</b>
      <el-button type="text"@click="gotoForum">前往论坛</el-button>
    </el-header>
    <el-main>
      <b>课程</b>
      <el-table :data="subject.courses" style="width: 100%" max-height="500">
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
            <el-button :disabled=isAdmin||isInstructor @click="selectCourse(scope.row)" type="text" size="small">选课</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-form v-show=isInstructor :inline="true">
        <el-form-item>
          <input type="file" ref="file" id="uploadSingleFile" accept='application/{}'>
        </el-form-item>
        <el-form-item>
          <el-button type="text" @click="uploadMaterial">上传课件</el-button>
        </el-form-item>
      </el-form>
      <b>课程资料</b><br>
      <template v-for="file in files" v-bind:value="file">
        <a :href=file.filePath >{{ file.fileName }}</a><br>
      </template>
    </el-main>
  </el-container>
</template>

<script>
    import App from "../App.vue"
    export default {
        name: "SubjectMain",
        data() {
          return {
            isAdmin: false,
            isInstructor: false,
            isStudent: false,
            fileInput: null,
            files: [
              {fileName: "", filePath: ""}
            ],
            result: "",
            subjectName: "",
            subject: {
              subjectName: "",
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
              homeworkList: [],
              testScoreFileUrl: "",
              totalGradeFileUrl: "",
              testScore: {},
              grades: {},
              instructorName: ""
            },
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
          }
        },
        methods: {
          gotoForum() {
            let forumName = this.subject.forum.forumName
            localStorage.setItem("forumName", forumName)
            this.$router.push("/forum/main")
          },
          showCourse(row) {
            this.course = row
            localStorage.setItem("courseId", this.course.id)
            this.$router.push("/course/main")
          },
          selectCourse(row){
            this.course = row
            let email =  localStorage.getItem("email")
            let param = new URLSearchParams()
            param.append("njuEmail", email)
            this.axios.post(App.path() + "/api/user/showStudentInfo", param)
              .then((res) => {
                this.student = res.data
              }).catch((res) => {
                alert("选课失败")
            })
            console.log(this.student)
            let params = new URLSearchParams()
            params.append("courseId", this.course.id)
            params.append("studentId", this.student.studentId)
            this.axios.post(App.path() + "/api/course/selectCourse", params)
              .then((res) => {
                this.result = res.data
                alert(this.result)
              })
          },
          uploadMaterial() {
            let params = new FormData()
            params.append("subjectName", this.subject.subjectName)
            console.log(this.fileInput.files[0])
            params.append("multipartFile", this.fileInput.files[0])
            this.axios.post(App.path() + "/api/subject/uploadSubjectMaterial", params)
              .then((res) => {
                this.result = res.data
                alert(this.result)
                this.getSubject()
              }).catch((error) => {
              alert("无文件上传")
            })
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
          },
          getSubject() {
            this.subjectName = localStorage.getItem("subjectName")
            let param = new FormData()
            param.append("subjectName", this.subjectName)
            this.axios.post(App.path() + "/api/subject/showSubject", param)
              .then((res) => {
                  this.subject = res.data
                  console.log(this.subject)
                  for(let i = 0 ; i < this.subject.materialUrls.length; i++) {
                    let strs = this.subject.materialUrls[i].split("\\")
                    let filename = strs[strs.length-1]
                    this.files.push({"fileName":filename, "filePath": App.path() + "/" + this.subject.materialUrls[i]})
                  }
              })
          }
        },
        mounted: function () {
          if(localStorage.getItem("isAdmin") === "true") {
            this.isAdmin = true
          }
          if(localStorage.getItem("isInstructor") === "true") {
            this.isInstructor = true
          }
          if(localStorage.getItem("isStudent") === "true") {
            this.isStudent = true
          }
          console.log(this.isInstructor)
          this.getSubject()
          this.showStudentInfo()
          this.fileInput = document.getElementById('uploadSingleFile')
        }
    }
</script>

<style scoped>

</style>
