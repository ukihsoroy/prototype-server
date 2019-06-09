# sigma-browser

## 启动

1. 修改resource/application-dev.yml, 添加data source配置
2. run BrowserApplicationServer

## 打包部署

```shell
mvn clean package
nohup java -jar -Xmx128m -Xms128m -Dspring.profiles.active=uat sigma-browser-1.0-SNAPSHOT.jar &
```