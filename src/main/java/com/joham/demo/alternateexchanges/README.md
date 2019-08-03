# 使用手册
### 概念
备份交换器是为了实现没有路由到队列的消息，声明交换机的时候添加属性alternate-exchange，声明一个备用交换机，一般声明为fanout类型，这样交换机收到路由不到队列的消息就会发送到备用交换机绑定的队列中。
1. 如果设置的备份交换器不存在,则消息丢失,没有异常
2. 如果备份交换器没有绑定任何队列,则消息丢失,没有异常
3. 如果备份交换器没有匹配的RoutingKey的队列,则消息丢失,没有异常
4. 如果备份交换器和mandatory参数同事使用,则mandatory参数不生效
### 运行示例

1、启动 RabbitMQ

```
docker run -d --hostname rabbit --name rabbit -p 5672:5672 -p 15672:15672 -e RABBITMQ_DEFAULT_USER=anoyi -e RABBITMQ_DEFAULT_PASS=Passw0rd rabbitmq:3.7-management
```

2、运行 `Application` (或者执行 `mvn spring-boot:run`)

### 测试

**1、测试备用交换器**

```
curl -X POST \
  http://127.0.0.1:8080/alternateexchanges/send \
  -H 'Content-Type: application/json' \
  -d '{
	"exchange":"exchange-direct-no-queue",
	"routingKey": "direct.queue.alternate",
	"content":" hello AE! ",
	"count": 1
}'
```

`routingKey` 为备用交换器的路由键
