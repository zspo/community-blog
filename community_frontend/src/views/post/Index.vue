<template>
  <div>
    <!-- <el-card>
      <div class="css-1fkiabs-TagsContainer-StyledTags">
        <div v-for="(item, index) in hottaglist" :key="index" class="css-1bn2f9l-TagWrap">
          {{ item }}
        </div>
      </div>
    </el-card> -->
    <el-card shadow="never">
      <div slot="header" class="clearfix">
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="推荐" name="rec">
            <PostList :articleList="articleList"/>
          </el-tab-pane>
          <el-tab-pane label="最新" name="latest">
            <PostList :articleList="articleList"/>
          </el-tab-pane>
          <!-- <el-tab-pane label="热门主题" name="hot">
            <PostList :articleList="articleList"/>
          </el-tab-pane> -->
        </el-tabs>
      </div>

      <!--分页-->
      <pagination
        v-show="page.total > 0"
        :total="page.total"
        :page.sync="page.current"
        :limit.sync="page.size"
        @pagination="init"
      />
    </el-card>
  </div>
</template>

<script>
import { getList } from '@/api/post'
import Pagination from '@/components/Pagination'
import PostList from '@/views/post/PostList'

export default {
  name: 'TopicList',
  components: { Pagination ,PostList},
  data() {
    return {
      activeName: 'rec',
      articleList: [],
      page: {
        current: 1,
        size: 10,
        total: 0,
        tab: 'rec'
      },
      hottaglist: ["java","c++","笔记"]
    }
  },
  created() {
    this.init(this.tab)
  },
  methods: {
    init(tab) {
      getList(this.page.current, this.page.size, tab).then((response) => {
        const { data } = response
        this.page.current = data.current
        this.page.total = data.total
        this.page.size = data.size
        this.articleList = data.records
      })
    },
    handleClick(tab) {
      this.page.current = 1
      this.init(tab.name)
    }
  }
}
</script>

<style scoped>
.css-1fkiabs-TagsContainer-StyledTags {
display: flex;
flex-wrap: wrap;
padding-top: 0px;
margin-bottom: 0px;
}
.css-1bn2f9l-TagWrap {
padding-top: 0px;
padding-right: 8px;
font-size: 12px;
height: 24px;
line-height: 20px;
font-weight: 400;
color: #31c27c;
cursor: pointer;
display: flex;
-webkit-box-align: center;
align-items: center;
background-color: 255,255,255,0.1;
border-top-left-radius: 12px;
border-top-right-radius: 12px;
border-bottom-right-radius: 12px;
border-bottom-left-radius: 12px;
margin-top: 2px;
margin-right: 10px;
margin-bottom: 2px;
margin-left: 0px;
}
</style>
