<template>
  <header id="header" class="header">
    <b-navbar class="head-container" :fixed-top="true">
      <template slot="brand" class="navbar-container">
        <a href="/" class="logo"><img :src="logoUrl" alt="AI学习空间" class="logo-img"></a>
        <b-navbar-item class="is-hidden-desktop" href="/" > 首页 </b-navbar-item>
        <!-- <b-navbar-item class="is-hidden-desktop">
          <el-dropdown>
            <b-navbar-item class="el-dropdown-link">发现<i class="el-icon-arrow-down el-icon--right"></i></b-navbar-item>
            <el-dropdown-menu slot="dropdown">
              <b-navbar-item tag="router-link" :to="{ path: '/category' }" > 分类 </b-navbar-item>
              <b-navbar-item tag="router-link" :to="{ path: '/tag' }"> 标签 </b-navbar-item>
            </el-dropdown-menu>
          </el-dropdown>
        </b-navbar-item> -->
        <b-navbar-item class="is-hidden-desktop" tag="router-link" :to="{ path: '/pins' }" > 沸点 </b-navbar-item>
        <b-navbar-item class="is-hidden-desktop" tag="router-link" :to="{ path: '/life' }" > 光影 </b-navbar-item>
        
      </template>

      <template slot="start">
        <ul class="navs">
          <b-navbar-item href="/" > 首页 </b-navbar-item>
          <!-- <div>
            <el-dropdown>
              <b-navbar-item class="el-dropdown-link">发现<i class="el-icon-arrow-down el-icon--right"></i></b-navbar-item>
              <el-dropdown-menu slot="dropdown">
                <b-navbar-item tag="router-link" :to="{ path: '/category' }" > 分类 </b-navbar-item>
                <b-navbar-item tag="router-link" :to="{ path: '/tag' }"> 标签 </b-navbar-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div> -->
          <b-navbar-item tag="router-link" :to="{ path: '/pins' }" > 沸点 </b-navbar-item>
          <b-navbar-item tag="router-link" :to="{ path: '/life' }" > 光影 </b-navbar-item>
        </ul>
      </template>

      <template slot="end">
        <b-navbar-item tag="div">
          <b-field position="is-centered">
            <b-input
              v-model="searchKey"
              class="s_input"
              width="80%"
              placeholder="搜索"
              rounded
              clearable
              @keyup.enter.native="search()"
            />

            <p class="control">
              <b-button
                class="is-info"
                @click="search()"
              ><img src="../../assets/image/images/search-icon.svg" class="search-icon" />
              </b-button>
            </p>
          </b-field>
        </b-navbar-item>

        <b-navbar-item tag="div">
          <b-switch
            v-model="darkMode"
            passive-type="is-warning"
            type="is-dark"
          >
            <!-- {{ darkMode ? "夜" : "昼" }} -->
          </b-switch>
        </b-navbar-item>

        <b-navbar-item
          v-if="token == null || token === ''"
          tag="div"
        >
          <div class="buttons">
            <b-button
              class="is-light"
              tag="router-link"
              :to="{ path: '/register' }"
            >
              注册
            </b-button>
            <b-button
              class="is-light"
              tag="router-link"
              :to="{ path: '/login' }"
            >
              登录
            </b-button>
          </div>
        </b-navbar-item>

        
        <b-navbar-dropdown v-else :label="user.alias">
          <b-navbar-item
            class="el-icon-user"
            tag="router-link"
            :to="{ path: `/member/${user.username}/home` }"
          >
            个人中心
          </b-navbar-item>
          <hr class="dropdown-divider">
          <b-navbar-item
            class="el-icon-setting"
            tag="router-link"
            :to="{ path: `/member/${user.username}/setting` }"
          >
            设置中心
          </b-navbar-item>
          <hr class="dropdown-divider">
          <b-navbar-item
            class="el-icon-switch-button"
            tag="a"
            @click="logout"
          > 退出登录
          </b-navbar-item>
        </b-navbar-dropdown>
      </template>
    </b-navbar>
  </header>
</template>

<script>
import { disable as disableDarkMode, enable as enableDarkMode } from 'darkreader'
import { getDarkMode, setDarkMode } from '@/utils/auth'
import { mapGetters } from 'vuex'

export default {
  name: 'Header',
  data() {
    return {
      logoUrl: require('@/assets/image/logo.png'),
      searchKey: '',
      darkMode: false
    }
  },
  computed: {
    ...mapGetters(['token', 'user'])
  },
  watch: {
    // 监听Theme模式
    darkMode(val) {
      if (val) {
        enableDarkMode({})
      } else {
        disableDarkMode()
      }
      setDarkMode(this.darkMode)
    }
  },
  created() {
    // 获取cookie中的夜间还是白天模式
    this.darkMode = getDarkMode()
    if (this.darkMode) {
      enableDarkMode({})
    } else {
      disableDarkMode()
    }
  },
  methods: {
    async logout() {
      this.$store.dispatch('user/logout').then(() => {
        this.$message.info('退出登录成功')
        setTimeout(() => {
          this.$router.push({ path: this.redirect || '/' })
        }, 500)
      })
    },
    search() {
      console.log(this.token)
      if (this.searchKey.trim() === null || this.searchKey.trim() === '') {
        this.$message.info({
          showClose: true,
          message: '请输入关键字搜索！',
          type: 'warning'
        })
        return false
      }
      this.$router.push({ path: '/search?key=' + this.searchKey })
    }
  }
}
</script>


<style scoped>
input {
  width: 80%;
  height: 86%;
}

.navs{
  display: flex;
  line-height: 60px;
  font-size: 16px;
  color: #71777c;
}
.head-container {
  max-width: 1344px;
  margin: auto;
}
.logo {
  margin-right: 1rem;
}
.logo-img {
  width: auto;
  height: auto;
}
.search-icon{
  padding-top: 5px;
  cursor: pointer;
}
.el-dropdown {
    cursor: pointer;
    font-size: 16px;
    line-height: 1.5;
    margin: 0;
}
.el-icon--right {
  margin-left: 0px;
}
</style>
