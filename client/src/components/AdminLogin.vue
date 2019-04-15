<template>
  <el-container>
    <el-main>
      <div>
        <h1>管理员登录界面</h1>
      </div>
      <el-form :inline="true" class="demo-login">
        <el-form-item label="用户名:" prop="username">
          <el-input v-model="username"></el-input>
        </el-form-item>
        <el-form-item label="密码:" prop="password">
          <el-input v-model="password" type="password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="login">登录</el-button>
        </el-form-item>
      </el-form>
    </el-main>
  </el-container>
</template>

<script>
  import App from '../App.vue'

  export default {
      name: "AdminLogin",
      data() {
        return {
          username: "",
          password: "",
          result: ""
        }
      },
      methods: {
        login() {
          let params = new URLSearchParams()
          params.append("njuEmail", this.username)
          params.append("password", this.password)
          this.axios.post(App.path() + "/api/user/login", params)
            .then((res) => {
              this.result = res.data
              if(this.result === "登陆成功") {
                localStorage.setItem("isAdmin", "true")
                localStorage.setItem("isStudent", "false")
                localStorage.setItem("isInstructor", "false")
                this.$router.push("/admin/main")
              }
              else {
                alert(this.result)
              }
            })
        }
      }
  }
</script>

<style scoped>

</style>
