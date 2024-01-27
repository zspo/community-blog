<template>
  <div class="columns">
    <div class="column is-full">
      <el-card
        class="box-card"
        shadow="never"
      >
        <div
          slot="header"
          class="clearfix"
        >
          <span><i class="el-icon-edit-outline"> 发布文章 </i></span>
        </div>
        <div>
          <el-form
            ref="ruleForm"
            :model="ruleForm"
            :rules="rules"
            class="demo-ruleForm"
          >
            <el-form-item prop="title">
              <el-input
                v-model="ruleForm.title"
                maxlength="25"
                show-word-limit
                placeholder="请输入文章标题"
              />
            </el-form-item>

            <!--Category-->
            <el-form-item prop="category">
              <el-select v-model="ruleForm.categoryId" placeholder="请选择一级分类">
                <el-option
                  v-for="cate in cateList"
                  :key="cate.id"
                  :label="cate.name"
                  :value="cate.id">
                </el-option>
              </el-select>

              <el-select
                v-model="ruleForm.subcategoryId"
                style="margin-left: 20px;"
                placeholder="请选择二级分类">
                <el-option
                  v-for="item in subcateList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>

            <!--Markdown-->
            <el-form-item prop="content">
            <div id="vditor" />
            </el-form-item>

            <el-form-item prop="tags">
              <el-select style="width:100%"
                v-model="ruleForm.tags"
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

            <el-form-item>
              <el-button type="primary" @click="submitForm('ruleForm')">
                立即创建
              </el-button>
              <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
import { post } from '@/api/post'
import { getAllCateInfoList, getSubCateInfoByCateId} from '@/api/category'
import { getAllTagsList } from '@/api/tag'

import Vditor from 'vditor'
import 'vditor/dist/index.css'

export default {
  name: 'TopicPost',

  data() {
    return {
      contentEditor: {},
      cateList: [],
      subcateList: [],
      tagList: [],
      ruleForm: {
        title: '', // 标题
        tags: [], // 标签
        categoryId: '',
        subcategoryId: '',
        content: '' // 内容
      },
      rules: {
        title: [
          { required: true, message: '请输入文章标题', trigger: 'blur' },
          {
            min: 1,
            max: 25,
            message: '长度在 1 到 25 个字符',
            trigger: 'blur'
          }
        ]
      },
    }
  },
  mounted() {
    this.contentEditor = new Vditor('vditor', {
      height: 500,
      placeholder: '此处为话题内容……',
      theme: 'classic',
      counter: {
        enable: true,
        type: 'markdown'
      },
      preview: {
        delay: 0,
        hljs: {
          style: 'monokai',
          lineNumber: true
        }
      },
      tab: '\t',
      typewriterMode: true,
      toolbarConfig: {
        pin: true
      },
      cache: {
        enable: false
      },
      mode: 'sv',
    })
  },
  created() {
    this.fetchCateList()
    this.fetchTagList()
  },
  watch: {
    'ruleForm.categoryId': {
      handler(newName, oldName) {
        this.getSubCateInfo(newName)
      },
      deep: true,
      // immediate: true
    }
  },
  methods: {
    fetchCateList() {
      getAllCateInfoList().then((response) => {
        const { data } = response
        this.cateList = data
      })
    },
    fetchTagList() {
      getAllTagsList().then((response) => {
        const { data } = response
        this.tagList = data
      })
    },
    getSubCateInfo(id) {
      getSubCateInfoByCateId(id).then((response) => {
        const { data } = response
        this.subcateList = data
      })
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (
            this.contentEditor.getValue().length === 1 ||
            this.contentEditor.getValue() == null ||
            this.contentEditor.getValue() === ''
          ) {
            alert('话题内容不可为空')
            return false
          }
          if (this.ruleForm.tags == null || this.ruleForm.tags.length === 0) {
            alert('标签不可以为空')
            return false
          }
          if (this.ruleForm.categoryId == null || this.ruleForm.subcategoryId == null) {
            alert('分类不可以为空')
            return false
          }
          this.ruleForm.content = this.contentEditor.getValue()
          post(this.ruleForm).then((response) => {
            const { data } = response
            // if (this.ruleForm.categoryId != '103') {
            //   setTimeout(() => {
            //     this.$router.push({
            //       name: 'post-detail',
            //       params: { id: data.id }
            //     })
            //   }, 800)
            // } else {
            //   setTimeout(() => {
            //     this.$router.push({
            //       name: 'life-detail',
            //       params: { id: data.id }
            //     })
            //   }, 800)
            // }
            setTimeout(() => {
                this.$router.push({
                  name: 'post-detail',
                  params: { id: data.id }
                })
              }, 800)
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
      this.contentEditor.setValue('')
      this.ruleForm.tags = ''
      this.ruleForm.categoryId = ''
      this.ruleForm.subcategoryId = ''
    }
  }
}
</script>

<style>
</style>
