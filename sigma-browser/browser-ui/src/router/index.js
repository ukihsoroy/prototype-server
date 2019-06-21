// https://router.vuejs.org/zh/

import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

import Layout from '@/layout'

const routes = [
  {
    path: '/',
    component: Layout,
    redirect: '/ceshi',
    children: [{
      path: 'ceshi',
      name: 'ceshi',
      component: () => import('@/views/ceshi/index'),
      meta: { title: '菜单一' }
    }]
  },
  {
    path: '/dashboard',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'dashboard',
        component: () => import('@/views/dashboard/index'),
        meta: { title: '菜单二' }
      }
    ]
  },
  {
    path: '/example',
    component: Layout,
    redirect: '/example/table',
    name: 'Example',
    meta: { title: 'Example' },
    children: [
      {
        path: 'table',
        name: 'Table',
        component: () => import('@/views/table/index'),
        meta: { title: 'Table' }
      },
      {
        path: 'tree',
        name: 'Tree',
        component: () => import('@/views/tree/index'),
        meta: { title: 'Tree' }
      }
    ]
  }
]

const router = new Router({
  routes
})

export default router
