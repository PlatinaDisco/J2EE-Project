<template>
  <el-main>
    <div>
      <h1>教师登录界面</h1>
    </div>
    <el-form :inline="true" class="demo-login">
      <el-form-item label="邮箱:" prop="email">
        <el-input v-model="email"></el-input>
      </el-form-item>
      <el-form-item label="密码:" prop="password">
        <el-input v-model="password" type="password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="login">登录</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="gotoRegister">注册</el-button>
      </el-form-item>
    </el-form>
  </el-main>
</template>

<script>
    import App from '../App.vue'
    export default {
      name: "InstructorLogin",
      data() {
        return {
          email: "",
          password: "",
          result: "",
        }
      },
      methods: {
        login() {
          let params = new URLSearchParams()
          params.append("njuEmail", this.email)
          params.append("password", this.password)
          localStorage.setItem("email", this.email)
          this.axios.post(App.path() + "/api/user/login", params)
            .then((res) => {
              this.result = res.data
              if(this.result === "登陆成功") {
                localStorage.setItem("isAdmin", "false")
                localStorage.setItem("isInstructor", "true")
                localStorage.setItem("isStudent", "false")
                this.$router.push("/instructor/main")
              }
              else {
                alert(this.result)
              }
            })
        },
        gotoRegister() {
          this.$router.push("/instructor/signUp")
        }
      }
    }
</script>

<style scoped>

</style>
