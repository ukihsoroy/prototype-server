<template>
  <div>
    <el-scrollbar wrap-class="scrollbar-wrapper">
      <el-menu
        mode="vertical"
        :background-color="variables.menuBg"
        :text-color="variables.menuText"
        :active-text-color="variables.menuActiveText"
        default-active="ceshi0"
      >
        <el-menu-item v-for="(route,index) in routes" :key="index" :index="route.children[0].name">
          <i class="el-icon-menu" />
          <span slot="title">{{ route.children[0].meta.title }}</span>
        </el-menu-item>
      </el-menu>
    </el-scrollbar>
  </div>
</template>

<script>
import variables from '@/styles/variables.scss'

export default {
  components: {

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
