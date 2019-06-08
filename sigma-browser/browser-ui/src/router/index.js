// https://router.vuejs.org/zh/
// 采用路由懒加载，随用随载

import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const routes = [
  {
    path: '/',
    component: () => import('@/views/login')
  }
]

const router = new Router({
  routes
})

export default router
