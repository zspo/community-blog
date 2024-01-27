import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: () => import("@/views/Home"),
  },
  {
    path: "/register",
    name: "register",
    component: () => import("@/views/auth/Register"),
    meta: { title: "注册" },
  },
  // 登录
  {
    name: "login",
    path: "/login",
    component: () => import("@/views/auth/Login"),
    meta: { title: "登录" },
  },
  // 发布
  {
    name: "post-create",
    path: "/post/create",
    component: () => import("@/views/post/Create"),
    meta: { title: "发布", requireAuth: true },
  },
  // 编辑
  {
    name: 'topic-edit',
    path: '/topic/edit/:id',
    component: () => import('@/views/post/Edit'),
    meta: {
      title: '编辑',
      requireAuth: true
    }
  },
  // 详情
  {
    name: "post-detail",
    path: "/post/:id",
    component: () => import("@/views/post/Detail"),
    meta: { title: "详情" },
  },
  {
    name: 'tag',
    path: '/tag/:name',
    component: () => import('@/views/tag/Tag'),
    meta: { title: '标签' }
  },
  {
    name: 'tag-all',
    path: '/tag',
    component: () => import('@/views/tag/Tag_all'),
    meta: { title: '标签' }
  },
  {
    name: 'category-all',
    path: '/category',
    component: () => import('@/views/cate/Category_all'),
    meta: { title: '分类' }
  },
  {
    name: 'category',
    path: '/category/:name',
    component: () => import('@/views/cate/Category'),
    meta: { title: '一级分类' }
  },
  {
    name: 'subcategory',
    path: '/category/subcategory/:name',
    component: () => import('@/views/cate/SubCategory'),
    meta: { title: '二级分类' }
  },
  // 检索
  {
    name: 'search',
    path: '/search',
    component: () => import('@/views/Search'),
    meta: { title: '搜索' }
  },
  // 用户主页
  {
    name: 'user',
    path: '/member/:username/home',
    component: () => import('@/views/user/Profile'),
    meta: { title: '用户主页' }
  },
  // 用户设置
  {
    name: 'user-setting',
    path: '/member/:username/setting',
    component: () => import('@/views/user/Setting'),
    meta: { title: '设置', requireAuth: true }
  },
  {
    name: 'life',
    path: '/life',
    component: () => import('@/views/life/Life'),
    meta: { title: '光影' }
  },
  {
    name: "life-detail",
    path: "/life/:id",
    component: () => import("@/views/life/LifeDetail"),
  },
  {
    name: "pins",
    path: "/pins",
    component: () => import("@/views/pins/Pins"),
    meta: { title: '沸点' }
  },
  {
    path: "/404",
    name: "404",
    component: () => import("@/views/error/404"),
    meta: { title: "404-NotFound" },
  },
  {
    path: "*",
    redirect: "/404",
    hidden: true,
  },
];

const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch((err) => err);
};

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
