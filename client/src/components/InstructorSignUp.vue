<template>
  <el-container>
    <el-header>
      <h1>教师账户注册</h1>
    </el-header>
    <el-main>
      <el-form :model="instructor" status-icon ref="instructor" class="demo-instructorSignUp">
        <el-form-item prop="email">
          <el-input v-model="instructor.email" placeholder="邮箱" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input type="password" v-model="instructor.password" autocomplete="off" placeholder="新密码"></el-input>
        </el-form-item>
        <el-form-item prop="name">
          <el-input v-model="instructor.name" autocomplete="off" placeholder="姓名"></el-input>
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
      name: "InstructorSignUp",
      data() {
        return {
          instructor: {
            email: "",
            password: "",
            name: "",
          },
          verificationCode: "",
          result: "",
        }
      },
      methods: {
        register() {
          let params = new URLSearchParams()
          params.append("njuEmail", this.instructor.email)
          params.append("password", this.instructor.password)
          params.append("name", this.instructor.name)
          params.append("verificationCode", this.verificationCode)

          this.axios.post(App.path() + "/api/user/instructorRegister", params)
            .then((res) => {
              this.result = res.data
              if (this.result === "注册成功") {
                this.$router.push("/instructor/login")
              }
              else {
                alert(this.result)
              }
            })
        },
        sendVerificationCode() {
          let param = new URLSearchParams()
          param.append("email", this.instructor.email)
          this.axios.get(App.path() + "/api/user/sendVerificationCode", param)
            .then((res) => {
              this.result = res.data
              alert(this.result)
            })
        },
        turnBackToLogin() {
          this.$router.push("/instructor/login")
        }
      }
    }
</script>

<style scoped>

</style>
