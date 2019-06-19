<template>
  <el-container style="height: 700px; border: 1px solid #eee">
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
    <el-aside width="200px" class="el-aside">
      <!--<el-row >-->
      <!--<el-col >-->
      <el-menu
        default-active="2"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b" style="height: 588px;">
        <el-menu-item index="1">
          <i class="el-icon-menu"></i>
          <span slot="title">主页</span>
        </el-menu-item>
        <el-menu-item index="2">
          <i class="el-icon-document"></i>
          <span slot="title" href="/#/teacher_info" @click="info">个人信息</span>
        </el-menu-item>
        <el-menu-item index="3">
          <i class="el-icon-setting"></i>
          <span slot="title">创建课程</span>
        </el-menu-item>
        <el-menu-item index="4">
          <i class="el-icon-setting"></i>
          <span slot="title">发布课程</span>
        </el-menu-item>
        <el-menu-item index="5">
          <i class="el-icon-setting"></i>
          <span slot="title">我的课程</span>
        </el-menu-item>
        <el-menu-item index="5">
          <i class="el-icon-setting"></i>
          <span slot="title">登出</span>
        </el-menu-item>
      </el-menu>
      <!--</el-col>-->
      <!--</el-row>-->
    </el-aside>

    <el-main>
    </el-main>
  </el-container>
</template>

<script>
export default {
  name: 'TeacherInfo',
  data () {
    return {

    }
  }
}
</script>

<style scoped>

</style>
