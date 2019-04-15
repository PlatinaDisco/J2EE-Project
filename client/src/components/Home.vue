<template>
 <el-container>
   <el-main>
     <el-button @click="toStudent">学生登录</el-button>
     <el-button @click="toInstructor">教师登录</el-button>
   </el-main>
 </el-container>
</template>

<script>
    export default {
      name: "Home",
      methods: {
        toStudent() {
          this.$router.push({path: "/student/login"})
        },
        toInstructor() {
          this.$router.push({path: "/instructor/login"})
        }
      }
    }

</script>

<style scoped>

</style>
