<template>
<div class="columns post_all_page">
    <div class="columns">
      <div class="column">
          <!-- <el-card class="box-card" shadow="hover">
            <div slot="header" class="title is-6  el-icon-notebook-2">
                  目录
                </div>
            <div>
              <el-tree class="tree" ref="tree" node-key="uuid" :data="treeData" :props="defaultProps" default-expand-all>
                <div class="custom-tree-node" slot-scope="{ node, data }">
                  <div @click="toDiv(data.uuid)">{{ data.text }}</div>
                </div>
              </el-tree>
            </div>
          </el-card> -->
          <catalogue></catalogue>
      </div>
      <div class="details">
          <el-card class="box-card" shadow="never" >
            <div slot="header" class="">
              <span class="is-size-3 has-text-weight-bold title">{{ topic.title }}</span>
              <div class="has-text-grey is-size-7 mt-3">
                <span class="el-icon-user">
                  <router-link :to="{ path: `/member/${topicUser.username}/home`}"> {{ topicUser.alias }}</router-link>
                </span>
                <el-divider direction="vertical" />
                <span class="el-icon-date"> {{ dayjs(topic.createTime).format('YYYY/MM/DD') }}</span>
                <el-divider direction="vertical" />
                <span class="el-icon-view"> {{ topic.view }} </span>
              </div>
            </div>

            <!--Markdown-->
            <div id="preview" />
            
            <!--标签-->
            <nav class="level has-text-grey is-size-7 mt-6">
              <div class="level-left">
                <p class="level-item">
                  <b-taglist>
                    <router-link
                      v-for="(tag, index) in tags"
                      :key="index"
                      :to="{ name: 'tag', params: { name: tag.name } }"
                    >
                      <b-tag type="is-info is-light mr-1">
                        {{ "#" + tag.name }}
                      </b-tag>
                    </router-link>
                  </b-taglist>
                </p>
              </div>

            </nav>

              <div class="level-right">
                <a class="level-item">
                  <span class="like" :class="{active: topic.isLike}" @click="likeClick(topic)">
                    <i class="iconfont icon-like"></i>
                    <span class="like-num el-icon-thumb">{{topic.like > 0 ? topic.like + '人点赞' : '点赞'}}</span>
                  </span>
                </a>
                <a class="level-item">
                  <span class="like-num el-icon-star-off">{{topic.collects > 0 ? topic.collects + '人收藏' : '收藏'}}</span>
                </a>
                <a class="level-item">
                  <span class="el-icon-share"> 分享 </span>
                </a>
                <div v-if="token && user.id === topicUser.id" class="level-right" >
                    <router-link class="level-item" :to="{name:'topic-edit',params: {id:topic.id}}">
                      <span class="el-icon-edit"> 编辑 </span>
                    </router-link>
                    <a class="level-item">
                      <span class="el-icon-delete" @click="handleDelete(topic.id)"> 删除 </span>
                    </a>
                </div>
              </div>

          </el-card>
          
          <!-- <lv-comments :slug="topic.id" />  -->
          <comment :comments="comments" :topicId="topic.id"></comment>

          <recommend v-if="flag" :topic-id="topic.id"/>
      </div>
    </div>
</div>
</template>

<script>
import { deleteTopic, getTopic } from '@/api/post'
import { fetchCommentsByTopicId } from '@/api/comment'
import { mapGetters } from 'vuex'
import Author from '@/views/post/Author'
import Recommend from '@/views/post/Recommend'
import Catalogue from '@/views/post/Catalogue'
import comment from '../../components/Comment/NewComments'
import Vditor from 'vditor'
import 'vditor/dist/index.css'
import Vue from 'vue'

// import * as CommentData from '../../components/Comment/mockdata'

export default {
  name: 'TopicDetail',
  components: { Author, Recommend, comment, Catalogue },
  computed: {
    ...mapGetters([
      'token','user'
    ])
  },
  data() {
    return {
      flag: false,
      topic: {
        content: '',
        id: this.$route.params.id
      },
      tags: [],
      topicUser: {},
      comments: [],
      topicId: this.$route.params.id,
      // treeData: [],
      // defaultProps: {
      //   label: 'text',
      //   children: 'children',
      // },
    }
  },
  created() {
    this.$nextTick(() => {
        this.fetchTopic()
        this.getCommentByTopicId(this.$route.params.id)
			})
  },
  // computed: {
	// 		content() {
	// 			return marked(this.topic.content)
	// 		}
	// 	},
  // mounted() {
  //   // this.getCatalog()
  //   this.getCommentByTopicId(this.$route.params.id)
  // },
  methods: {
    renderMarkdown(md) {
      Vditor.preview(document.getElementById('preview'), md, {
        hljs: { style: 'github' },
        // markdown: { toc: true,
        //             paragraphBeginningSpace: true,
        //           }
      })
    },
    // 初始化
    async fetchTopic() {
      getTopic(this.$route.params.id).then(response => {
        const { data } = response
        document.title = data.topic.title

        this.topic = data.topic
        this.tags = data.tags
        this.topicUser = data.user
        // this.comments = data.comments
        this.renderMarkdown(this.topic.content)
        this.flag = true
      })
    },
    handleDelete(id) {
      deleteTopic(id).then(value => {
        const { code, message } = value
        alert(message)

        if (code === 200) {
          setTimeout(() => {
            this.$router.push({ path: '/' })
          }, 500)
        }
      })
    },
    getCommentByTopicId(id) {
      fetchCommentsByTopicId(id).then(response => {
        const {data} = response
        this.comments = data
      })
    },
    likeClick(item) {
        if (item.isLike === null) {
          Vue.$set(item, "isLike", true);
          item.like++
        } else {
          if (item.isLike) {
            item.like--
          } else {
            item.like++
          }
          item.isLike = !item.isLike;
        }
    },
  }
}
</script>

<style>
#preview {
  min-height: 300px;
}
.post_all_page {
  padding: 20px;
}
.details {
  width: 1000px;
  margin: auto;
  padding: 10px 10px;
}
.title {
  line-height: 1.22;
  margin: 5px 0; 
}
</style>
