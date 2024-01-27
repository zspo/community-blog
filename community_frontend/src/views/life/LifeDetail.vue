<template>
    <main role="main-inner-wrapper" class="container">
        <!-- work details -->
        <div class="work-details">
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-4">
                    <header role="work-title">
                        <h2>{{ title }}</h2>
                        <h6><span>{{ topic_c_time }}</span></h6>
                    </header>
                </div>
                <div class="col-xs-12 col-sm-12"> 
                     <!-- col-md-8 -->
                    <section>
                        <div v-for="(item, index) in topic_p_list" :key="index">
                            <p>{{ item }}</p>
                        </div>
                    </section>
                </div>
            </div>
            <div class="clearfix"></div>
            <div class="work-images grid">
                <ul class="effect-2">
                    <li v-for="(item, index) in topic_img_list" :key="index" class="animate" >
                        <img :src="item" alt="" class="img-responsive"/>
                    </li>
                </ul>
            </div>
            <div v-if="token && user.id === topic_user.id" class="level-right">
          <!-- <div class="level-right"> -->
                <!-- <router-link
                class="level-item"
                :to="{name:'topic-edit',params: {id:topicId}}"
                >
                <span class="el-icon-edit"> 编辑 </span>
                </router-link> -->
                <a class="level-item">
                <span
                    class="el-icon-delete"
                    @click="handleDelete(topicId)"
                > 删除 </span>
                </a>
          </div>
        </div>
    </main>
   
</template>


<script>
import { deleteTopic } from '@/api/post'
import { getLifeDetail } from '@/api/post'
import { mapGetters } from 'vuex'

export default {
    name: 'LifeDetail',
    computed: {
        ...mapGetters([
        'token','user'
        ])
    },
    data() {
        return {
            // topic: {
            //     title: '杭州-湘湖-春',
            //     createTime: '三月 2021',
            //     content: ['四月的某个周末，','这个感觉','测试第三行'],
            //     images: ['http://img.winterchen.com/IMG_078477.jpg','http://img.winterchen.com/IMG_078477.jpg'],
            //     id: this.$route.params.id
            // },
            title: "",
            topic_c_time: "",
            topic_p_list: [],
            topic_img_list: [],
            topic_user: {},
            topicId: this.$route.params.id,
        }
    },
    created() {
        this.fetchTopic()
    },
    methods: {
        // 初始化
        async fetchTopic() {
            getLifeDetail(this.$route.params.id).then(response => {
                const { data } = response
                document.title = data.title

                this.title = data.title
                this.topic_c_time = data.topic_c_time
                this.topic_p_list = data.topic_p_list
                this.topic_img_list = data.topic_img_list
                this.topic_user = data.user
            })
        },
        handleDelete(id) {
            deleteTopic(id).then(value => {
                const { code, message } = value
                alert(message)

                if (code === 200) {
                setTimeout(() => {
                    this.$router.push({ path: '/' })
                }, 500)
                }
            })
        },
    }
}
</script>

<style scoped>

/* @import '/static/css/bootstrap.min.css'; */
@import '/static/css/component.css';
@import '/static/css/font-awesome.4.6.0.css';
@import '/static/css/normalize.css';
@import '/static/css/responsive.css';
@import '/static/css/set2.css';
@import '/static/css/style.css';
/* @import '/static/css/style.min.css'; */
/* @import "https://cdn.bootcss.com/highlight.js/9.9.0/styles/xcode.min.css"; */

body {
font-family: 'Open Sans', sans-serif;
font-weight: 400;
font-size: 13px;
line-height: 1.42857143;
color: #353535;
background-color: #f6f6f6;
}
/* body {
font-family: -apple-system,SF UI Text,Arial,PingFang SC,Hiragino Sans GB,Microsoft YaHei,WenQuanYi Micro Hei,sans-serif;
font-size: 14px;
line-height: 1.8;
margin: 0 auto;
color: #313131;
text-rendering: geometricPrecision;
-webkit-font-smoothing: antialiased;
-moz-osx-font-smoothing: grayscale;
} */
/* html {
font-family: sans-serif;
-ms-text-size-adjust: 100%;
-webkit-text-size-adjust: 100%;
font-size: 10px;
overflow-x: hidden;
} */
main[role="main-home-wrapper"], main[role="main-inner-wrapper"] {
padding-bottom: 50px;
}
/* .container {
width: 1170px;
} */
.container {
padding-right: 15px;
padding-left: 15px;
margin-right: auto;
margin-left: auto;
}
article, aside, details, figcaption, figure, footer, header, hgroup, main, nav, section, summary {
display: block;
}
html, body, div, span, object, iframe, h1, h2, h3, h4, h5, h6, p, blockquote, pre, abbr, address, cite, code, del, dfn, em, img, ins, kbd, q, samp, small, strong, sub, sup, var, b, i, dl, dt, dd, ol, ul, li, fieldset, form, label, legend, table, caption, tbody, tfoot, thead, tr, th, td, article, aside, canvas, details, figcaption, figure, footer, header, hgroup, menu, nav, section, summary, time, mark, audio, video, input, main {
-webkit-box-sizing: border-box;
-moz-box-sizing: border-box;
box-sizing: border-box;
margin: 0;
padding: 0;
vertical-align: baseline;
border: 0;
outline: 0;
}
* {
-webkit-box-sizing: border-box;
-moz-box-sizing: border-box;
box-sizing: border-box;
margin: 0;
padding: 0;
border: 0;
-webkit-font-smoothing: antialiased;
-moz-osx-font-smoothing: grayscale;
}
:after, :before {
-webkit-box-sizing: border-box;
-moz-box-sizing: border-box;
box-sizing: border-box;
}
*, :after, :before {
-webkit-box-sizing: border-box;
-moz-box-sizing: border-box;
box-sizing: border-box;
}
*, *:before, *:after {
-webkit-box-sizing: border-box;
-moz-box-sizing: border-box;
box-sizing: border-box;
}
.work-details {
padding: 0 69px;
margin-top: 50px;
}
.row {
margin-right: -15px;
margin-left: -15px;
}
.btn-group-vertical>.btn-group:after, .btn-group-vertical>.btn-group:before, .btn-toolbar:after, .btn-toolbar:before, .clearfix:after, .clearfix:before, .container-fluid:after, .container-fluid:before, .container:after, .container:before, .dl-horizontal dd:after, .dl-horizontal dd:before, .form-horizontal .form-group:after, .form-horizontal .form-group:before, .modal-footer:after, .modal-footer:before, .modal-header:after, .modal-header:before, .nav:after, .nav:before, .navbar-collapse:after, .navbar-collapse:before, .navbar-header:after, .navbar-header:before, .navbar:after, .navbar:before, .pager:after, .pager:before, .panel-body:after, .panel-body:before, .row:after, .row:before {
display: table;
content: " ";
}
.btn-group-vertical>.btn-group:after, .btn-toolbar:after, .clearfix:after, .container-fluid:after, .container:after, .dl-horizontal dd:after, .form-horizontal .form-group:after, .modal-footer:after, .modal-header:after, .nav:after, .navbar-collapse:after, .navbar-header:after, .navbar:after, .pager:after, .panel-body:after, .row:after {
clear: both;
}
.col-md-4 {
width: 33.33333333%;
}
.col-md-1, .col-md-10, .col-md-11, .col-md-12, .col-md-2, .col-md-3, .col-md-4, .col-md-5, .col-md-6, .col-md-7, .col-md-8, .col-md-9 {
float: left;
}
.col-sm-12 {
width: 100%;
}
.col-sm-1, .col-sm-10, .col-sm-11, .col-sm-12, .col-sm-2, .col-sm-3, .col-sm-4, .col-sm-5, .col-sm-6, .col-sm-7, .col-sm-8, .col-sm-9 {
float: left;
}
.col-xs-12 {
width: 100%;
}
.col-xs-1, .col-xs-10, .col-xs-11, .col-xs-12, .col-xs-2, .col-xs-3, .col-xs-4, .col-xs-5, .col-xs-6, .col-xs-7, .col-xs-8, .col-xs-9 {
float: left;
}
.col-lg-1, .col-lg-10, .col-lg-11, .col-lg-12, .col-lg-2, .col-lg-3, .col-lg-4, .col-lg-5, .col-lg-6, .col-lg-7, .col-lg-8, .col-lg-9, .col-md-1, .col-md-10, .col-md-11, .col-md-12, .col-md-2, .col-md-3, .col-md-4, .col-md-5, .col-md-6, .col-md-7, .col-md-8, .col-md-9, .col-sm-1, .col-sm-10, .col-sm-11, .col-sm-12, .col-sm-2, .col-sm-3, .col-sm-4, .col-sm-5, .col-sm-6, .col-sm-7, .col-sm-8, .col-sm-9, .col-xs-1, .col-xs-10, .col-xs-11, .col-xs-12, .col-xs-2, .col-xs-3, .col-xs-4, .col-xs-5, .col-xs-6, .col-xs-7, .col-xs-8, .col-xs-9 {
position: relative;
min-height: 1px;
padding-right: 15px;
padding-left: 15px;
}
.col-md-8 {
width: 66.66666667%;
}
.work-details header h2 {
color: #343434;
font-size: 36px;
font-weight: 800;
}
.h1, .h2, .h3, h1, h2, h3 {
margin-top: 20px;
margin-bottom: 10px;
}
.h1, .h2, .h3, .h4, .h5, .h6, h1, h2, h3, h4, h5, h6 {
line-height: 1.1;
color: inherit;
}
h1, h2, h3, h4, h5, h6 {
font-weight: 400;
-webkit-font-smoothing: antialiased;
}
h2 {
display: block;
font-size: 1.5em;
margin-block-start: 0.83em;
margin-block-end: 0.83em;
margin-inline-start: 0px;
margin-inline-end: 0px;
font-weight: bold;
}
.h6, h6 {
font-size: 12px;
}
.h4, .h5, .h6, h4, h5, h6 {
margin-top: 10px;
margin-bottom: 10px;
}
.h1, .h2, .h3, .h4, .h5, .h6, h1, h2, h3, h4, h5, h6 {
line-height: 1.1;
color: inherit;
}
h6 {
font-size: 1em;
color: #555;
}
h1, h2, h3, h4, h5, h6 {
font-weight: 400;
-webkit-font-smoothing: antialiased;
}
::selection {
color: #fff;
background-color: #6fa3ef;
}
::selection {
background-color: #fb5353;
color: #fff;
text-shadow: none;
}
.work-details p {
font-size: 16px;
color: #666666;
line-height: 30px;
margin-bottom: 20px;
font-weight: 300;
}
p {
margin: 0 0 10px;
}
blockquote, dl, ol, p, pre, table, ul {
margin: 20px 0;
}
.clearfix {
zoom: 1;
}
.grid {
position: relative;
list-style: none;
overflow: hidden;
text-align: center;
}
.work-images {
margin-top: 46px;
}
ol, ul {
margin-top: 0;
margin-bottom: 10px;
}
.grid-lod.effect-2 li.animate {
-webkit-transform: translateY(200px);
transform: translateY(200px);
-webkit-animation: moveUp 0.65s ease forwards;
animation: moveUp 0.65s ease forwards;
}
.animate {
-webkit-transform: translateY(200px);
transform: translateY(200px);
-webkit-animation: moveUp 0.65s ease forwards;
animation: moveUp 0.65s ease forwards;
}
.grid-lod li {
display: block;
opacity: 0;
}
.work-images li {
margin-bottom: 50px;
}
li {
word-wrap: break-word;
font-size: 16px;
}
.grid-lod li a, .grid-lod li img {
outline: none;
border: none;
display: block;
max-width: 100%;
}
ol li ol:first-of-type, ol li ul:first-of-type, ol li>:first-child, ul li ol:first-of-type, ul li ul:first-of-type, ul li>:first-child {
margin-top: 0;
}
.carousel-inner>.item>a>img, .carousel-inner>.item>img, .img-responsive, .thumbnail a>img, .thumbnail>img {
display: block;
max-width: 100%;
height: auto;
}
img {
border: 0;
vertical-align: middle;
border: 0;
}
main[role="main-inner-wrapper"] .thumbnails-pan section figure, main[role="main-inner-wrapper"] .thumbnails-pan section figure, img, main[role="main-inner-wrapper"] .thumbnails-pan section figure figcaption, section.blog-content figure, section.blog-content figure img, section.blog-content article {
transition-delay: 0s;
transition-duration: 0.6s;
transition-property: all;
transition-timing-function: ease;
}
li {
word-wrap: break-word;
font-size: 16px;
}
li {
display: list-item;
text-align: -webkit-match-parent;
}
ul, menu, dir {
display: block;
list-style-type: disc;
margin-block-start: 1em;
margin-block-end: 1em;
margin-inline-start: 0px;
margin-inline-end: 0px;
padding-inline-start: 0px;
}
.header_color {
    background: #f6f6f6;
    box-shadow: 0;
}



</style>
