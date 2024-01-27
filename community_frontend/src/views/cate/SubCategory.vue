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
        <el-card class="box-card" shadow="never">
            <el-tree
            class="filter-tree"
            :data="cateList"
            :props="defaultProps"
            default-expand-all
            ref="tree">
            </el-tree>
        </el-card>
    </div>
  </div>

</template>

<script>
import { getAllCateInfoList, getTopicsBySubCateName } from '@/api/category'
import pagination from '@/components/Pagination/index'
import PostList from '@/views/post/PostList'

export default {
  name: 'SubCategory',
  components: {pagination ,PostList},
  data() {
    return {
      topics: [],
      cateList: [],
      defaultProps: {
        children: 'subCateList',
        label: 'name'
      },
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
    this.fetchCateList()
  },
  methods: {
    fetchCateList() {
      getAllCateInfoList().then((response) => {
        const { data } = response
        this.cateList = data
      })
    },
    fetchList: function() {
      getTopicsBySubCateName(this.paramMap).then(response => {
        console.log(response)
        this.topics = response.data.records
        this.page.current = response.data.current
        this.page.size = response.data.size
        this.page.total = response.data.total
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
