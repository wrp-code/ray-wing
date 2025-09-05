import { createRouter, createWebHistory } from 'vue-router'

// 1. 定义路由组件（懒加载推荐）
const Home = () => import('@/views/Home.vue')
const ArticleDetail = () => import('@/views/ArticleDetail.vue')
const ArticleList = () => import('@/views/ArticleList.vue')
const Projects = () => import('@/views/Projects.vue')
const Drawing = () => import('@/views/Drawing.vue')
const AboutMe = () => import('@/views/AboutMe.vue')

// 2. 定义路由规则
const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/article/detail',
    name: 'articleDetail',
    component: ArticleDetail,
  },
  {
    path: '/article/list',
    name: 'articleList',
    component: ArticleList
  },
  {
    path: '/project/list',
    name: 'projects',
    component: Projects
  },
  {
    path: '/drawing',
    name: 'drawing',
    component: Drawing
  },
  {
    path: '/about',
    name: 'about',
    component: AboutMe
  }
]

// 3. 创建路由实例
const router = createRouter({
  history: createWebHistory(), // 使用 HTML5 History 模式
  routes
})

export default router