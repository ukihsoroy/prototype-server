<template>
  <div id="login_bg">
    <div>
        <el-input v-model="username" placeholder="请输入用户名" />
        <el-input v-model="password" placeholder="请输入密码"/>
        <el-button @click="login">Login</el-button>
    </div>
  </div>
</template>

<script>
export default {
    props: {
        username: "",
        password: ""
    },
    methods: {
        login () {
            this.$notify({
                title: 'It works!',
                type: 'success',
                message: 'We\'ve laid the ground work for you. It\'s time for you to build something epic!',
                duration: 5000
            })
        }
    }
}
</script>

<style>
#login_bg {
  font-family: Helvetica, sans-serif;
  text-align: center;
}
</style>
