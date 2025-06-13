# 📦 产品驾驶舱系统 / Product Pilot Home
多模块企业级微服务系统项目，整合前后端分离架构、注册中心、服务网关与快速开发平台。 

A multi-module enterprise-level microservices project integrating front-back separation, service discovery, gateway routing, and rapid development tools.

## 🌐 项目简介 / Project Overview
本系统基于 Java 技术栈构建，采用 Spring Boot + Spring Cloud 架构，配套 Vue 前端与 renren-fast 后台管理，部署环境为 VirtualBox + CentOS7 + Docker。

This system is built using the Java stack, following the Spring Boot + Spring Cloud architecture, with a Vue-based front end and renren-fast admin panel, deployed in a VirtualBox + CentOS7 + Docker environment.

## 📁 项目结构 / Project Structure
```
bash
product-pilot-home/
├── pph-common/         # 公共依赖模块 / Common utilities
├── pph-commodity/      # 商品服务模块 / Commodity service
├── pph-service/        # 通用业务服务 / General service integration
├── pph-gateway/        # 网关服务 / API Gateway (Spring Cloud Gateway)
├── renren-fast/        # 管理后台系统 / Admin system
├── renren-generator/   # 代码生成器 / Code generator (MyBatis-Plus)
├── deploy/             # 部署文件夹 / Deployment scripts & configs
│   ├── docker/
│   ├── start-docker.sh
│   └── README_DEPLOY.md
├── pom.xml
└── README.md
```

## 🚀 快速启动 / Quick Start
✅ 开发模式 / Development (IDEA)
1. 使用 IntelliJ IDEA 打开项目
Open project using IntelliJ IDEA

2. 启动数据库与 Nginx（详见 deploy/README_DEPLOY.md）
Start MySQL and Nginx containers (see deploy/README_DEPLOY.md)

3. 启动 Nacos 注册中心（推荐版本：1.1.3）
Start Nacos (Recommended: version 1.1.3)

4.启动以下模块 / Start the following modules:

| 模块 / Module     | 端口 / Port | 启动类 / Main Class          |
| --------------- | --------- | ------------------------- |
| `pph-gateway`   | 5050      | `PphGatewayApplication`   |
| `pph-commodity` | 9090      | `PphCommodityApplication` |
| `pph-service`   | 7070      | `PphServiceApplication`   |
| `renren-fast`   | 8080      | `RenrenApplication`       |



## 🐳 容器服务部署 / Docker-based Deployment
使用 start-docker.sh 启动以下基础容器服务：

Use start-docker.sh to launch containerized services:

| 服务 / Service | 镜像 / Image | 端口 / Port |
| ------------ | ---------- | --------- |
| MySQL        | mysql:5.7  | 3306      |
| Nginx        | nginx:1.10 | 80        |


```
bash
cd deploy
chmod +x start-docker.sh
./start-docker.sh
```
📄 查看部署文档 / See full deployment: deploy/README_DEPLOY.md

---
## 🧱 技术栈 / Tech Stack

| 类别 / Type              | 技术 / Technology                                      |
| ---------------------- | ---------------------------------------------------- |
| 前端 / Frontend          | HTML, CSS, JS, Vue, ElementUI, Axios, jQuery, Node.js |
| 后端 / Backend           | Spring Boot, Spring Cloud, MyBatis-Plus              |
| 数据库 / DB               | MySQL 5.7                                            |
| 注册中心 / Registry        | Alibaba Nacos                                        |
| 网关 / Gateway           | Spring Cloud Gateway                                 |
| 文件存储 / OSS             | Alibaba OSS                                          |
| 快速开发 / Rapid Dev       | renren-generator, renren-fast                        |
| 运维工具 / DevOps          | Linux, Docker, Nginx                                 |
| 虚拟化工具 / Virtualization | VirtualBox, Vagrant                                  |
| 常用技术 / Others          | Postman, Maven, Git, CORS, SPU/SKU schema     |

---
## 📚 数据库说明 / Database Notes
- 数据库名Database name：pph_commodity

- 数据库用户User/Password：root / root

- 表结构包括Tables included：SPU、SKU、Category、Brand, etc.

- 初始化脚本路径 Initialization Script path：deploy/docker/mysql/init.sql（可选Optional）

---
## 🔗 常用访问路径 / Key Endpoints
| 模块 / Module          | 地址 / URL                                                                                    |
| -------------------- |---------------------------------------------------------------------------------------------|
| 前端首页 / Frontend UI   | [http://localhost/](http://localhost/)                                                      |
| 后台管理 / Admin Panel   | [http://localhost:8080](http://localhost:8080)                                              |
| 网关服务 / Gateway API   | [http://localhost:5050](http://localhost:5050)                                              |
| 注册中心 / Nacos Console | [http://localhost:8848/nacos](http://localhost:8848/nacos)                                  |
| 接口文档 / Swagger Docs  | [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) or /doc.html |

---
## ✅ 部署验证建议 / Verify Deployment
| 检查点 / Check                 | 工具 / How to Test         |
| --------------------------- |--------------------------|
| 容器是否运行 / Containers running | `docker ps`              |
| 后端服务端口 / Backend reachable  | browser、Postman          |
| Nacos 注册状态 / Nacos status   | Web Console              |
| 接口返回是否正常 / API response     | call `/api/**` Endpoint |

---
## 📘 项目文档 / Documentation
- deploy/README_DEPLOY.md：Docker + 虚拟机部署说明 Virtual Machine

- deploy/start-docker.sh：一键启动脚本One-Click Startup Script

- deploy/docker/mysql/init.sql：数据库初始化脚本Database Initialization Script（可选Optional）

---
## 📄 License
本项目用于教学与学习目的，采用 MIT 开源协议。

This project is for learning & educational purposes and is licensed under the MIT License.


---
📬 如有问题请通过 issue 联系作者或发送邮件。

Feel free to open an issue or contact the author for help.
