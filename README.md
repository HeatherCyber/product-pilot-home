# 🚀 Product Pilot Home - Distributed E-commerce Demo Platform

> **📱 Frontend Repository**: [product-pilot-home-frontend](https://github.com/HeatherCyber/product-pilot-home-frontend) - Vue.js Frontend Application

## 🌟 Overview

This project was developed in a professional training bootcamp under instructor guidance, focusing on backend microservices development and enterprise-style deployment practices. It simulates a distributed e-commerce product management platform, applying concepts of service decomposition, service discovery, API gateway routing, and containerized deployment.

## 🏗️ Architecture

### Backend Services (Current Repository)
```
product-pilot-home/
├── pph-commodity/      # Product catalog microservice (Custom)
├── pph-service/        # File storage service (Aliyun OSS integration)
├── pph-gateway/        # API Gateway (Spring Cloud Gateway)
├── renren-fast/        # Admin management system (Open source scaffold)
├── renren-generator/   # Code generator (MyBatis-Plus) (Open source scaffold)
├── pph-common/         # Shared utilities and components
└── deploy/             # Docker deployment configurations
```

### Frontend Application (Separate Repository)
```
product-pilot-home-frontend/
├── src/                # Vue.js source code
├── static/             # Static assets and plugins
├── build/              # Webpack build configuration
├── config/             # Environment configuration
├── package.json        # Frontend dependencies
└── README.md           # Frontend documentation
```

## 🛠️ Technology Stack

| Category | Technologies |
|----------|-------------|
| **Backend** | Spring Boot, Spring Cloud, MyBatis-Plus |
| **Database** | MySQL 5.7 |
| **Service Registry** | Nacos |
| **API Gateway** | Spring Cloud Gateway |
| **File Storage** | Aliyun OSS |
| **Frontend** | Vue.js, ElementUI, Axios |
| **DevOps** | Docker, Nginx, Linux (CentOS 7 VMs) |

## ⚙️ Deployment

### Infrastructure Setup
- **Virtual Environment**: CentOS 7 VMs simulating distributed infrastructure
- **Remote Database**: MySQL running on a separate VM instance
- **Load Balancer**: Nginx reverse proxy for API routing and load balancing
- **Containerization**: Docker-based deployment for service isolation and orchestration

## 📚 Key Features

### Custom Development
- **Product Catalog Service**: SPU/SKU modeling and management
- **Category and Brand Management**: Complete product organization system
- **File Upload Service**: Aliyun OSS integration for cloud storage
- **API Gateway**: Centralized routing and cross-cutting concerns
- **Containerized Deployment**: Docker-based service orchestration

### Open Source Integration
- **Admin Dashboard**: Built on [renren-fast](https://gitee.com/renrenio/renren-fast) scaffold
- **Code Generation**: Leveraging [renren-generator](https://gitee.com/renrenio/renren-generator)
- **Frontend Framework**: Extended [renren-fast-vue](https://gitee.com/renrenio/renren-fast-vue) with e-commerce modules

## 👤 My Contributions

- ✅ **Custom Microservices**: Implemented commodity and OSS services from scratch
- ✅ **Service Integration**: Integrated Spring Cloud Gateway and Nacos for routing and service discovery
- ✅ **Enterprise Deployment**: Designed and tested deployment on Docker + Nginx + CentOS VMs
- ✅ **Full-Stack Integration**: Connected backend services with Vue.js frontend
- ✅ **Business Logic**: Extended e-commerce domain logic (SPU/SKU, categories, brands)

## 📄 License

This project is for educational and learning purposes, licensed under the MIT License.

---

📌 **Note**: This project is for educational purposes. Many business features are still in progress, but the backend already demonstrates a complete distributed architecture and core e-commerce functionality.