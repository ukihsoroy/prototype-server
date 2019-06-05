//相关文档
//http://nodejs.cn/api/

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