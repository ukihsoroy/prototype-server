# vue-admin-template

English | [简体中文](./README-zh.md)

> A minimal vue admin template with Element UI & axios & iconfont & permission control & lint

**Live demo:** http://panjiachen.github.io/vue-admin-template


**The current version is `v4.0+` build on `vue-cli`. If you want to use the old version , you can switch branch to [tag/3.11.0](https://github.com/PanJiaChen/vue-admin-template/tree/tag/3.11.0), it does not rely on `vue-cli`**

## Build Setup


```bash
# clone the project
git clone https://github.com/PanJiaChen/vue-admin-template.git

# enter the project directory
cd vue-admin-template

# install dependency
npm install

# develop
npm run dev
```

This will automatically open http://localhost:9528

## 项目涉及的相关文档
```
Node API          http://nodejs.cn/api/
Webpack           https://webpack.docschina.org/
Vue               https://cn.vuejs.org/
Vue-Cli           https://cli.vuejs.org/zh/
Vue-Router        https://router.vuejs.org/zh/
Vue-Loader        https://vue-loader.vuejs.org/zh/
Vue-Eslint        https://github.com/vuejs/eslint-config-vue
Sass              https://www.sass.hk/
Normalize.css     http://necolas.github.io/normalize.css/
Vuex              https://vuex.vuejs.org/zh/
```

## Advanced

```bash
# preview the release environment effect
npm run preview

# preview the release environment effect + static resource analysis
npm run preview -- --report

# code format check
npm run lint

# code format check and auto fix
npm run lint -- --fix
```
npm install -g serve
# -s 参数的意思是将其架设在 Single-Page Application 模式下
# 这个模式会处理即将提到的路由问题
serve -s dist
```

http://39.105.164.165:1337/sigma/swagger-ui.html