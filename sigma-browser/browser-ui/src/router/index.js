// 相关文档
// https://router.vuejs.org/zh/

import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/dashboard'

Vue.use(Router)

const routes = [
  {
    path: '/',
    component: Home
  }
]

const router = new Router({
  routes
})

export default router
