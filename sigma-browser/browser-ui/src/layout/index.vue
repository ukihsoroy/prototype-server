<template>
  <div class="app-wrapper">
    <Sidebar class="sidebar-container" />
  </div>
</template>

<script>
import { Sidebar } from './components'

export default {
  name: 'Layout',
  components: {
    Sidebar
  }
}
</script>

<style lang="scss" scoped>
  .app-wrapper {
    position: relative;
    width: 100%;
    height: 100%;
  }
</style>

