# sigma-browser

## 启动

1. 修改resource/application-dev.yml, 添加data source配置
2. run BrowserApplicationServer

## 打包部署

```shell
# 打包
mvn clean package

# 启动
nohup java -jar -Xmx128m -Xms128m -Dspring.profiles.active=uat sigma-browser-1.0-SNAPSHOT.jar &

# tail log
tail -f nohup.out
```

## 功能列表

1. 登陆注册
2. 方法级权限
3. 菜单接口
4. 权限接口
5. 用户管理
6. 文件上传下载
7. excel导入导出
8. csv导入导出
9. 微信登陆
10. QQ登陆
11. 微博登陆
12. 代码生成

> ------

## 接下来待开发功能

1. 手机号登陆
2. Excel导入导出功能
3. CSV导入导出
4. 重构一次
