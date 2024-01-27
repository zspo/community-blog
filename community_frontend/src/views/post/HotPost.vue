<template>
  <el-card class="box-card" shadow="never">
    <div slot="header">
      <span class="el-icon-hot-water"> 热门文章</span>
    </div>
    <!-- <div>
      <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="周" name="first"></el-tab-pane>
          <el-tab-pane label="月" name="second"></el-tab-pane>
          <el-tab-pane label="全部" name="third"></el-tab-pane>
      </el-tabs>
    </div> -->
    <div>
      <p v-for="(item, index) in list" :key="index" class="block">
        <span>{{ index+1 }}. </span>
        <router-link :to="{name:'post-detail',params:{id:item.id}}">
        <span>{{ item.title }}</span>
        </router-link>

        <!-- <router-link v-if="item.categoryId != '103'" :to="{name:'post-detail',params:{id:item.id}}">
        <span>{{ item.title }}</span>
        </router-link>
        <router-link v-else :to="{name:'life-detail',params:{id:item.id}}">
        <span>{{ item.title }}</span>
        </router-link> -->


        <!-- <a :href="item.link" target="_blank">{{ item.title }}</a> -->
      </p>
    </div>
  </el-card>
</template>

<script>
import { getHotPost } from '@/api/post'

export default {
  name: 'HotPost',
  data() {
    return {
      list: [],
      mode: 'month',
    }
  },
  created() {
    this.fetchList()
  },
  methods: {
    fetchList() {
      getHotPost(this.mode).then((response) => {
        const { data } = response
        this.list = data
      })
    }
  }
}
</script>

<style scoped>
.block {
    margin-bottom: 0.7rem;
}
</style>
