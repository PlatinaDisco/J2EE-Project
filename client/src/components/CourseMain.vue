<template>
  <el-container>
    <el-main>
      <b>课程界面</b>
      <el-form :inline="true">
        <el-form-item>
          <b>课程编号：</b><span>{{course.id}}</span>
        </el-form-item>
        <el-form-item>
          <b>课程名称：</b><span>{{course.courseName}}</span>
        </el-form-item>
        <el-form-item>
          <b>学期：</b><span>{{course.semester}}</span>
        </el-form-item>
        <el-form-item>
          <b>班次：</b><span>{{course.classes}}</span>
        </el-form-item>
        <el-form-item>
          <b>限选人数：</b><span>{{course.limitation}}</span>
        </el-form-item>
        <el-form-item>
          <b>教师姓名：</b><span>{{course.instructorName}}</span>
        </el-form-item>
      </el-form>
      <div v-show="isSelected||isInstructor||isAdmin">
        <b>作业</b>
        <el-button v-show=isInstructor type="primary" @click="publishHomeworkDialogVisible = true">发布新作业</el-button>
        <el-table :data="course.homeworkList">
          <el-table-column prop="homeworkTitle" label="作业标题"></el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="showHomework(scope.$index)">查看</el-button>
            </template>
          </el-table-column>
        </el-table>
        <b v-show="isInstructor||isAdmin">选课学生</b>
        <el-table v-show=isInstructor||isAdmin :data="students">
          <el-table-column prop="name" label="姓名"></el-table-column>
          <el-table-column prop="studentId" label="学号"></el-table-column>
          <el-table-column prop="type" label="学生类型"></el-table-column>
        </el-table>
      </div>
      <el-dialog title="发布新作业" :visible.sync="publishHomeworkDialogVisible">
        <el-form class="demo-publishHomework">
          <el-form-item prop="courseName">
            <el-input v-model="homework.homeworkTitle" placeholder="作业标题" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item prop="time">
            <el-date-picker
              v-model="time"
              type="datetimerange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              value-format="yyyy-MM-dd HH:mm:ss">
            </el-date-picker>
          </el-form-item>
          <el-form-item prop="homeworkContent">
            <el-input v-model="homework.homeworkContent" type="textarea" :rows="10" placeholder="请输入内容"></el-input>
          </el-form-item>
          <el-form-item prop="homeworkSize">
            <el-input-number v-model="size" :min="1" :max="200" label="描述文字"></el-input-number>
            <el-select v-model="sizeType" placeholder="请选择" value="">
              <el-option
                v-for="item in sizeTypes"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="submitFileType">
            <el-select v-model="submitFileType" placeholder="请选择" value="">
              <el-option
                v-for="item in submitFileTypes"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-button type="primary" @click="publishHomework">发布作业</el-button>
          <el-button @click="publishHomeworkDialogVisible = false">取消</el-button>
        </el-form>
      </el-dialog>
    </el-main>
    <el-footer v-show="isSelected||isInstructor||isAdmin">
      <el-form :inline="true">
        <el-form-item>
          <input type="file" ref="file" id="uploadTestScoreFile" accept='application/{}'>
        </el-form-item>
        <el-form-item>
          <el-button v-show=isInstructor type="text" @click="uploadTestScoreFile">上传测试分数</el-button>
        </el-form-item>
      </el-form>
      <el-form :inline="true">
        <el-form-item>
          <input type="file" ref="file" id="uploadTotalGradeFile" accept='application/{}'>
        </el-form-item>
        <el-form-item>
          <el-button v-show=isInstructor type="text" @click="uploadTotalGradeFile">上传总评分数</el-button>
        </el-form-item>
      </el-form>
      <b>分数</b><br>
      <template v-for="file in files" v-bind:value="file">
        <a :href=file.filePath >{{ file.fileName }}</a><br>
      </template>
      <el-dialog title="群发邮件" :visible.sync="sendEmailDialogVisible">
        <el-form>
          <el-input v-model="emailContent" type="textarea" :rows="10" placeholder="请输入内容"></el-input>
          <el-button type="primary" @click="sendEmail">发送</el-button>
          <el-button @click="sendEmailDialogVisible = false">取消</el-button>
        </el-form>
      </el-dialog>
      <el-button v-show="isInstructor" type="primary" @click="sendEmailDialogVisible = true">群发邮件</el-button>
    </el-footer>
  </el-container>
</template>

<script>
    import App from "../App.vue"
    export default {
        name: "CourseMain",
        data() {
          return {
            isAdmin: false,
            isStudent: true,
            isInstructor: false,
            isSelected: true,
            result: "",
            publishHomeworkDialogVisible: false,
            sendEmailDialogVisible: false,
            emailContent: "",
            time: ["", ""],
            students: [],
            fileInput1: null,
            fileInput2: null,
            files: [
              {fileName: "", filePath: ""}
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
            },
            homework: {
              id: "",
              homeworkTitle: "",
              assignmentTime: "",
              deadline: "",
              homeworkSize: "",
              homeworkContent: "",
              submitFileType: "",
              homeworkUrls: [],
              homeworkScoreFileUrl: ""
            },
            size: "",
            sizeType: "",
            sizeTypes: [
              {value: "K", label: "K"},
              {value: "M", label: "M"},
              {value: "G", label: "G"}
            ],
            submitFileType: "",
            submitFileTypes: [
              {value: ".pdf", label: ".pdf"},
              {value: ".doc", label: ".doc"},
              {value: ".zip", label: ".zip"}
            ]
          }
        },
        methods: {
          publishHomework() {
            let params = new FormData()
            params.append("homeworkTitle", this.homework.homeworkTitle)
            params.append("assignmentTime", this.time[0])
            params.append("deadline", this.time[1])
            params.append("homeworkSize", this.size + this.sizeType)
            params.append("homeworkContent", this.homework.homeworkContent)
            params.append("submitFileType", this.submitFileType)
            params.append("courseId", this.course.id)
            this.axios.post(App.path() + "/api/homework/publishHomework", params)
              .then((res) => {
                this.result = res.data
                if(this.result === "作业发布成功"){
                  this.publishHomeworkDialogVisible = false
                  this.getCourse()
                  alert(this.result)
                }
                alert(this.result)
              })
          },
          uploadTestScoreFile() {
            let params = new FormData()
            params.append("courseId", this.course.id)
            console.log(this.fileInput1.files[0])
            params.append("multipartFile", this.fileInput1.files[0])
            this.axios.post(App.path() + "/api/course/uploadTestScoreFile", params)
              .then((res) => {
                this.result = res.data
                alert(this.result)
                this.getCourse()
              }).catch((error) => {
                alert("无文件上传")
            })
          },
          uploadTotalGradeFile() {
            let params = new FormData()
            params.append("courseId", this.course.id)
            console.log(this.fileInput2.files[0])
            params.append("multipartFile", this.fileInput2.files[0])
            this.axios.post(App.path() + "/api/course/uploadTotalGradeFile", params)
              .then((res) => {
                this.result = res.data
                alert(this.result)
                this.getCourse()
              }).catch((error) => {
              alert("无文件上传")
            })
          },
          sendEmail() {
            let param = new FormData()
            param.append("courseId", this.course.id)
            param.append("content", this.emailContent)
            this.axios.post(App.path() + "/api/course/sendEmailToAllStudentsWhoSelectTheCourse", param)
              .then((res) => {
                this.sendEmailDialogVisible = false
                alert("发送成功")
              }).catch((error) => {
                alert("发送失败")
            })
          },
          showHomework(index) {
            let homeworkId = this.course.homeworkList[index].id
            localStorage.setItem("homeworkId", homeworkId)
            this.$router.push("/homework/main")
          },
          getCourse() {
            let courseId = localStorage.getItem("courseId")
            let param = new FormData()
            param.append("courseId", courseId)
            this.axios.post(App.path() + "/api/course/showCourse", param)
              .then((res) => {
                this.course = res.data
                console.log(this.course)
                if(this.course.testScoreFileUrl != null) {
                  let strs = this.course.testScoreFileUrl.split("\\")
                  let filename = strs[strs.length-1]
                  this.files.push({"fileName":filename, "filePath": App.path() + "/" + this.course.testScoreFileUrl})
                }
                if(this.course.totalGradeFileUrl != null) {
                  let strs = this.course.totalGradeFileUrl.split("\\")
                  let filename = strs[strs.length-1]
                  this.files.push({"fileName":filename, "filePath": App.path() + "/" + this.course.totalGradeFileUrl})
                }
              })
          },
          getCourseStudents() {
            let courseId = localStorage.getItem("courseId")
            let param = new FormData()
            param.append("courseId", courseId)
            this.axios.post(App.path() + "/api/user/showCourseStudents", param)
              .then((res) => {
                this.students = res.data
                console.log(this.students)
              })
          }
        },
        mounted: function () {
          if(localStorage.getItem("isStudent") === "true") {
            this.isStudent = false
          }
          if(localStorage.getItem("isInstructor") === "true"){
            this.isInstructor = true
          }
          this.getCourse()
          this.getCourseStudents()
          let email = localStorage.getItem("email")
          // for(let i=0; i<this.students.length; i++) {
          //   let studentEmail = this.students[i].njuEmail
          //   console.log(studentEmail)
          //   if (email === studentEmail) {
          //     this.isSelected = true
          //     break;
          //   }
          // }
          this.fileInput1 = document.getElementById('uploadTestScoreFile')
          this.fileInput2 = document.getElementById('uploadTotalGradeFile')
        }
    }
</script>

<style scoped>

</style>
