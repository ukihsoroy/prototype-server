// https://router.vuejs.org/zh/

import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

import Layout from '@/layout'

const routes = [
  {
    path: '/',
    component: Layout
  }
]

const router = new Router({
  routes
})

export default router
