#!/bin/bash

# 🚀 启动 Product Pilot Home 所需的基础 Docker 服务
# 基于：CentOS 7 + Docker + MySQL 5.7 + Nginx 1.10

# MySQL 配置
MYSQL_CONTAINER_NAME=mysql
MYSQL_IMAGE=mysql:5.7
MYSQL_PORT=3306
MYSQL_PASSWORD=root
MYSQL_DATA_DIR="/mydata/mysql"

# Nginx 配置
NGINX_CONTAINER_NAME=nginx
NGINX_IMAGE=nginx:1.10
NGINX_PORT=80
NGINX_DATA_DIR="/mydata/nginx"

echo "========== 🚧 清理旧容器 =========="
docker rm -f $MYSQL_CONTAINER_NAME > /dev/null 2>&1
docker rm -f $NGINX_CONTAINER_NAME > /dev/null 2>&1

echo "========== 🐳 启动 MySQL ($MYSQL_IMAGE) =========="
sudo docker run -p $MYSQL_PORT:3306 \
  --name $MYSQL_CONTAINER_NAME \
  -v $MYSQL_DATA_DIR/log:/var/log/mysql \
  -v $MYSQL_DATA_DIR/data:/var/lib/mysql \
  -v $MYSQL_DATA_DIR/conf:/etc/mysql \
  -e MYSQL_ROOT_PASSWORD=$MYSQL_PASSWORD \
  -d $MYSQL_IMAGE

echo "========== 🐳 启动 Nginx ($NGINX_IMAGE) =========="
sudo docker run -p $NGINX_PORT:80 \
  --name $NGINX_CONTAINER_NAME \
  -v $NGINX_DATA_DIR/html:/usr/share/nginx/html \
  -v $NGINX_DATA_DIR/logs:/var/log/nginx \
  -v $NGINX_DATA_DIR/conf:/etc/nginx \
  -d $NGINX_IMAGE

echo "✅ 所有容器启动完成。当前运行状态："
docker ps --filter "name=mysql" --filter "name=nginx"

echo "📢 请确保 Nacos 服务已在宿主机或其他终端运行： http://localhost:8848/nacos"
