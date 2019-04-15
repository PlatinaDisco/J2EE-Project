<template>
  <el-container>
    <el-main>
      <h2>{{homework.homeworkTitle}}</h2>
      <el-form :inline="true">
        <el-form-item>
          <b>发布时间：</b><span>{{homework.assignmentTime}}</span>
        </el-form-item>
        <el-form-item>
          <b>截止时间：</b><span>{{homework.deadline}}</span>
        </el-form-item>
        <el-form-item>
          <b>作业大小：</b><span>{{homework.homeworkSize}}</span>
        </el-form-item>
        <el-form-item>
          <b>提交作业类型：</b><span>{{homework.submitFileType}}</span>
        </el-form-item>
      </el-form>
      <b>作业说明：</b><span>{{homework.homeworkContent}}</span><br>
      <el-form :inline="true">
        <el-form-item>
          <input type="file" ref="file" id="uploadSingleFile" accept='application/{}'>
        </el-form-item>
        <el-form-item>
          <el-button type="text" @click="submitHomework">上传作业</el-button>
        </el-form-item>
      </el-form>
      <div>
        <b>作业：</b><br>
        <template v-for="file in files" v-bind:value="file">
          <a :href=file.filePath >{{ file.fileName }}</a><br>
        </template>
      </div>
    </el-main>
  </el-container>
</template>

<script>
    import App from "../App.vue"
    export default {
        name: "HomeworkMain",
        data() {
          return {
            isAdmin: true,
            isInstructor: true,
            isStudent: true,
            result: "",
            fileInput: null,
            files: [
              {fileName: "", filePath: ""}
            ],
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
            }
          }
        },
        methods: {
          submitHomework() {
            let params = new FormData()
            params.append("homeworkId", this.homework.id)
            console.log(this.fileInput.files[0])
            params.append("multipartFile", this.fileInput.files[0])
            this.axios.post(App.path() + "/api/homework/submitHomework", params)
              .then((res) => {
                this.result = res.data
                alert(this.result)
                this.getHomework()
              }).catch((error) => {
              alert("无文件上传")
            })
          },
          getHomework() {
            let homeworkId = localStorage.getItem("homeworkId")
            let param = new URLSearchParams()
            param.append("homeworkId", homeworkId)
            this.axios.post(App.path() + "/api/homework/showHomework", param)
              .then((res) => {
                  this.homework = res.data
                  console.log(this.homework)
                  for(let i = 0 ; i < this.homework.homeworkUrls.length; i++) {
                    let strs = this.homework.homeworkUrls[i].split("\\")
                    let filename = strs[strs.length-1]
                    this.files.push({"fileName":filename, "filePath": App.path() + "/" + this.homework.homeworkUrls[i]})
                  }
              })
          }
        },
        mounted: function () {
          if(localStorage.getItem("isAdmin") === "true") {
            this.isAdmin = false
          }
          if(localStorage.getItem("isInstructor") === "true") {
            this.isInstructor = false
          }
          if(localStorage.getItem("isStudent") === "true") {
            this.isStudent = false
          }
          this.getHomework()
          this.fileInput = document.getElementById('uploadSingleFile')
        }
    }
</script>

<style scoped>

</style>
