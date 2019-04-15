<template>
  <el-container>
    <el-header>
      <b>{{forum.forumName}}</b>
    </el-header>
    <el-main>
      <el-table :data="forum.posts">
        <el-table-column prop="id" label="帖号"></el-table-column>
        <el-table-column prop="title" label="主题"></el-table-column>
        <el-table-column prop="author" label="发帖人"></el-table-column>
        <el-table-column prop="postTime" label="发帖时间"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button @click="showPost(scope.row)" type="text" size="small">查看</el-button>
          </template>
        </el-table-column>>
      </el-table>
    </el-main>
    <el-footer>
      <el-form :inline="true">
        <el-button type="primary" @click="postingDialogVisible = true">发帖</el-button>
      </el-form>
      <el-dialog title="发帖" :visible.sync="postingDialogVisible">
        <el-form :model="post" status-icon ref="post" class="demo-createPost">
          <el-form-item prop="title">
            <el-input v-model="post.title" placeholder="主题" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item prop="content">
            <el-input v-model="post.content" type="textarea" :rows="5" placeholder="请输入内容"></el-input>
          </el-form-item>
        </el-form>
        <el-button type="primary" @click="posting">发帖</el-button>
        <el-button @click="postingDialogVisible = false">取消</el-button>
      </el-dialog>
    </el-footer>
  </el-container>
</template>

<script>
    import App from "../App.vue"
    export default {
        name: "ForumMain",
        data() {
          return {
            result: {},
            postingDialogVisible: false,
            forum: {
              forumName: "",
              posts: []
            },
            post: {
              id: "",
              title: "",
              content: "",
              author: "",
              postTime: "",
              returnPosts: []
            }
          }
        },
        methods: {
          posting() {
            let params = new URLSearchParams()
            params.append("forumName", this.forum.forumName)
            params.append("title", this.post.title)
            params.append("content", this.post.content)
            params.append("author", localStorage.getItem("name"))
            this.axios.post(App.path() + "/api/forum/posting", params)
              .then((res) => {
                this.result = res.data
                if(this.result !== null) {
                  this.postingDialogVisible = false
                  this.getForum()
                }
                else {
                  alert("创建失败")
                }
              })
          },
          showPost(row) {
            let post1 = row
            localStorage.setItem("postId", post1.id)
            this.$router.push("/post/main")
          },
          getForum() {
            let forumName = localStorage.getItem("forumName")
            let param = new URLSearchParams()
            param.append("forumName", forumName)
            this.axios.post(App.path() + "/api/forum/showForum", param)
              .then((res) => {
                this.forum = res.data
              })
          }
        },
        mounted: function () {
          this.getForum()
        }
    }
</script>

<style scoped>

</style>
