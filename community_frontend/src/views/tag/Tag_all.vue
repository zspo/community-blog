<template>
  <el-card class="box-card" shadow="never">
      <div class="tag-cloud-title">标签 - {{ tagList.length }}</div>
      <div class="tag-cloud">
        <router-link
          :style="{ 'font-size': Math.floor(item.tagCount * 3) + 15 + 'px' }"
          v-for="item of tagList"
          :key="item.tagName"
          :to="'/tag/' + item.tagName"
        >
          {{ item.tagName }} ({{ item.tagCount }})
        </router-link>
      </div>
  </el-card>
</template>

<script>
import { getAllTagsList } from '@/api/tag'

export default {
  name: "Tag_all",
  data() {
      return {
          tagList: [],
      }
  },
  created() {
    this.fetchList()
  },
  methods: {
    fetchList() {
      getAllTagsList().then((response) => {
        const { data } = response
        this.tagList = data
      })
    }
  }
};
</script>

<style scoped>
.tag-banner {
  background: url(https://www.static.talkxj.com/73lleo.png) center center /
    cover no-repeat;
  background-color: #49b1f5;
}
.tag-cloud-title {
  line-height: 2;
  font-size: 30px;
  text-align: center;
}
@media (max-width: 759px) {
  .tag-cloud-title {
    font-size: 25px;
  }
}
.tag-cloud {
  text-align: center;
}
.tag-cloud a {
  color: #616161;
  display: inline-block;
  text-decoration: none;
  padding: 0 8px;
  line-height: 2;
  transition: all 0.3s;
}
.tag-cloud a:hover {
  color: #03a9f4 !important;
  transform: scale(1.1);
}
</style>