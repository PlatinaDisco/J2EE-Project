<template>
  <el-container>
    <el-header>
      <h1>教师信息修改</h1>
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
      <el-button type="primary" @click="updateInstructorInfo">保存</el-button>
      <el-button type="primary" @click="turnBackToMain">返回主页面</el-button>
    </el-main>
  </el-container>
</template>

<script>
    import App from "../App.vue"
    export default {
      name: "InstructorInfoUpdate",
      data() {
        return {
          instructor: {
            email: "",
            password: "",
            name: "",
          },
          result: "",
        }
      },
      methods: {
        updateInstructorInfo() {
          let params = new URLSearchParams()
          params.append("njuEmail", this.instructor.email)
          params.append("password", this.instructor.password)
          params.append("name", this.instructor.name)
          this.axios.post(App.path() + "/api/user/updateInstructorInfo", params)
            .then((res) => {
              this.result = res.data
              alert(this.result)
            })
        },
        turnBackToMain() {
          this.$router.push("/instructor/main")
        }
      }
    }
</script>

<style scoped>

</style>
