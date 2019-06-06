//http://nodejs.cn/api/
//https://cli.vuejs.org/zh/

const path = require('path')

function resolve(dir){
  return path.join(__dirname,dir)
}

module.exports = {
  publicPath: '/',
  configureWebpack: {
    name: 'vue-element-starter',
    resolve: {
      alias: {
        '@': resolve('src')
      }
    }
  }
}