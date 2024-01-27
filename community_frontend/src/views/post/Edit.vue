<template>
  <section>
    <div class="columns">
      <div class="column is-full">
        <el-card class="box-card" shadow="never">
          <div slot="header" class="clearfix">
            <span><i class="el-icon-refresh"> 更新文章 </i></span>
          </div>
          <div>
            <el-form :model="topic" ref="topic" class="demo-topic">

              <el-form-item prop="title">
                <el-input
                  v-model="topic.title"
                  maxlength="25"
                  show-word-limit
                  placeholder="请输入文章标题"
                />
              </el-form-item>

              <!--Category-->
              <el-form-item prop="category">
                <el-select v-model="category_name" placeholder="请选择">
                </el-select>

                <el-select
                  v-model="subcategory_name"
                  style="margin-left: 20px;"
                  placeholder="请选择">
                </el-select>
              </el-form-item>

              <!--Markdown-->
              <el-form-item prop="content">
                <div id="vditor" />
              </el-form-item>

              <!-- <b-taginput
                v-model="tags"
                class="my-3"
                maxlength="15"
                maxtags="3"
                ellipsis
                placeholder="请输入主题标签，限制为 15 个字符和 3 个标签"
              /> -->

              <el-form-item prop="tags">
                <el-select style="width:100%"
                  v-model="tags"
                  multiple
                  filterable
                  allow-create
                  default-first-option
                  multiple-limit="5"
                  placeholder="请输入或选择文章标签，限制为 15 个字符和 3 个标签">
                  <el-option
                    v-for="item in tagList"
                    :key="item.tagName"
                    :label="item.tagName"
                    :value="item.tagName">
                  </el-option>
                </el-select>
              </el-form-item>

              <el-form-item class="mt-3">
                <el-button type="primary" @click="handleUpdate()"
                  >更新
                </el-button>
                <el-button @click="resetForm('topic')">重置</el-button>
              </el-form-item>
            </el-form>
          </div>
        </el-card>
      </div>
    </div>
  </section>
</template>

<script>
import { getTopic, update } from "@/api/post";
import { getAllTagsList } from '@/api/tag'
import Vditor from "vditor";
import "vditor/dist/index.css";

export default {
  name: "TopicEdit",
  components: {},
  data() {
    return {
      topic: {},
      category_name: '',
      subcategory_name: '',
      tags: [],
      tagList: []
    };
  },
  created() {
    this.fetchTopic()
    this.fetchTagList()
  },
  methods: {
    renderMarkdown(md) {
      this.contentEditor = new Vditor("vditor", {
        height: 460,
        placeholder: "输入要更新的内容",
        preview: {
          hljs: { style: "monokai" },
          markdown: { toc: true,
                      paragraphBeginningSpace: true,
                  },
        },
        
        mode: "sv",
        after: () => {
          this.contentEditor.setValue(md);
        },
        // upload: {
        //   accept: 'image/*',
        //   url: '/post/api/upload_file',
        //   filename (name) {
        //     return name.replace(/[^(a-zA-Z0-9\u4e00-\u9fa5\.)]/g, '').
        //       replace(/[\?\\/:|<>\*\[\]\(\)\$%\{\}@~]/g, '').
        //       replace('/\\s/g', '')
        //   },
        // },
      });
    },
    fetchTagList() {
      getAllTagsList().then((response) => {
        const { data } = response
        this.tagList = data
      })
    },
    fetchTopic() {
      getTopic(this.$route.params.id).then((value) => {
        this.topic = value.data.topic;
        this.category_name = value.data.category_name;
        this.subcategory_name = value.data.subcategory_name;
        this.tags = value.data.tags.map(tag => tag.name);
        this.renderMarkdown(this.topic.content);
      });
    },
    handleUpdate: function () {
      this.topic.content = this.contentEditor.getValue();
      update(this.topic).then((response) => {
        const { data } = response;
        console.log(data);
        setTimeout(() => {
          this.$router.push({
            name: "post-detail",
            params: { id: data.id },
          });
        }, 800);
      });
    },

    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.contentEditor.setValue("");
      // this.tags = "";
    },
  },
};
</script>

<style>
.vditor-reset pre > code {
  font-size: 100%;
}
</style>
