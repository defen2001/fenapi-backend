# 项目简介
一个提供 API 接口供开发者调用的平台。
管理员可以接入并发布接口，统计分析各接口调用情况；用户可以注册登录并开通接口调用权限，然后可以浏览接口及在线调试，还能使用客户端 SDK 轻松在代码中调用接口。
项目的前端并不复杂，更侧重后端，包含丰富的编程技巧和架构设计层面的知识。

# 技术选型
## 前端
- React 18
- Ant Design Pro 5.x 脚手架
- Ant Design & Procomponents 组件库
- Umi 4 前端框架
- OpenAPI 前端代码生成

## 后端
- Java Spring Boot
- MySQL Redis 数据库
- MyBatis-Plus 及 MyBatis X 自动生成
- API 签名认证（Http 调用）
- Spring Boot Starter（SDK 开发）
- Dubbo 分布式（RPC、Nacos）
- Swagger + Knife4j 接口文档生成
- Spring Cloud Gateway 微服务网关
- Hutool、Apache Common Utils、Gson 等工具库
- 腾讯云 COS 对象存储

## 业务功能
- 全局请求响应拦截器（记录日志）
- 全局异常处理器
- 自定义错误码
- 封装通用响应类
- Swagger + Knife4j 接口文档
- 自定义权限注解 + 全局校验
- 全局跨域处理
- 多环境配置

## 核心业务
- 用户登录页面开发
- 接口管理功能开发（Ant Design 高级组件）
- 模拟接口项目 | 示例接口开发
- 模拟接口项目 | HTTP 客户端调用
- API 签名认证详解及实战
- 客户端 SDK 开发（Spring Boot Starter）
- 接口管理功能 | 发布 / 下线接口开发
- 接口管理功能 | 前端页面开发
- 接口列表页面开发
- 在线调试接口功能 | 后端接口开发
- 在线调试接口功能 | 前端页面开发

## 快速开始

> 只需要修改 fen-admin 的 application.yml 文件里部分内容即可
```yml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/my_db
    username: root
    password: 123456
# 选择性修改
spring:
  session:
    store-type: redis
# 可不配置
cos:
  secretId:
  secretKey:
  bucketName: image-fenapi-1319981817
  region: ap-guangzhou
  endpoint: cos.ap-guangzhou.myqcloud.com
```

