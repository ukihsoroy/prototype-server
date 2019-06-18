<template>
  <div>
    <template>
      <app-link :to="resolvePath(item.children[0].path)">
        <el-menu-item :index="item.children[0].name">
          <i class="el-icon-menu" />
          <span slot="title">{{ item.children[0].meta.title }}</span>
        </el-menu-item>
      </app-link>
    </template>
  </div>
</template>

<script>
import path from 'path'
import AppLink from './link'

export default {
  name: 'SidebarItem',
  components: { AppLink },
  props: {
    item: {
      type: Object,
      required: true
    },
    basePath: {
      type: String,
      required: true
    }
  },
  methods: {
    resolvePath(routePath) { // 处理路径
      return path.resolve(this.basePath, routePath)
    }
  }
}
</script>

<style>

</style>
