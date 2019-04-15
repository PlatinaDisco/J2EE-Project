<template>
  <el-container>
    <el-main>
      <b>{{post.title}}</b>
      <el-form :inline="true">
        <el-form-item>
          <b>帖号：</b><span>{{post.id}}</span>
        </el-form-item>
        <el-form-item>
          <b>发帖时间：</b><span>{{post.postTime}}</span>
        </el-form-item>
        <el-form-item>
          <b>发帖人：</b><span>{{post.author}}</span>
        </el-form-item>
      </el-form>
      <b>内容：</b><span>{{post.content}}</span>
      <el-table :data="post.returnPosts" style="width: 100%">
        <el-table-column prop="id" width="100" label="回帖号"></el-table-column>
        <el-table-column prop="replyTime" width="180" label="回帖时间"></el-table-column>
        <el-table-column prop="author" width="100" label="回复者"></el-table-column>
        <el-table-column prop="content" label="内容"></el-table-column>
        <el-table-column label="操作" width="100" fixed="right">
          <template slot-scope="scope">
            <el-button @click="showComments(scope.row)" type="text" size="small">查看评论</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-dialog title="评论" style="max-height: available" :visible.sync="returnPostContentDialogVisible">
        <option v-for="string in temp.comments" v-bind:value="string">
          {{ string }}
        </option>
        <el-form>
          <el-input v-model="comment" auto-complete="off"></el-input>
          <el-button type="primary" @click="writeComment">保存</el-button>
        </el-form>
      </el-dialog>
    </el-main>
    <el-footer>
      <el-form :inline="true">
        <el-button type="primary" @click="writeReturnPostDialogVisible=true">写回帖</el-button>
        <el-button type="primary" @click="gotoForum">返回</el-button>
      </el-form>
      <el-dialog title="发表回复帖" :visible.sync="writeReturnPostDialogVisible">
        <el-form :model="returnPost" status-icon ref="returnPost" class="demo-createReturnPost">
          <el-form-item prop="content">
            <el-input v-model="returnPost.content" type="textarea" :rows="5" placeholder="请输入内容"></el-input>
          </el-form-item>
        </el-form>
        <el-button type="primary" @click="writeReturnPost">发帖</el-button>
        <el-button @click="postingDialogVisible=false">取消</el-button>
      </el-dialog>
    </el-footer>
  </el-container>
</template>

<script>
    import App from "../App.vue"
    export default {
        name: "PostMain",
        data() {
          return {
            result: {},
            comment: "",
            comments: [],
            temp: {
              id: "",
              content: "",
              replyTime: "",
              author: "",
              comments: []
            },
            returnPostContentDialogVisible: false,
            writeReturnPostDialogVisible: false,
            post: {
              id: "",
              title: "",
              content: "",
              author: "",
              postTime: "",
              returnPosts: []
            },
            returnPost: {
              id: "",
              content: "",
              replyTime: "",
              author: "",
              comments: []
            }
          }
        },
        methods: {
          writeReturnPost() {
            let params = new URLSearchParams()
            params.append("postId", localStorage.getItem("postId"))
            params.append("content", this.returnPost.content)
            params.append("author", localStorage.getItem("name"))
            this.axios.post(App.path() + "/api/forum/writeReturnPost", params)
              .then((res) => {
                this.result = res.data
                if(this.result !== null) {
                  this.writeReturnPostDialogVisible = false
                  this.getPost()
                }
                else {
                  alert("创建失败")
                }
              })
          },
          writeComment() {
            let params = new URLSearchParams()
            params.append("returnPostId", this.temp.id)
            params.append("content", this.comment)
            this.axios.post(App.path() + "/api/forum/writeComment", params)
              .then((res) => {
                this.getReturnPost(this.temp.id)
              })
          },
          showComments(row) {
            this.temp = row
            this.returnPostContentDialogVisible = true
          },
          gotoForum() {
            this.$router.push("/forum/main")
          },
          getReturnPost(returnPostId) {
            let param = new URLSearchParams()
            param.append("returnPostId", returnPostId)
            this.axios.post(App.path() + "/api/forum/showReturnPost", param)
              .then((res) => {
                this.returnPost = res.data
              })
          },
          getPost() {
            let postId = localStorage.getItem("postId")
            let param = new URLSearchParams()
            param.append("postId", postId)
            this.axios.post(App.path() + "/api/forum/showPost", param)
              .then((res) => {
                this.post = res.data
                console.log(this.post)
              })
          }
        },
        mounted: function () {
          this.getPost()
        }
    }
</script>

<style scoped>

</style>
