<template>
  <div>
    <TopCard></TopCard>
    <div class="mb-5"></div>
    <div class="columns">
      <div class="column is-three-quarters">
        <TopicList></TopicList>
      </div>
      <div class="column">
        <CardBar></CardBar>
      </div>
    </div>
  </div>
</template>

<script>
import CardBar from "@/views/card/CardBar"
import PostList from '@/views/post/Index'
import TopCard from "@/views/card/TopCard"

export default {
  name: "Home",
  components: {TopCard, CardBar, TopicList: PostList},
};
</script>
