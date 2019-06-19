<template>
  <el-container style="height: 590px; border: 1px solid #eee">
    <el-header class="el-header">
      <el-dropdown>
          <span class="el-dropdown-link">
            <span style="position:relative;">
              <el-image  :src="url" :fit="fit" style="width: 30px; height: 30px;"></el-image>
              <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
              <span style="font-size: 15px; color: aliceblue; position: absolute;top:0; left: 40px">
                {{name}}
                <i class="el-icon-arrow-down el-icon--right"></i>
              </span>
            </span>
          </span>
        <el-dropdown-menu slot="dropdown" style="width: 100px">
          <el-link href="/#/teacher_info">
            <el-dropdown-item style="font-size: 15px">个人信息</el-dropdown-item>
          </el-link>
          <el-link href="/#/logout">
            <el-dropdown-item style="font-size: 15px">退出登录</el-dropdown-item>
          </el-link>
        </el-dropdown-menu>
      </el-dropdown>
    </el-header>
    <el-aside width="200px" class="el-aside">
      <el-menu :default-openeds="['1', '2']" default-active="/student_main" style="width: 100% ; height: 100%">
        <el-link href="/#/student_main">
          <el-menu-item index="/student_main">
            <template slot="title">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <i class="el-icon-s-home"></i>主页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </template>
          </el-menu-item>
        </el-link>
        <el-link href="/#/student_main">
          <el-menu-item index="/student_main">
            <template slot="title">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <i class="el-icon-s-home"></i>创建课程&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </template>
          </el-menu-item>
        </el-link>
        <el-link href="/#/student_main">
          <el-menu-item index="/student_main">
            <template slot="title">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <i class="el-icon-s-home"></i>发布课程&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </template>
          </el-menu-item>
        </el-link>
        <el-link href="/#/student_main">
          <el-menu-item index="/student_main">
            <template slot="title">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <i class="el-icon-s-home"></i>我的课程&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </template>
          </el-menu-item>
        </el-link>
      </el-menu>
      <!--</el-col>-->
      <!--</el-row>-->
    </el-aside>

    <el-main>
    </el-main>
  </el-container>
</template>

<script>
// import { getLoading } from '../loading'

export default {
  name: 'TeacherMain',
  // mounted: function () {
  //   this.getInfo()
  // },
  methods: {
    Info () {
      alert(0)
      window.location.href = '/#/TeacherInfo'
    },
    getInfo () {
      // const loading = getLoading(this)
      this.$axios({
        method: 'get',
        url: 'http://localhost:8080/vue/teacher/info'
      }).then(function (res) {
        alert(0)
        const info = res.data
        // loading.close()
        this.name = info.name
        this.url = 'http://localhost:8080' + info.portrait
      }.bind(this)).catch(function (err) {
        if (err.response.status === 401) {
          this.$router.push('/login_register')
        }
      }.bind(this))
    }
  },
  data () {
    return {
    }
  }
}
</script>

<style scoped>
  .el-header {
    background-color: #333;
    color: #333;
    line-height: 60px;
    text-align: right;
  }
  .el-dropdown-link {
    cursor: pointer;
    height: 50px;
    width: 200px;
    font-size: 50px;
  }
  .el-aside {
    color: #333;
    background-color: rgb(238, 241, 246);
  }
</style>
