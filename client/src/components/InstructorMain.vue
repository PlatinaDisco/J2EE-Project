<template>
  <el-container>
    <el-header style="text-align: left">
      <b>教师界面</b>
      <el-form :inline="true">
        <el-form-item>
          <b>邮箱：</b><span>{{instructor.njuEmail}}</span>
        </el-form-item>
        <el-form-item>
          <b>姓名：</b><span>{{instructor.name}}</span>
        </el-form-item>
      </el-form>
    </el-header>
    <el-main style="text-align: center">
      <b>我的课程</b>
      <el-table :data="instructor.subjects" style="width: 100%" max-height="500">
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
      <el-dialog title="创建科目" :visible.sync="startSubjectDialogVisible">
        <el-form class="demo-createSubject">
          <el-form-item prop="subjectName">
            <el-input v-model="subjectName" placeholder="科目名称" auto-complete="off"></el-input>
          </el-form-item>
        </el-form>
        <el-button type="primary" @click="startSubject">提交审核</el-button>
        <el-button @click="startSubjectDialogVisible = false">取消</el-button>
      </el-dialog>
      <el-dialog title="创建课程" :visible.sync="startCourseDialogVisible">
        <el-form class="demo-createSubject">
          <el-form-item prop="courseName">
            <el-input v-model="course.courseName" placeholder="课程名称" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item prop="year">
            <el-date-picker
              v-model="year"
              type="year"
              placeholder="选择年份"
              value-format="yyyy">
            </el-date-picker>
          </el-form-item>
          <el-form-item prop="year">
            <el-select v-model="season" placeholder="请选择" value="">
              <el-option
                v-for="item in seasons"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-input v-model="course.classes" placeholder="班次" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item>
            <el-input-number v-model="course.limitation" :min="1" :max="200" label="描述文字"></el-input-number>
          </el-form-item>
          <el-form-item>
            <el-input v-model="course.subjectName" placeholder="所属科目名称" auto-complete="off"></el-input>
          </el-form-item>
        </el-form>
        <el-button type="primary" @click="startCourse">提交审核</el-button>
        <el-button @click="startCourseDialogVisible = false">取消</el-button>
      </el-dialog>
    </el-main>
    <el-footer>
      <el-form :inline="true">
        <el-button :disabled="isNotAdmin" type="primary" @click="startSubjectDialogVisible = true">创建科目</el-button>
        <el-button :disabled="isNotAdmin" type="primary" @click="startCourseDialogVisible = true">创建课程</el-button>
        <el-button :disabled="isNotAdmin" type="primary" @click="updateInstructorInfo">修改信息</el-button>
        <el-button :disabled="isNotAdmin" type="primary" @click="gotoLogin">登出</el-button>
      </el-form>
    </el-footer>
  </el-container>
</template>

<script>
    import App from '../App.vue'
    export default {
        name: "InstructorMain",
        data() {
          return {
            isNotAdmin: false,
            isInstructor: false,
            email: "",
            result: "",
            startSubjectDialogVisible: false,
            startCourseDialogVisible: false,
            instructor: {
              njuEmail: "",
              name: "",
              subjects: []
            },
            subjectName: "",
            year: "",
            season: "",
            seasons: [
              {value: "spring", label: "spring"},
              {value: "summer", label: "summer"},
              {value: "autumn", label: "autumn"},
              {value: "winter", label: "winter"}
            ],
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
            }
          }
        },
        methods: {
          updateInstructorInfo() {
            this.$router.push("/instructor/updateInfo")
          },
          gotoLogin() {
            this.$router.push("/instructor/login")
          },
          startSubject() {
            let params = new URLSearchParams()
            params.append("instructorEmail", this.instructor.njuEmail)
            params.append("subjectName", this.subjectName)
            this.axios.post(App.path() + "/api/subject/startSubject", params)
              .then((res) => {
                this.result = res.data
                if(this.result === "课程创建成功") {
                  this.startSubjectDialogVisible = false
                  this.showInstructorInfo()
                }
                alert(this.result)
              })
          },
          startCourse() {
            let params = new URLSearchParams()
            params.append("courseName", this.course.courseName)
            let semester = this.year + "-" + this.season
            params.append("semester", semester)
            params.append("classes", this.course.classes)
            params.append("limitation", this.course.limitation)
            params.append("subjectName", this.course.subjectName)
            this.axios.post(App.path() + "/api/course/startCourse", params)
              .then((res) => {
                this.result = res.data
                if(this.result === "课程创建成功") {
                  this.startCourseDialogVisible = false
                  this.showInstructorInfo()
                }
                alert(this.result)
              })
          },
          showSubject(index) {
            let subjectName = this.instructor.subjects[index].subjectName
            localStorage.setItem("subjectName", subjectName)
            this.$router.push("/subject/main")
          },
          showForum(index) {
            let forumName = this.instructor.subjects[index].forum.forumName
            localStorage.setItem("forumName", forumName)
            this.$router.push("/forum/main")
          },
          showInstructorInfo() {
            this.email = localStorage.getItem("email")
            let param = new URLSearchParams()
            param.append("njuEmail", this.email)
            this.axios.post(App.path() + "/api/user/showInstructorInfo", param)
              .then((res) => {
                this.instructor = res.data
                console.log(this.instructor)
                localStorage.setItem("name", this.instructor.name)
              })
          }
        },
        mounted: function () {
          if(localStorage.getItem("isAdmin") === "true") {
            this.isNotAdmin = true
          }
          if(localStorage.getItem("isInstructor") === "true") {
            this.isInstructor = true
          }
          this.showInstructorInfo()
        }
    }
</script>

<style scoped>

</style>
