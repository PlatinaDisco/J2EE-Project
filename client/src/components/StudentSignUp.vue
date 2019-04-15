<template>
  <el-container>
      <el-header>
        <h1>学生账户注册</h1>
      </el-header>
      <el-main>
        <el-form :model="student" status-icon ref="student" class="demo-studentSignUp">
          <el-form-item prop="email">
            <el-input v-model="student.email" placeholder="邮箱" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input type="password" v-model="student.password" autocomplete="off" placeholder="新密码"></el-input>
          </el-form-item>
          <el-form-item prop="name">
            <el-input v-model="student.name" autocomplete="off" placeholder="姓名"></el-input>
          </el-form-item>
          <el-form-item prop="studentId">
            <el-input v-model="student.studentId" auto-complete="off" placeholder="学号"></el-input>
          </el-form-item>
          <el-form-item prop="type" >
            <el-select v-model="student.type" placeholder="学生类型" value="">
              <el-option label="本科生" value="本科生"></el-option>
              <el-option label="研究生" value="研究生"></el-option>
              <el-option label="博士生" value="博士生"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <el-form :inline="true" class="demo-sendVerificationCode">
          <el-form-item>
            <el-input v-model="verificationCode" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="sendVerificationCode">发送验证码</el-button>
          </el-form-item>
        </el-form>
        <el-button type="primary" @click="register">注册</el-button>
        <el-button type="primary" @click="turnBackToLogin">前往登录</el-button>
      </el-main>
  </el-container>
</template>

<script>
    import App from "../App.vue"
    export default {
        name: "StudentSignUp",
        data() {
          return {
            student: {
              email: "",
              password: "",
              name: "",
              studentId: "",
              type: ""
            },
            verificationCode: "",
            result: "",
          }
        },
        methods: {
          register() {
            let params = new URLSearchParams()
            params.append("njuEmail", this.student.email)
            params.append("password", this.student.password)
            params.append("name", this.student.name)
            params.append("id", this.student.studentId)
            params.append("type", this.student.type)
            params.append("verificationCode", this.verificationCode)

            this.axios.post(App.path() + "/api/user/studentRegister", params)
              .then((res) => {
                this.result = res.data
                if (this.result === "注册成功") {
                  alert(this.result)
                  this.$router.push("/student/login")
                }
                else {
                  alert(this.result)
                }
              })
          },
          sendVerificationCode() {
            let param = new URLSearchParams()
            param.append("email", this.student.email)
            this.axios.post(App.path() + "/api/user/sendVerificationCode", param)
              .then((res) => {
                this.result = res.data
                alert(this.result)
              })
          },
          turnBackToLogin() {
            this.$router.push("/student/login")
          }
        }
    }
</script>

<style scoped>

</style>
