<template>
    <el-container>
      <el-header>
        <h1>学生信息修改</h1>
      </el-header>
      <el-main>
        <el-form :model="updateInfo" status-icon ref="updateInfo" class="demo-studentInfoUpdate">
          <el-form-item prop="njuEmail">
            <el-input v-model="updateInfo.njuEmail" placeholder="邮箱" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input type="password" v-model="updateInfo.password" autocomplete="off" placeholder="新密码"></el-input>
          </el-form-item>
          <el-form-item prop="name">
            <el-input v-model="updateInfo.name" autocomplete="off" placeholder="姓名"></el-input>
          </el-form-item>
          <el-form-item prop="studentId">
            <el-input v-model="updateInfo.studentId" auto-complete="off" placeholder="学号"></el-input>
          </el-form-item>
          <el-form-item prop="type" >
            <el-select v-model="updateInfo.type" placeholder="学生类型" value="">
              <el-option label="本科生" value="本科生"></el-option>
              <el-option label="研究生" value="研究生"></el-option>
              <el-option label="博士生" value="博士生"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <el-form :inline="true">
          <el-button type="primary" @click="updateStudentInfo">保存</el-button>
          <el-button type="primary" @click="turnBackToMain">返回主页面</el-button>
        </el-form>
      </el-main>
    </el-container>
</template>

<script>
    import App from "../App.vue"
    export default {
        name: "StudentInfoUpdate",
        data() {
          return {
            updateInfo: {
              njuEmail: "",
              password: "",
              name: "",
              studentId: "",
              type: ""
            },
            result: "",
          }
        },
        methods: {
          updateStudentInfo() {
            let params = new URLSearchParams()
            params.append("njuEmail", this.updateInfo.njuEmail)
            params.append("password", this.updateInfo.password)
            params.append("name", this.updateInfo.name)
            params.append("id", this.updateInfo.studentId)
            params.append("type", this.updateInfo.type)
            this.axios.post(App.path() + "/api/user/updateStudentInfo", params)
              .then((res) => {
                this.result = res.data
                alert(this.result)
              })
          },
          turnBackToMain() {
            this.$router.push("/student/main")
          }
        }
    }
</script>

<style scoped>

</style>
