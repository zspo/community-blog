<template>
  <div>
    <el-card shadow="never">
      <div slot="header" class="clearfix">
        🔍 检索到 <span class="has-text-info">{{ list.length }}</span>
        条关于 <span class="has-text-info">{{ query.keyword }}</span> 的记录
      </div>
      <div>
        <PostList :articleList="list"/>
      </div>

      <!--分页-->
      <pagination
        v-show="query.total > 0"
        :total="query.total"
        :page.sync="query.pageNum"
        :limit.sync="query.pageSize"
        @pagination="fetchList"
      />
    </el-card>
  </div>
</template>

<script>
import { searchByKeyword } from '@/api/search'
import Pagination from '@/components/Pagination'
import PostList from '@/views/post/PostList'

export default {
  name: 'Search',
  components: { Pagination ,PostList},
  data() {
    return {
      list: [],
      query: {
        keyword: this.$route.query.key,
        pageNum: 1,
        pageSize: 10,
        total: 0
      }
    }
  },
  created() {
    this.fetchList()
  },
  methods: {
    fetchList() {
      searchByKeyword(this.query).then(value => {
        const { data } = value
        this.list = data.records
        this.query.total = data.total
        this.query.pageSize = data.size
        this.query.pageNum = data.current
      })
    }
  }
}
</script>

<style scoped>

</style>
