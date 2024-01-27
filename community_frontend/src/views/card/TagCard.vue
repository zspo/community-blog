<template>
  <el-card class="box-card" shadow="never">
    <div slot="header">
        <span class="el-icon-sugar"> 标签 </span>
    </div>
    <div>
        <span
            v-for="(tag, index) in tagList"
            :key="index"
            class="tag is-hidden-mobile is-success is-light mr-1"
            >
            <router-link :to="{ name: 'tag', params: { name: tag.tagName } }">
                {{ tag.tagName }}
            </router-link>
        </span>
    </div>
  </el-card>
</template>

<script>
import { getAllTagsList } from '@/api/tag'

export default {
  name: "TagCard",
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

</style>