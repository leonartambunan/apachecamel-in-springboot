package id.co.nio;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class PubSubReceiver extends RouteBuilder {
    Logger logger = LoggerFactory.getLogger(PubSubReceiver.class.getName());

    @Autowired
    private Environment env;

    @Autowired
    ProducerTemplate producerTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    final static String ROUTE_NAME="Route-1:PubSubReceiver";

    @Override
    public void configure() throws Exception {

        String uri = env.getProperty("app.redis_pubsub_uri");

        logger.info(uri);

        from(uri)
                .routeId(ROUTE_NAME)
                .process(exchange -> {
                    final String text = exchange.getIn().getBody(String.class);
                    log.info("**********************************");
                    log.info("Thanks God, I got some work to do");
                    log.info("Processing {}",ROUTE_NAME);
                }).to("seda:processor");
    }
}
