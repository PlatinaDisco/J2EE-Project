<template>
  <el-container style="height: 900px; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <el-menu :default-openeds="['1', '2']" style="height: 898px">
        <el-link href="/#/student_main">
          <el-menu-item index="student_main">
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
            StudentID&Name
            <i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-link herf="/#/student_info">
              <el-dropdown-item style="font-size: 15px">个人信息</el-dropdown-item>
            </el-link>
            <el-dropdown-item style="font-size: 15px">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>

      <el-main class="info" style="margin-top: 150px">
        <el-col :span="5"><br/></el-col>
        <el-col :span="16">
          <el-card class="box-card" style="width: 80%; height: 400px">
            <div style="font-size: 10px; color: #B3C0D1">
              <i class="el-icon-warning"></i>点击头像可进行更换
            </div>
            <el-col :span="2"><br/></el-col>
            <el-col :span="9" style="margin-top: 30px">
              <el-upload
                class="avatar-uploader"
                action="https://jsonplaceholder.typicode.com/posts/"
                :show-file-list="false"
                :on-success="handleAvatarSuccess"
                :before-upload="beforeAvatarUpload">
                <img v-if="imageUrl" :src="imageUrl" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              </el-upload>
              <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign">
                <el-form-item label="姓名">
                  <el-input v-model="formLabelAlign.name"></el-input>
                </el-form-item>
              </el-form>
            </el-col>
            <el-col :span="2"><br/></el-col>
            <el-col :span="9">
              <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign" style="margin-top: 100px">
                <el-form-item label="学号">
                  <el-text v-model="formLabelAlign.studentID"></el-text>
                </el-form-item>
                <el-form-item label="邮箱" style="margin-top:30px">
                  <el-text v-model="formLabelAlign.email"></el-text>
                </el-form-item>
                <el-button type="primary" @click="modify" style="margin-top:70px">保存修改</el-button>
                <el-button @click="reset" style="margin-left: 30px">重置</el-button>
              </el-form>
            </el-col>
            <el-col :span="2"><br/></el-col>
          </el-card>
        </el-col>
        <el-col :span="5"><br/></el-col>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: 'StudentInfo',
  data () {
    return {
      imageUrl: '',
      labelPosition: 'right',
      formLabelAlign: {
        name: '',
        studentID: '',
        email: ''
      }
    }
  },
  methods: {
    handleAvatarSuccess (res, file) {
      this.imageUrl = URL.createObjectURL(file.raw)
    },
    beforeAvatarUpload (file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    }
  }
}

</script>

<style scoped>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
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
