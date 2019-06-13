<template>
  <div>
    <el-scrollbar wrap-class="scrollbar-wrapper">
      <el-menu
        default-active="ceshi0"
        :collapse="false"
        :background-color="variables.menuBg"
        :text-color="variables.menuText"
        :unique-opened="false"
        :active-text-color="variables.menuActiveText"
        :collapse-transition="false"
        mode="vertical"
      >
        <sidebar-item v-for="(route,index) in routes" :key="index" :item="route" :base-path="route.path" />
      </el-menu>
    </el-scrollbar>
  </div>
</template>

<script>
import variables from '@/styles/variables.scss'
import SidebarItem from './SidebarItem'

export default {
  components: {
    SidebarItem
  },
  computed: {
    routes() {
      return this.$router.options.routes
    },
    variables() {
      return variables
    }
  }
}
</script>

<style>

</style>
