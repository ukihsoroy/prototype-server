<template>
  <div class="app-container">
    <div class="filter-container">
      <span @click="getU">11111111</span>
    </div>
  </div>
</template>

<script>
import { getUserMenu } from '@/api/user'

export default {
  data() {
    return {
      a: 1
    }
  },
  methods: {
    getU() {
      getUserMenu(this.$store.getters.roles[0]).then(response => {
        console.log('111')
        console.log(response)
      })

      return 1
    }
  }
}
</script>
