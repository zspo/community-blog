<template>
  <el-card class="box-card" shadow="never">
    <el-tree
    class="filter-tree"
    :data="cateList"
    :props="defaultProps"
    default-expand-all
    ref="tree">
    </el-tree>
  </el-card>
  <!-- <div data-v-563bf316="" data-v-7ba5bd90="" class="el-card box-card is-never-shadow">
    <div class="el-card__body">
      <div data-v-563bf316="" role="tree" class="el-tree filter-tree">
        <div role="treeitem" tabindex="-1" aria-expanded="true" aria-disabled="" draggable="false" class="el-tree-node is-expanded is-focusable">
          <div class="el-tree-node__content" style="padding-left: 0px;">
            <span class="expanded el-tree-node__expand-icon el-icon-caret-right"> </span>
          </div>
          <div role="group" aria-expanded="true" class="el-tree-node__children">
            <div role="treeitem" tabindex="-1" aria-expanded="true" aria-disabled="" draggable="false" class="el-tree-node is-expanded is-focusable">
              <div class="el-tree-node__content" style="padding-left: 18px;">
                <span class="is-leaf el-tree-node__expand-icon el-icon-caret-right"></span>
                <span class="el-tree-node__label">看一看（0）</span>
              </div>
              <div role="group" aria-expanded="true" class="el-tree-node__children"></div>
            </div>
          </div>
        </div>
       <div class="el-tree__drop-indicator" style="display: none;"></div>
      </div>
    </div>
  </div> -->
</template>

<script>

import { getAllCateInfoList } from '@/api/category'

export default {
  name: 'Category_all',
  data() {
    return {
      cateList: [],
      defaultProps: {
        children: 'subCateList',
        label: 'name'
      },
      // test_cate: [
      //   {'cate_id':'',
      //   'cate_name':'',
      //   'subcatelist': [
      //     {'subcate_id':'',
      //     'subcate_name':'',
      //     'articallist':[{'artical_id':'','artical_title':''}]
      //     }
      //     ]
      //   }
      // ]
      }
  },
  created() {
    this.fetchList()
  },
  methods: {
    fetchList() {
      getAllCateInfoList().then((response) => {
        const { data } = response
        this.cateList = data
      })
    }
  }
}
</script>



<style scoped>
</style>
