# Apache Camel in Spring Boot
Apache Camel hosted in SpringBoot listening to Redis PubSub topic (and responds back to another topic)


```                                                                            
             o-----------------------o                o-----------------------------o               o-----------------------o
request -->  | REDIS - request topic | <---listen---> |  Camel Redis Component      |---forward---> |  Camel SEDA Component |
             o-----------------------o                o-----------------------------o               o-----------------------o
                                                                                                               |
                                                                                                               |
                                                                                                               V
                                                                                                            
             o----------------------o                                                         o---------------------------o
response <-- |REDIS - response topic| <------------------publish------------------------------|  Spring Redis Publisher   |
             o----------------------o                                                         o---------------------------o
                                                                                                          

```

# How to build

```mvn clean package```

# How to run
Prerequisites: Redis Server is installed in localhost

Execute the following command line

```java -jar target/camel-springboot-1.0.0.jar```

Example console output:

```
nacho@libre:/tmp/apachecamel-in-springboot$ java -jar target/camel-springboot-1.0.0.jar 
          _____                    _____                    _____
         /\    \                  /\    \                  /\    \
        /::\    \                /::\    \                /::\    \
       /::::\    \               \:::\    \              /::::\    \
      /::::::\    \               \:::\    \            /::::::\    \
     /:::/\:::\    \               \:::\    \          /:::/\:::\    \
    /:::/__\:::\    \               \:::\    \        /:::/__\:::\    \
   /::::\   \:::\    \              /::::\    \      /::::\   \:::\    \
  /::::::\   \:::\    \    ____    /::::::\    \    /::::::\   \:::\    \
 /:::/\:::\   \:::\    \  /\   \  /:::/\:::\    \  /:::/\:::\   \:::\____\
/:::/__\:::\   \:::\____\/::\   \/:::/  \:::\____\/:::/  \:::\   \:::|    |
\:::\   \:::\   \::/    /\:::\  /:::/    \::/    /\::/    \:::\  /:::|____|
 \:::\   \:::\   \/____/  \:::\/:::/    / \/____/  \/_____/\:::\/:::/    /
  \:::\   \:::\    \       \::::::/    /                    \::::::/    /
   \:::\   \:::\____\       \::::/____/                      \::::/    /
    \:::\   \::/    /        \:::\    \                       \::/____/
     \:::\   \/____/          \:::\    \                       ~~
      \:::\    \               \:::\    \
       \:::\____\               \:::\____\   leonar.tambunan@gmail.com
        \::/    /                \::/    /
         \/____/                  \/____/

2021-03-24 00:27:17.761  INFO 10254 --- [           main] id.co.nio.RedisEipApplication            : Starting RedisEipApplication v1.0.0 on libre with PID 10254 (/tmp/apachecamel-in-springboot/target/camel-springboot-1.0.0.jar started by nacho in /tmp/apachecamel-in-springboot)
2021-03-24 00:27:17.765  INFO 10254 --- [           main] id.co.nio.RedisEipApplication            : No active profile set, falling back to default profiles: default
2021-03-24 00:27:18.278  INFO 10254 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Multiple Spring Data modules found, entering strict repository configuration mode!
2021-03-24 00:27:18.278  INFO 10254 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data Redis repositories in DEFAULT mode.
2021-03-24 00:27:18.300  INFO 10254 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 7ms. Found 0 Redis repository interfaces.
2021-03-24 00:27:18.526  INFO 10254 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Multiple Spring Data modules found, entering strict repository configuration mode!
2021-03-24 00:27:18.527  INFO 10254 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data Redis repositories in DEFAULT mode.
2021-03-24 00:27:18.536  INFO 10254 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 9ms. Found 0 Redis repository interfaces.
2021-03-24 00:27:18.739  INFO 10254 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'org.apache.camel.spring.boot.CamelAutoConfiguration' of type [org.apache.camel.spring.boot.CamelAutoConfiguration$$EnhancerBySpringCGLIB$$17b182b7] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
2021-03-24 00:27:19.033  INFO 10254 --- [           main] o.a.c.i.converter.DefaultTypeConverter   : Type converters loaded (core: 195, classpath: 1)
2021-03-24 00:27:19.526  INFO 10254 --- [           main] id.co.nio.MainProcessor                  : seda:processor
2021-03-24 00:27:19.565  INFO 10254 --- [           main] id.co.nio.PubSubReceiver                 : spring-redis://localhost:6379?connectionFactory=#jedisConnectionFactory&redisTemplate=#redisTemplate&serializer=#stringSerializer&command=SUBSCRIBE&channels=task-request
2021-03-24 00:27:19.566  INFO 10254 --- [           main] o.a.camel.spring.boot.RoutesCollector    : Loading additional Camel XML routes from: classpath:camel/*.xml
2021-03-24 00:27:19.566  INFO 10254 --- [           main] o.a.camel.spring.boot.RoutesCollector    : Loading additional Camel XML rests from: classpath:camel-rest/*.xml
2021-03-24 00:27:19.574  INFO 10254 --- [           main] o.a.camel.spring.SpringCamelContext      : Apache Camel 2.25.2 (CamelContext: camel-1) is starting
2021-03-24 00:27:19.575  INFO 10254 --- [           main] o.a.c.m.ManagedManagementStrategy        : JMX is enabled
2021-03-24 00:27:19.823  INFO 10254 --- [           main] o.a.camel.spring.SpringCamelContext      : StreamCaching is not in use. If using streams then its recommended to enable stream caching. See more details at http://camel.apache.org/stream-caching.html
2021-03-24 00:27:19.824  INFO 10254 --- [           main] o.a.camel.component.seda.SedaEndpoint    : Endpoint seda://processor is using shared queue: seda://processor with size: 1000
2021-03-24 00:27:19.855  INFO 10254 --- [           main] o.a.camel.component.seda.SedaEndpoint    : Endpoint seda://process-responder is using shared queue: seda://process-responder with size: 1000
2021-03-24 00:27:19.871  INFO 10254 --- [           main] o.a.camel.spring.boot.RoutesCollector    : Starting CamelMainRunController to ensure the main thread keeps running
2021-03-24 00:27:19.876  INFO 10254 --- [           main] o.a.camel.spring.SpringCamelContext      : Route: Route-2:Processor started and consuming from: seda://processor
2021-03-24 00:27:19.878  INFO 10254 --- [           main] o.a.camel.spring.SpringCamelContext      : Route: Route-10:ProcessResponder started and consuming from: seda://process-responder
2021-03-24 00:27:19.880  INFO 10254 --- [           main] o.a.camel.spring.SpringCamelContext      : Route: Route-1:PubSubReceiver started and consuming from: spring-redis://localhost:6379?channels=task-request&command=SUBSCRIBE&connectionFactory=%23jedisConnectionFactory&redisTemplate=%23redisTemplate&serializer=%23stringSerializer
2021-03-24 00:27:19.882  INFO 10254 --- [           main] o.a.camel.spring.SpringCamelContext      : Total 3 routes, of which 3 are started
2021-03-24 00:27:19.884  INFO 10254 --- [           main] o.a.camel.spring.SpringCamelContext      : Apache Camel 2.25.2 (CamelContext: camel-1) started in 0.309 seconds
2021-03-24 00:27:19.888  INFO 10254 --- [           main] id.co.nio.RedisEipApplication            : Started RedisEipApplication in 2.467 seconds (JVM running for 2.861)

```

# How to test

1. Subscribe to "task-response" topic of Redis.
2. Publish a json string into "task-request" topic of Redis. You will get message in "task-response" topic.

(Ref: https://redis.io/topics/pubsub)

# Commercial collaboration

Feel free to send your inquiries about commercial collaboration (such as systems integration, Android/iOS applications development and other payment solutions) to leonar.tambunan@gmail.com.
