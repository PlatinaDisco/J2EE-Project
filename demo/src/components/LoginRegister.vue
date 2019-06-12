<template>
  <el-row>
    <el-col :span="8"><div><br/></div></el-col>

    <el-col :span="8">
      <div style="margin-top: 33%">
        <el-tabs v-model="activeName" type="border-card" >
          <el-tab-pane label="登录" name="login">
            <h2>MyCourses登录</h2>
            <el-form ref="loginForm" :model="loginForm">
              <el-form-item>
                <el-input v-model="loginForm.login" placeholder="请输入用户名或邮箱"></el-input>
              </el-form-item>
              <el-form-item>
                <el-input v-model="loginForm.password" placeholder="请输入密码"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="login">登录</el-button>
              </el-form-item>
            </el-form>
          </el-tab-pane>
          <el-tab-pane label="注册" name="register">
            <h2>MyCourses注册</h2>
            <el-form :label-position="right" :model="registerForm" :rules="rules" ref="registerForm" label-width="80px">
              <el-form-item label="邮箱" prop="email">
                <el-input v-model="registerForm.email"></el-input>
              </el-form-item>
              <el-form-item label="密码" prop="password">
                <el-input type="password" v-model="registerForm.password"></el-input>
              </el-form-item>
              <el-form-item label="确认密码" prop="confirmPassword">
                <el-input type="password" v-model="registerForm.confirmPassword"></el-input>
              </el-form-item>
              <el-form-item label="姓名">
                <el-input v-model="registerForm.name"></el-input>
              </el-form-item>
            </el-form>
            <el-button type="primary" @click="register">注册</el-button>
            <el-button @click="back">返回</el-button>
          </el-tab-pane>
        </el-tabs>
      </div>
    </el-col>

    <el-col :span="8"><div><br/></div></el-col>
  </el-row>
</template>

<script>
export default {
  name: 'LoginRegister',
  mounted: function () {
  },
  methods: {
    login () {
      alert('登录成功')
    },
    register () {
      alert('注册成功')
    },
    back () {
      this.activeName = 'login'
    }
  },
  data () {
    const validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.registerForm.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      activeName: 'login',
      loginForm: {
        login: '',
        password: ''
      },
      registerForm: {
        email: '',
        password: '',
        confirmPassword: '',
        name: ''
      },
      rules: {
        email: [
          { type: 'email', required: true, message: '请输入南大邮箱', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 3, message: '长度最少 3 位', trigger: 'blur' }
        ],
        confirmPassword: [
          { required: true, validator: validatePass2, trigger: 'blur' }
        ]
      }
    }
  }
}
</script>

<style scoped>

</style>
