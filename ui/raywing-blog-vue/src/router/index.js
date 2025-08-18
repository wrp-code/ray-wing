import { createRouter, createWebHistory } from 'vue-router'
import ArticleDetail from '../views/ArticleDetail.vue'

// 1. 定义路由组件（懒加载推荐）
const Home = () => import('@/views/Home.vue')
// const ArticleDetail = () => import('@/views/ArticleDetail.vue')

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
    component: ArticleDetail
  }
]

// 3. 创建路由实例
const router = createRouter({
  history: createWebHistory(), // 使用 HTML5 History 模式
  routes
})

export default router