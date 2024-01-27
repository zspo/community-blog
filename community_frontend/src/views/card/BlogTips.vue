<template>
  <el-card class="box-card" shadow="never">
    <div slot="header">
      <span class="el-icon-bell"> 公告说明 </span>
    </div>
    <div>
        {{ billboard.content }}
    </div>
  </el-card>
</template>

<script>
import { getBillboard } from "@/api/billboard";
export default {
  name: 'BlogTips',
  data() {
    return {
      billboard: {
        content: "",
      },
    };
  },
  created() {
    this.fetchBillboard();
  },
  methods: {
    async fetchBillboard() {
      getBillboard().then((value) => {
        const { data } = value;
        this.billboard = data;
      });
    },
  },
}
</script>

<style scoped>
</style>
