<template>
  <footer class="footer">
    <div class="container">

      <!-- <div class="footerlink">
            <template>
              <a class="text" href="/">首页</a>
              <a class="text" href="/category">分类</a>
              <a class="text" href="/tag">标签</a>
            </template>
      </div> -->

      <div class="footerlink">

        <div><a href="http://beian.miit.gov.cn">沪ICP备</a></div>
        <div><a>{{ title }} ALL RIGHTS RESERVED</a></div>
      
      </div>

    </div>
    <back-top></back-top>
  </footer>
</template>

<script>
import BackTop from "@/components/Backtop/BackTop";

export default {
  name: "Footer",
  components: {
    BackTop
  },
  data() {
    return {
      title: "© " + new Date().getFullYear() + ' 冰河映寒星',
      author: '冰河映寒星',
    };
  },
};
</script>

<style scoped>

footer {
  margin-top: 100px;
  height: 70px;
  background-color: #FFF;
}
footer a{
  color: #9c9c9c;
}

.footerlink {
  text-align: center;
  margin: 0 auto;
  /* width: 1000px; */
  padding: 0;
  line-height: 26px;
  font-size: 0.75rem;
  text-overflow: ellipsis;
  margin-bottom: 1.5rem;
  margin-top: 0.25rem;
  /* display: flex; */
}

.footerlink .text {
  text-align: center;
  margin: 0 auto;
  margin-right: 1.5rem;
  margin-bottom: 0.25rem;
}


</style>