# 📦 Product Pilot Home - 部署说明文档

本文件说明如何在 VirtualBox 虚拟机中部署 Product Pilot Home 项目所依赖的基础服务，包括：

- 🐳 MySQL 5.7（Docker 容器）
- 🌐 Nginx 1.10（Docker 容器，支持反向代理与静态资源托管）
- 🔧 Nacos 注册中心（单独运行）
- 🚀 后端服务（IDEA 启动）

---

## 🧰 环境准备

| 组件        | 推荐配置                           |
|-------------|------------------------------------|
| 虚拟机       | VirtualBox + CentOS 7              |
| Docker      | 已安装，推荐版本 ≥ 20.x            |
| 项目代码     | 克隆或下载至 `/home/user/product-pilot-home` |
| 目录挂载点   | `/mydata/mysql`、`/mydata/nginx`  |
| Nacos       | 单独运行，版本建议 1.1.3            |

---

## 📁 推荐部署结构

```
bash
/mydata/
├── mysql/
│   ├── conf/            # 可放置 my.cnf（可选）
│   ├── data/            # 数据持久化目录
│   └── log/             # MySQL 日志目录

├── nginx/
│   ├── conf/            # 包含 nginx.conf 和 productpilothome.conf
│   ├── logs/            # Nginx 日志输出
│   └── html/            # Vue 前端打包资源

product-pilot-home/
└── deploy/
    ├── start-docker.sh       # 一键启动脚本
    ├── README_DEPLOY.md      # 本部署文档
    └── docker/
        └── mysql/init.sql    # 可选，数据库初始化脚本
```

---
## 🚀 一键启动容器服务
✅ 步骤一：运行启动脚本
进入部署目录，执行：
```
bash
cd product-pilot-home/deploy
chmod +x start-docker.sh
./start-docker.sh
```
脚本将执行以下操作：
- 启动 MySQL（监听 3306 端口，挂载数据与配置目录）
- 启动 Nginx（监听 80 端口，自动加载静态页面与反向代理配置）

📢 注意：容器重启前会自动清除旧容器。

---
✅ 步骤二：启动后端服务模块（IDEA）
以下模块需要你在 IDEA 中手动启动：

模块(端口):启动类
- pph-gateway(5050):PphGatewayApplication
- pph-commodity(9090):PphCommodityApplication
- pph-service(7070):PphServiceApplication
- renren-fast(8080):RenrenApplication（管理后台）

确保这些模块中数据库连接配置与实际一致：

```
yml
spring:
    datasource:
        url: jdbc:mysql://localhost:3306/pph_commodity
        username: root
        password: root
```
---
✅ 步骤三：启动 Nacos 注册中心（非容器）
请在宿主机运行 Nacos：

```
bash
cd /your/path/to/nacos/bin
./startup.sh -m standalone
```
访问控制台：http://localhost:8848/nacos
默认账号密码：admin / nacos
---
✅ 步骤四：容器状态检查
```
bash
docker ps --filter "name=mysql" --filter "name=nginx"
```
若需重启服务：

```bash
sudo docker restart mysql
sudo docker restart nginx
```
---
✅ 验证部署成功

- 后台系统访问	http://localhost:8080
- 前端 Vue 页面	http://localhost/
- 网关代理接口	http://localhost/api/xxx
- Nacos 控制台	http://localhost:8848/nacos
- 数据库连接验证	使用 Navicat 或 mysql -u root