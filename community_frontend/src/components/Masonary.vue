<template>
  <div id="app" class="container">
    <!-- <el-card class="box-card" shadow="never">
      <div class="upload-div">
          <el-upload
            class="upload-demo"
            action="https://jsonplaceholder.typicode.com/posts/"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :before-remove="beforeRemove"
            multiple
            :limit="3"
            :on-exceed="handleExceed"
            :file-list="fileList">
            <el-button size="small" type="primary">点击上传</el-button>
          </el-upload>
      </div>
    </el-card> -->
    <div v-masonry
         transition-duration=".3s"
         origin-top="true"
         origin-left="false"
         item-selector=".item"
         gutter="10"
         horizontalOrder="true"
         :horizontal-order="true">
      <!-- <div class="row"> -->
        <div v-masonry-tile class="item"
             :style="{ maxWidth: imageSize + '%' }"
             v-for="(post, index) in posts" v-bind:key="index">
          <!-- <div class="card" > -->
            <img class="card-img-top" :src="post.img_url" alt="Card image cap">
            <!-- <div class="card-body"> -->
              <!-- <h5 class="card-title"><strong>{{ post.title }}</strong></h5> -->
              <!-- <p class="card-text">{{ post.content }}</p> -->
            <!-- </div> -->
          <!-- </div> -->
        </div>
      <!-- </div> -->
    </div>
  </div>
</template>

<script>
import VueSlideBar from "vue-slide-bar"

import { getRandomPhoto } from '@/api/photo'

export default {
    name: 'masonry',
    components: {
        VueSlideBar
    },
    data() {
        return {
            posts: [],
            imageSize: 24
        }
    },
    created() {
        window.addEventListener('scroll', this.handleScroll);
        this.getPosts()
    },
    methods: {
        getPosts() {
            getRandomPhoto(this.imageSize).then(response => {
                    // console.log(Date.now() + ' Fetched: ', response.data['image_names'].length);
                    for (const photo_details of response.data) {
                        this.posts.push({
                            "img_url": photo_details.imgUrl
                        })
                    }
                }).catch(e=> {
                    alert(e)
                })
        },
        handleScroll() {
            let scrollHeight = window.scrollY;
            let maxHeight = window.document.body.scrollHeight - window.document.documentElement.clientHeight;
            // console.log('scrollHeight: ', scrollHeight, 'maxHeight: ', maxHeight);
            if (scrollHeight >= maxHeight - 300) {
                this.getPosts()
            }
        },
        smartTrim(string, maxLength) {
            let trimmedString = string.substr(0, maxLength);
            return trimmedString.substr(0, Math.min(trimmedString.length, trimmedString.lastIndexOf(" ")))
        },
        reDraw: function(){
            this.$redrawVueMasonry();
        },
        dragEnd() {
            // console.log('Slider set image size; ' + this.imageSize);
            this.reDraw();
        },
        randomStr(len) {
            return btoa('' + Math.random()).slice(0, len);
        }
    }
}
</script>

<style scoped>
.box-card {
  border: 1px solid #EBEEF5 !important;
  border-radius: 20px;
}
.upload-div {
  display: flex;
}
.item {
  margin-top: 0.2rem;
  margin-bottom: 0rem;
  /* border-radius: 15px; */
}
.card {
  margin-left: 10px;
  margin-bottom: 10px;
  border-radius: 20px;
}
.card-img-top {
  /* margin-left: 10px; */
  margin-bottom: 10px;
  border-radius: 20px;
}
</style>
