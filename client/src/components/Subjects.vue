 <template>
  <el-container>
    <el-header style="text-align: left">
      <h1>全部课程</h1>
    </el-header>
    <el-main>
      <el-table :data="subjects">
        <el-table-column prop="subjectName" label="科目名称"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button @click="showSubject(scope.$index)" type="text" size="small">查看</el-button>
            <el-button @click="showForum(scope.$index)" type="text" size="small">访问论坛</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-main>
  </el-container>
</template>

<script>
    import App from "../App.vue"
    export default {
        name: "Subjects",
        data() {
          return {
            subjects: [],
            result: "",
          }
        },
        methods: {
          showSubject(index) {
            let subjectName = this.subjects[index].subjectName
            localStorage.setItem("subjectName", subjectName)
            this.$router.push("/subject/main")
          },
          showForum(index) {
            let forumName = this.subjects[index].forum.forumName
            localStorage.setItem("forumName", forumName)
            this.$router.push("/forum/main")
          },
          getAllApprovedSubjects() {
            this.axios.post(App.path() + "/api/subject/showApprovedSubjects")
              .then((res) => {
                this.subjects = res.data
                console.log(this.subjects)
              })
          }
        },
        mounted: function () {
          this.getAllApprovedSubjects()
        }
    }
</script>

<style scoped>

</style>
