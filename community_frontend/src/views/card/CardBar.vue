<template>
  <section>
    <!--是否登录-->
    <!--如果登陆且用户id为管理员，才能发文章 -->
    <div
      v-if="token && user.username === 'admin'"
    >
      <login-welcome />
    </div>

    <hot-post />

    <tag-card />

    <!--今日赠言-->
    <tip-card />

    <!--资源推介-->
    <!-- <PromotionCard /> -->
  </section>
</template>

<script>
import BlogTips from '@/views/card/BlogTips'
import TipCard from '@/views/card/Tip'
// import PromotionCard from '@/views/card/Promotion'
import TagCard from '@/views/card/TagCard'
import LoginWelcome from '@/views/card/LoginWelcome'
import HotPost from '@/views/post/HotPost'

import { mapGetters } from 'vuex'

export default {
  name: 'CardBar',
  components: { LoginWelcome, BlogTips, TipCard, HotPost, TagCard},
  computed: {
    ...mapGetters([
      'token','user'
    ])
  },
}
</script>

<style scoped>
</style>
