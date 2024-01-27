<template>
  <div class="member">
    <div class="columns">
      <div class="column is-one-quarter">
        <el-card shadow="never">
          <div slot="header" class="has-text-centered">
            <el-avatar :size="64" :src="`${topicUser.avatar}`" :key="`${topicUser.avatar}`"/>
            <p class="is-size-5 mb-3">
                {{ topicUser.alias }} <span class="is-size-7 has-text-grey">{{ '@' + topicUser.username }}</span>
            </p>
            
            <div
              v-if="token && user.id === topicUser.id"
              class="level-right"
            >
            </div>
            <div v-else>
              <button
                v-if="hasFollow"
                class="button is-success button-center is-fullwidth"
                @click="handleUnFollow(topicUser.id)"
              >
                已关注
              </button>
              <button v-else class="button is-link button-center is-fullwidth" @click="handleFollow(topicUser.id)">
                关注
              </button>
            </div>
          </div>
          <div>
            <p class="content">文章：<code>{{ topicUser.topicCount }}</code></p>
            <p class="content">粉丝：<code>{{ topicUser.followerCount }}</code></p>
            <p class="content">入驻：{{ dayjs(topicUser.createTime).format("YYYY/MM/DD") }}</p>
            <p class="content">简介：{{ topicUser.bio }}</p>
          </div>
        </el-card>
      </div>

      <div class="column">
        <!--用户发布的话题-->
        <el-card class="box-card content" shadow="never">
          <div slot="header" class="has-text-weight-bold">
            <span>文章</span>
          </div>

          <div v-if="topics.length===0">
            暂无文章
          </div>

          <div v-else class="topicUser-info">
            <PostList :articleList="topics"/>
          </div>

          <!--分页-->
          <pagination
            v-show="page.total > 0"
            class="mt-5"
            :total="page.total"
            :page.sync="page.current"
            :limit.sync="page.size"
            @pagination="fetchUserById"
          />
        </el-card>
      </div>
    </div>
  </div>
</template>

<script>
import { follow, hasFollow, unFollow } from '@/api/follow'
import { getInfoByName } from '@/api/user'
import pagination from '@/components/Pagination/index'
import { mapGetters } from 'vuex'
import { deleteTopic } from '@/api/post'
import PostList from '@/views/post/PostList'

export default {
  name: 'Profile',
  components: { pagination ,PostList},
  data() {
    return {
      topicUser: {},
      topics: {},
      page: {
        current: 1,
        size: 5,
        total: 0
      },
      hasFollow: false
    }
  },
  mounted() {
    this.fetchInfo()
  },
  computed: {
    ...mapGetters(['token', 'user'])
  },
  created() {
    this.fetchUserById()
  },
  methods: {
    fetchInfo() {
      if(this.token != null && this.token !== '')
      {
        hasFollow(topicUser.id).then(value => {
          const { data } = value
          this.hasFollow = data.hasFollow
        })
      }
    },
    fetchUserById() {
      getInfoByName(this.$route.params.username, this.page.current, this.page.size).then((res) => {
        const { data } = res
        this.topicUser = data.user
        this.page.current = data.topics.current
        this.page.size = data.topics.size
        this.page.total = data.topics.total
        this.topics = data.topics.records
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
    handleFollow: function(id) {
      if(this.token != null && this.token !== '')
      {
        follow(id).then(response => {
          const { message } = response
          this.$message.success(message)
          this.hasFollow = !this.hasFollow
          this.topicUser.followerCount = parseInt(this.topicUser.followerCount) + 1
        })
      }
      else{
        this.$message.success('请先登录')
      }
    },
    handleUnFollow: function(id) {
      unFollow(id).then(response => {
        const { message } = response
        this.$message.success(message)
        this.hasFollow = !this.hasFollow
        this.topicUser.followerCount = parseInt(this.topicUser.followerCount) - 1
      })
    }
  }
}
</script>

<style scoped>

</style>
