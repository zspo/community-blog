<template>
    <div>
    <div v-for="(item, index) in articleList" :key="index" class="content-box">
        <div class="article-box">
            <div class="ellipsis is-ellipsis-1">
                <router-link :to="{name:'post-detail',params:{id:item.id}}">
                <span class="title-style">{{ item.title }}</span>
                </router-link>
            </div>
            <div class=" has-text-grey is-mobile  is-size-7 mt-2 mb-3">
                <div class="level-left">
                    <span class="mr-3 el-icon-date">
                    {{ dayjs(item.createTime).format("YYYY-MM-DD") }}
                    </span>
                    <span class="el-icon-present">
                        <router-link :to="{ name: 'category', params: { name: item.categoryName } }">
                        {{ item.categoryName}}
                        </router-link>
                        /
                        <router-link :to="{ name: 'subcategory', params: { name: item.subcategoryName } }">
                        {{ item.subcategoryName}}
                        </router-link>
                    </span>
                </div>
            </div>

            <div class="article-contract">
                文章缩略摘要
            </div>
            
            <div class=" has-text-grey is-mobile  is-size-7 my-2">
                <div class="level-left">
                    <!-- <router-link class="level-item" :to="{ path: `/member/${item.username}/home` }">
                    {{ item.alias }}
                    </router-link> -->

                    <span
                    v-for="(tag, index) in item.tags"
                    :key="index"
                    class="tag is-hidden-mobile is-success is-light mr-1"
                    >
                    <router-link :to="{ name: 'tag', params: { name: tag.name } }">
                        {{ "#" + tag.name }}
                    </router-link>
                    </span>

                    <span class="mr-2 el-icon-view">{{ item.view }}</span>
                    <span class="mr-2 el-icon-thumb">{{ item.like }}</span>
                    <span class="mr-2 el-icon-chat-square">{{ item.comments }}</span>
                     <!-- v-if="item.categoryName != '生活掠影'" -->
                </div>
            </div>
        </div>
        <div class="cover-box" v-if="item.categoryName == '生活掠影' && item.cover">
            <!-- <figure class="image"> -->
            <!-- <img :src="`http://img.deepcode.ltd/IMG_0655.jpg`" style="border-radius: 20px;"> -->
            <el-image :src="item.cover" style="border-radius: 5px;"></el-image>
            <!-- </figure> -->
        </div>
    </div>
    </div>
</template>

<script>
export default {
    name: 'PostList',
    props: {
        articleList: {
            type: Array,
            default: []
        }
    },
}
</script>

<style scoped>
.cover-box {
    flex: 0 0 auto;
    width: 13rem;
    height: 10rem;
    margin-left: 2rem;
    background-color: #fff;
    border-radius: 2px;
    position: relative;
    -o-object-fit: cover;
    object-fit: cover;
    overflow: hidden;
}
</style>>