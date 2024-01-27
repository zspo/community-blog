<template>
  <el-card class="box-card" shadow="never">
    <div slot="header">
      <!-- <span class="has-text-weight-bold">相关推荐</span> -->
      <div class="title is-5 el-icon-cold-drink">相关推荐</div>
    </div>
    <!-- <div>
      <p v-for="(item,index) in recommend" :key="index" :title="item.title" class="block ellipsis is-ellipsis-1">
        <router-link :to="{name:'post-detail',params: { id: item.id }}">
          <span v-if="index<9" class="tag">
            0{{ parseInt(index) + 1 }}
          </span>
          <span v-else class="tag">
            {{ parseInt(index) + 1 }}
          </span>
          {{ item.title }}
        </router-link>
      </p>
    </div> -->

       <el-carousel :interval="4000" type="card" height="200px">
        <el-carousel-item v-for="(item,index) in recommend" :key="index">
          <!-- <h3 class="medium"> {{ parseInt(index) + 1 }} {{ item.title }} </h3> -->
          <div>{{ parseInt(index) + 1 }} {{ item.title }}</div>
        </el-carousel-item>
      </el-carousel>
  </el-card>
</template>

<script>
import { getRecommendTopics } from '@/api/post'

export default {
  name: 'Recommend',
  props: {
    topicId: {
      type: String,
      default: null
    }
  },
  data() {
    return {
      recommend: []
    }
  },
  created() {
    this.fetchRecommendTopics()
  },
  methods: {
    fetchRecommendTopics() {
      getRecommendTopics(this.topicId).then(value => {
        const { data } = value
        this.recommend = data
      })
    }
  }
}
</script>

<style scoped>

.el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }
  
.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
</style>
