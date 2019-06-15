<template>
  <el-container style="height: 590px; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <el-menu :default-openeds="['1', '2']" default-active="/student_main" style="height: 588px">
        <el-link href="/#/student_main">
          <el-menu-item index="/student_main">
           <template slot="title">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             <i class="el-icon-s-home"></i>主页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           </template>
          </el-menu-item>
        </el-link>
        <el-submenu index="2">
          <template slot="title"><i class="el-icon-menu"></i>课程</template>
          <el-menu-item-group>
            <el-menu-item index="2-1">选项1</el-menu-item>
            <el-menu-item index="2-2">选项2</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="text-align: right; font-size: 24px">
        <el-dropdown>
          <span class="el-dropdown-link" style="font-size: 20px; color: aliceblue">
            <el-image  :src="url" :fit="fit" style="width: 30px; height: 30px"></el-image>
            {{name}}
            <i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown" style="width: 100px">
            <el-link href="/#/student_info">
              <el-dropdown-item style="font-size: 15px">个人信息</el-dropdown-item>
            </el-link>
            <el-link href="/#/logout">
              <el-dropdown-item style="font-size: 15px">退出登录</el-dropdown-item>
            </el-link>
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>

      <el-main>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: 'StudentMain',
  mounted: function () {
    this.getInfo()
  },
  methods: {
    getInfo () {
      this.$axios({
        method: 'get',
        url: 'http://localhost:8080/vue/student/info'
      }).then(function (res) {
        const info = res.data
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
      fit: 'cover',
      url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
      name: ''
    }
  }
}
</script>

<style scoped>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }
  .el-dropdown-link {
    cursor: pointer;
  }
  .el-aside {
    color: #333;
  }
</style>
