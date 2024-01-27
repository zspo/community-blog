<template>
  <div>
    <div class="mb-5">
      <Header></Header>
    </div>
    <div class="container">
        <router-view :key="this.$route.fullPath"></router-view>
    </div>
    <div>
      <Footer></Footer>
    </div>
  </div>
</template>

<script>
import Header from "@/components/Layout/Header";
import Footer from "@/components/Layout/Footer";

export default {
  name: "App",
  components: {Header, Footer },
};
</script>

<style scoped>
.container {
  min-height: 500px;
}
.mb-5 {
    margin-bottom: 2rem !important;
}
</style>
