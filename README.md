## spring-boot-demo2

* jdk 1.80_201
* Spring Boot 2.1.5.RELEASE
* Redis 4.0.9
* Mysql 5.6
* RabbitMQ 3.9.13
* MongoDB 5.0.6
* Elasticsearch 6.4.3

## 功能

* 集成 Redis
* 集成 Mail
* 集成 Thymeleaf
* 集成 MongoDB
* 集成 Spring Data JPA
* 集成 MyBatis
* 集成 RabbitMQ
* 集成 Spring Boot Admin
* 集成 Prometheus监控
* 集成 Elasticsearch
* 集成 Swagger
* 集成 Quartz
* 图片上传

### es安装分词器

./elasticsearch-plugin
install https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v6.4.3/elasticsearch-analysis-ik-6.4.3.zip

### 安装redis

https://github.com/redis/redis/archive/refs/tags/4.0.9.zip
cd /Applications/redis-4.0.9/ make

### 安装rabbitmq

brew install rabbitmq

https://github.com/rabbitmq/rabbitmq-delayed-message-exchange/releases/download/3.9.0/rabbitmq_delayed_message_exchange-3.9.0.ez

rabbitmq-plugins enable rabbitmq_delayed_message_exchange

sbin/rabbitmq-plugins enable rabbitmq_delayed_message_exchange

### 安装mongodb

brew tap mongodb/brew

brew install mongodb-community

### 启动es

sh /Applications/elasticsearch-6.4.3/bin/elasticsearch

### 启动redis

/Applications/redis-4.0.9/src/redis-server

### 启动rabbitmq

/usr/local/Cellar/rabbitmq/3.9.13/sbin/rabbitmq-server -detached

### 启动mongodb

mongod --config /usr/local/etc/mongod.conf