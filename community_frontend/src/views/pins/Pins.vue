<template>
  <section>

    <div class="columns is-multiline">
      <div class="column is-2">
        <el-card shadow="never">
          <div></div>
          <div class="has-text-centered">
            <ul>
              <li class="content">
                <el-button style="border: none" @click="handleList('random')">最新</el-button>
              </li>
              <li class="content">
                <el-button style="border: none" @click="handleList('hot')">推荐</el-button>
              </li>
              <el-divider></el-divider>
              <li class="content">
                <el-button style="border: none" @click="handleList('follow')">关注</el-button>
              </li>
              <li class="content">
                <el-button style="border: none" @click="handleList('hole')">树洞</el-button>
              </li>
              <li class="content">
                <el-button style="border: none" @click="handleList('lazy')">摸鱼</el-button>
              </li>
            </ul>
          </div>
        </el-card>
      </div>

      <div class="column is-7">
        <el-card shadow="never">
          <div>
            <el-input type="textarea" resize="none" rows="5" placeholder="这里是摸鱼板块，目前尚未开发完成，敬请期待！"></el-input>
          </div>
          <div class="level mt-3">
            <div class="level-left">
              <span class="level-item"><a href="">连接</a></span>
              <span class="level-item"><a href="">图片</a></span>
              <span class="level-item"><a href="">#话题</a></span>
            </div>
            <div class="level-right">
              <el-button style="border: none" type="primary" plain>发布</el-button>
            </div>
          </div>
        </el-card>

        <el-card shadow="never" v-for="item in list" :key="item">
          <div slot="header">
            <div class="level">
              <div class="level-left">
                <div class="level-item">
                  <el-avatar :size="50" :src="circleUrl"></el-avatar>
                </div>
                <div class="level-item">nanaLin</div>
              </div>
              <!-- <div class="level-right">
                <el-button style="border: none" type="warning" plain><i class="fa fa-plus"></i>&nbsp;关注</el-button>
              </div> -->
            </div>
          </div>
          <div class="ml-6">
            <div class="content">
              四目相对
              两顾无言
              这时节，风沙迷人眼
              这时节，天地也垂怜
            </div>

            <div class="level has-text-grey is-size-7 mt-6">
              <div class="level-left">
                <span class="level-item">#摸鱼</span>
              </div>
              <div class="level-right">
                <span class="level-item mr-5"><i class="fa fa-thumbs-o-up"></i> 68</span>

                <span class="level-item mr-5"><i class="fa fa-comment-o">&nbsp;&nbsp;</i>19</span>
                <span class="level-item "><i class="fa fa-reply">&nbsp;&nbsp;</i>12</span>
              </div>
            </div>
          </div>
        </el-card>
      </div>

      <div class="column is-3">
        <el-card shadow="never">
          <div slot="header" class="has-text-weight-bold"> 话题 </div>
          <div class="content">
            #上班摸鱼 #树洞
          </div>
        </el-card>

        <el-card shadow="never">
          <div slot="header" class="has-text-weight-bold"> 热门 </div>
          <div class="content">
            热门沸点
          </div>
        </el-card>

        <el-card shadow="never">
          <div class="has-text-weight-bold" slot="header">沸点</div>
          <div class="content">
          <span class="icon has-text-info">
            <i class="fa fa-fax"></i>
          </span>
            如何玩转沸点
          </div>
          <div class="content">
          <span class="icon has-text-info">
            <i class="fa fa-heart"></i>
          </span>
            关于TGIF[thank God it's Friday]
          </div>
        </el-card>
      </div>
    </div>
  </section>
</template>

<script>
// import {getList} from "@/api/sparetime";

export default {
  name: "pins",
  data() {
    return {
      query: {
        page: 1,
        size: 10,
        type: {
          String,
          default: 'random'
        }
      },
      list: 4
    }
  },
  created() {
    this.fetchList();
  },
  methods: {
    fetchList() {
      getList(this.query.type, this.query).then(() => {
        this.list = 1;
      });
    },

    // 板块
    handleList(type) {
      getList(type, this.query).then(() => {
        this.list = 1;
      });
    }
  }
}
</script>

<style scoped>

</style>