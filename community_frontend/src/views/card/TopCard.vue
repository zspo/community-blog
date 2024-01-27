<template>
  <el-carousel>
    <el-carousel-item v-for="item in 4" :key="item">
      <h3> 文章 </h3>
    </el-carousel-item>
  </el-carousel>
</template>

<style>
.el-carousel {
  border-radius: 8px;
}

.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  text-align: left;
  margin-left: 20%;
}

.el-carousel__item:nth-child(2n) {
    /* other styles */
/* background: rgba(255, 255, 255, .7); */
/* -webkit-backdrop-filter: blur(10px);
backdrop-filter: blur(10px); */
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
</style>