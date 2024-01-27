<template>
  <div id="tag" class="columns">
    <div class="column is-three-quarters">
      <el-card class="box-card" shadow="never">
        <div slot="header" class="clearfix el-icon-search">
          检索到 <span class="has-text-info">{{ topics.length }}</span> 篇有关
          <span class="has-text-info">{{ this.$route.params.name }}</span>
          的文章
        </div>

        <div>
          <PostList :articleList="topics"/>
        </div>

        <!--分页-->
        <pagination
          v-show="page.total > 0"
          class="mt-5"
          :total="page.total"
          :page.sync="page.current"
          :limit.sync="page.size"
          @pagination="fetchList"
        />

      </el-card>
    </div>

    <div class="column">
      <el-card shadow="hover">
        <div slot="header" class="el-icon-magic-stick">
          热门标签
        </div>
        <div>
          <ul>
            <li v-for="(tag,index) in tags" :key="index" style="padding: 6px 0">
              <router-link :to="{name:'tag',params:{name:tag.name}}">
                <span v-if="index<9" class="tag">
                  0{{ parseInt(index) + 1 }}
                </span>
                <span v-else class="tag">
                  {{ parseInt(index) + 1 }}
                </span>
                {{ tag.name }}
              </router-link>
            </li>
          </ul>
        </div>
      </el-card>
    </div>
  </div>

</template>

<script>
import { getTopicsByTag } from '@/api/tag'
import pagination from '@/components/Pagination/index'
import PostList from '@/views/post/PostList'

export default {
  name: 'Tag',
  components: {pagination ,PostList},
  data() {
    return {
      topics: [],
      tags: [],
      paramMap: {
        name: this.$route.params.name,
        page: 1,
        size: 10
      },
      page: {
        current: 1,
        size: 5,
        total: 0
      },
    }
  },
  created() {
    this.fetchList()
  },
  methods: {
    fetchList: function() {
      getTopicsByTag(this.paramMap).then(response => {
        console.log(response)
        this.topics = response.data.topics.records
        this.page.current = response.data.topics.current
        this.page.size = response.data.topics.size
        this.page.total = response.data.topics.total
        this.tags = response.data.hotTags.records
      })
    }
  }
}
</script>

<style scoped>
#tag {
  min-height: 500px;
}
</style>
