<!--老师页面的课程信息-->
<template>
  <el-container style="height: 590px; border: 1px solid #eee">
    <el-aside width="200px" class="el-aside">
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
            <el-menu-item index="2-1">创建课程</el-menu-item>
            <el-menu-item index="2-2">发布课程</el-menu-item>
            <el-menu-item index="2-3">我的课程</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
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
        <el-row>
          <el-col :span="5" v-for="o in 15" :key="o" :offset="1">
            <el-link href = "/#/CurrentCoures">
            <el-card :body-style="{ padding: '10px' }" class="courseCard">
                <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image">
              <div style="padding: 14px;">
                <span>计算机网络</span>
                <div class="bottom clearfix">
                  <time class="time">{{ currentDate }}</time>
                  <el-button type="text" class="button">操作按钮</el-button>
                </div>
              </div>
            </el-card>
            </el-link>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data () {
    return {
      currentDate: new Date()
    }
  }
}
</script>

<style scoped>
  #courseCard  {
    margin:25px 50px 75px 100px;
  }
  .image {
    width: 70%;
  }
  .el-header {
    background-color: #B3C0D1;
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
