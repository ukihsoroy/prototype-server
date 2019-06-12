// https://router.vuejs.org/zh/

import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

import Layout from '@/layout'

const routes = [
  {
    path: '/',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'ceshi0',
        component: () => import('@/views/dashboard/index'),
        meta: { title: '测试零' }
      }
    ]
  },
  {
    path: '/dashboard',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'ceshi1',
        component: () => import('@/views/dashboard/index'),
        meta: { title: '测试一' }
      }
    ]
  },
  {
    path: '/dashboard',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'ceshi2',
        component: () => import('@/views/dashboard/index'),
        meta: { title: '测试二' }
      }
    ]
  },
  {
    path: '/dashboard',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'ceshi3',
        component: () => import('@/views/dashboard/index'),
        meta: { title: '测试三' }
      }
    ]
  },
  {
    path: '/dashboard',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'ceshi4',
        component: () => import('@/views/dashboard/index'),
        meta: { title: '测试四' }
      }
    ]
  },
  {
    path: '/dashboard',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'ceshi5',
        component: () => import('@/views/dashboard/index'),
        meta: { title: '测试五' }
      }
    ]
  },
  {
    path: '/dashboard',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'ceshi6',
        component: () => import('@/views/dashboard/index'),
        meta: { title: '测试六' }
      }
    ]
  },
  {
    path: '/dashboard',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'ceshi7',
        component: () => import('@/views/dashboard/index'),
        meta: { title: '测试七' }
      }
    ]
  },
  {
    path: '/dashboard',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'ceshi8',
        component: () => import('@/views/dashboard/index'),
        meta: { title: '测试八' }
      }
    ]
  },
  {
    path: '/dashboard',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'ceshi9',
        component: () => import('@/views/dashboard/index'),
        meta: { title: '测试九' }
      }
    ]
  },
  {
    path: '/dashboard',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'ceshi10',
        component: () => import('@/views/dashboard/index'),
        meta: { title: '测试十' }
      }
    ]
  },
  {
    path: '/dashboard',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'ceshi11',
        component: () => import('@/views/dashboard/index'),
        meta: { title: '测试十一' }
      }
    ]
  },
  {
    path: '/dashboard',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'ceshi12',
        component: () => import('@/views/dashboard/index'),
        meta: { title: '测试十二' }
      }
    ]
  },
  {
    path: '/dashboard',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'ceshi13',
        component: () => import('@/views/dashboard/index'),
        meta: { title: '测试十三' }
      }
    ]
  },
  {
    path: '/dashboard',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'ceshi14',
        component: () => import('@/views/dashboard/index'),
        meta: { title: '测试十四' }
      }
    ]
  },
  {
    path: '/dashboard',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'ceshi15',
        component: () => import('@/views/dashboard/index'),
        meta: { title: '测试十五' }
      }
    ]
  },
  {
    path: '/dashboard',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'ceshi16',
        component: () => import('@/views/dashboard/index'),
        meta: { title: '测试十六' }
      }
    ]
  },
  {
    path: '/dashboard',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'ceshi17',
        component: () => import('@/views/dashboard/index'),
        meta: { title: '测试十七' }
      }
    ]
  },
  {
    path: '/dashboard',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'ceshi18',
        component: () => import('@/views/dashboard/index'),
        meta: { title: '测试十八' }
      }
    ]
  },
  {
    path: '/dashboard',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'ceshi19',
        component: () => import('@/views/dashboard/index'),
        meta: { title: '测试十九' }
      }
    ]
  },
  {
    path: '/dashboard',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'ceshi20',
        component: () => import('@/views/dashboard/index'),
        meta: { title: '测试二十' }
      }
    ]
  }
]

const router = new Router({
  routes
})

export default router
