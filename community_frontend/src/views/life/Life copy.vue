<template>
  <div>
    <div id="app"></div>
    <!-- <div id="loading">Loading...</div> -->
    <!--  -->
  </div>
</template>

<script>

import Masonry from './masonry.js'

export default {
  name: "Life",
  components: {},
  data() {
    return {
      datalist: [{"src":"https://img-blog.csdnimg.cn/20210208150659161.png","height":500,"width":240},
            {"src":"https://img-blog.csdnimg.cn/20210208151656979.jpeg","height":400,"width":80},
            {"src":"https://img-blog.csdnimg.cn/20210208173437518.jpg","height":60,"width":130}
            ]
    };
  },
  mounted() {
      const basewidth = document.getElementById("app").offsetWidth
      const container = document.querySelector("#app")
      const masonry = new Masonry(container,basewidth,this.datalist)
      masonry.init()
    },
  methods: {
  }
};
</script>

<style scoped>
  @import '../../style/main.css';
</style>
