<!--评论模块-->
<template>
<el-card class="box-card" shadow="never">
        <div slot="header">
          <div class="title is-5 el-icon-chat-line-square">评论</div>
        </div>
        <b-field>
          <el-input class=""
                      v-model.lazy="postComment"
                      type="textarea"
                      :rows="3"
                      autofocus
                      clearable
                      show-word-limit
                      maxlength="400"
                      minlength="1"
                      placeholder="留下你的评论吧！"
                      :disabled="isLoading_post">
          </el-input>
        </b-field>
        <div class="btn-control">
            <el-button class="comfirm" type="success" :disabled="isLoading_post" round @click="commitPostComment(topicId)">
              评论
            </el-button>
        </div>
<el-divider></el-divider>
  <div class="">
    <div class="comment" v-for="(item, index) in comments" :key="index">
      <div class="info">
        <img class="avatar" :src="item.fromAvatar" width="36" height="36" :key="item.fromAvatar"/>
        <div class="right">
          <router-link :to="{ path: `/member/${item.user_name}/home` }">
            <div class="name">{{item.fromName}}</div>
          </router-link>
          <div class="date">{{ dayjs(item.date).format('YYYY/MM/DD HH:mm:ss')}}</div>
        </div>
      </div>
      <div class="content">{{item.content}}</div>
      <div class="control">
        <span class="like" :class="{active: item.isLike}" @click="likeClick(item)">
          <i class="iconfont icon-like"></i>
          <span class="like-num el-icon-thumb">{{item.likeNum > 0 ? item.likeNum + '人赞' : '赞'}}</span>
        </span>
        <span class="comment-reply" @click="showCommentInput(item)">
          <i class="iconfont icon-comment"></i>
          <span class="el-icon-chat-round">回复</span>
        </span>
      </div>
      
      <div class="reply">
        <div class="item" v-for="(reply, index) in item.reply" :key="index">
          <div class="reply-content">
            <router-link :to="{ path: `/member/${reply.user_name}/home` }"><span class="from-name">{{reply.fromName}}</span></router-link><span>: </span>
            <router-link :to="{ path: `/member/${reply.to_user_name}/home` }"><span class="to-name">@{{reply.toName}}</span></router-link>
            <span>{{reply.content}}</span>
          </div>
          <div class="reply-bottom">
            <span class="date">{{dayjs(reply.date).format('YYYY/MM/DD HH:mm:ss')}}</span>
            <span class="reply-text" @click="showCommentInput(item, reply)">
              <i class="iconfont icon-comment"></i>
              <span class="el-icon-chat-round">回复</span>
            </span>
          </div>
        </div>
        <!-- <div class="write-reply" v-if="item.reply.length > 0" @click="showCommentInput(item)">
          <i class="el-icon-edit"></i>
          <span class="add-comment">添加新评论</span>
        </div> -->
        <transition name="fade">
          <div class="input-wrapper" v-if="showItemId === item.id">
            <b-field>
            <el-input class=""
                      v-model.lazy="replyComment"
                      type="textarea"
                      :rows="2"
                      autofocus
                      clearable
                      show-word-limit
                      maxlength="400"
                      placeholder="写下你的评论"
                      :disabled="isLoading_reply">
            </el-input>
            </b-field>
            <div class="btn-control">
              <span class="cancel" @click="cancel">取消</span>
              <el-button class="comfirm" type="success" :disabled="isLoading_reply" round @click="commitReplyComment(topicId,item)">
                确定
              </el-button>
            </div>
          </div>
        </transition>
      </div>
    </div>
  </div>
</el-card>
</template>

<script>
import { pushComment } from '@/api/comment'
import { mapGetters } from 'vuex'
import Vue from 'vue'

export default {
  props: {
    comments: {
      type: Array,
      required: true
    },
    topicId: {
      type: String,
      default: ''
    }
  },
  components: {},
  data() {
    return {
      postComment: '',
      replyComment: '',
      showReplyInfo: '',
      showItemId: '',
      isLoading_post: false,
      isLoading_reply: false
    }
  },
  computed: {
      ...mapGetters([
      'token'
    ])
  },
  methods: {
    /**
     * 点赞
     */
    likeClick(item) {
      if (item.isLike === null) {
        Vue.$set(item, "isLike", true);
        item.likeNum++
      } else {
        if (item.isLike) {
          item.likeNum--
        } else {
          item.likeNum++
        }
        item.isLike = !item.isLike;
      }
    },

    /**
     * 点击取消按钮
     */
    cancel() {
      this.showItemId = ''
    },

    /**
     * 提交评论
     */
    async commitPostComment(topicId) {
      if(this.token != null && this.token !== '') {
        this.isLoading_post = true
        try {
          if (
            this.postComment == ''
          ) {
              alert('评论内容不可为空')
              return false
          }
          let postData = {}
          console.log(this.postComment);
          postData['content'] = this.postComment
          postData['topic_id'] = topicId
          postData['like'] = 0
          await pushComment(postData)
          this.$parent.getCommentByTopicId(topicId)
          this.$message.success('评论成功')
        } catch (e) {
          this.$buefy.toast.open({
            message: `Cannot comment this story. ${e}`,
            type: 'is-danger'
          })
        } finally {
          this.isLoading_post = false
        }
      } else {
        this.$message.success('请先登录')
      }
    },
    async commitReplyComment(topicId,item) {
      if(this.token != null && this.token !== '') {
        this.isLoading_reply = true
        try {
          let postData = {}
          console.log(this.replyComment);
          postData['content'] = this.replyComment
          postData['topic_id'] = topicId
          postData['reply_id'] = item.fromId
          postData['parent_id'] = item.id
          await pushComment(postData)
          this.$parent.getCommentByTopicId(topicId)
          this.$message.success('评论成功')
        } catch (e) {
          this.$buefy.toast.open({
            message: `Cannot comment this story. ${e}`,
            type: 'is-danger'
          })
        } finally {
          this.isLoading_reply = false
        }
      } else {
        this.$message.success('请先登录')
      }
    },

    /**
     * 点击评论按钮显示输入框
     * item: 当前大评论
     * reply: 当前回复的评论
     */
    showCommentInput(item, reply) {
      if(this.token != null && this.token !== '') {
        if (reply) {
          this.replyComment = "@" + reply.fromName + " "
        } else {
          this.replyComment = "@" + item.fromName + " "
        }
        this.showItemId = item.id
      } else {
        this.$message.success('请先登录')
      }
    }
  },
  created() {
    console.log(this.comments)
  }
}
</script>

<style scoped lang="scss">

@import "@/assets/scss/index.scss";

.container {
  padding: 0 10px;
  box-sizing: border-box;
  .comment {
    display: flex;
    flex-direction: column;
    padding: 10px;
    border-bottom: 1px solid $border-fourth;
    .info {
      display: flex;
      align-items: center;
      .avatar {
        border-radius: 50%;
      }
      .right {
        display: flex;
        flex-direction: column;
        margin-left: 10px;
        .name {
          font-size: 14px;
          color: $text-main;
          margin-bottom: 5px;
          font-weight: 500;
        }
        .date {
          font-size: 12px;
          color: $text-minor;
        }
      }
    }
    .content {
      font-size: 14px;
      color: $text-main;
      line-height: 20px;
      padding: 11px 0;
      margin-bottom: 5px;
    }
    .control {
      display: flex;
      align-items: center;
      font-size: 14px;
      color: $text-minor;
      .like {
        display: flex;
        align-items: center;
        margin-right: 20px;
        cursor: pointer;
        &.active, &:hover {
          color: $color-main;
        }
        .iconfont {
          font-size: 14px;
          margin-right: 5px;
        }
      }
      .comment-reply {
        display: flex;
        align-items: center;
        cursor: pointer;
        &:hover {
          color: $text-333;
        }
        .iconfont {
          font-size: 12px;
          margin-right: 5px;
        }
      }

    }
    .reply {
      margin: 10px 0;
      border-left: 2px solid $border-first;
      .item {
        margin: 0 10px;
        padding: 10px 0;
        border-bottom: 1px dashed $border-third;
        .reply-content {
          display: flex;
          align-items: center;
          font-size: 14px;
          color: $text-main;
          .from-name {
            color: $color-main;
          }
          .to-name {
            color: $color-main;
            margin-left: 5px;
            margin-right: 5px;
          }
        }
        .reply-bottom {
          display: flex;
          align-items: center;
          margin-top: 6px;
          font-size: 14px;
          color: $text-minor;
          .date {
              font-size: 12px;
              color: $text-minor;
          }
          .reply-text {
            display: flex;
            align-items: center;
            margin-left: 10px;
            cursor: pointer;
            &:hover {
              color: $text-333;
            }
            .icon-comment {
              margin-right: 5px;
            }
          }
        }
      }
      .write-reply {
        display: flex;
        align-items: center;
        font-size: 14px;
        color: $text-minor;
        padding: 10px;
        cursor: pointer;
        &:hover {
          color: $text-main;
        }
        .el-icon-edit {
          margin-right: 5px;
        }
      }
      .fade-enter-active, fade-leave-active {
        transition: opacity 0.5s;
      }
      .fade-enter, .fade-leave-to {
        opacity: 0;
      }
      .input-wrapper {
        padding: 10px;
        .gray-bg-input, .el-input__inner {
          background-color: #67C23A;
        }
        .btn-control {
          display: flex;
          justify-content: flex-end;
          align-items: center;
          padding-top: 5px;
          .cancel {
            font-size: 12px;
            color: $text-normal;
            margin-right: 20px;
            cursor: pointer;
            &:hover {
              color: $text-333;
            }
          }
          .confirm {
            font-size: 12px;
          }
        }
      }
    }
  }
}
</style>

