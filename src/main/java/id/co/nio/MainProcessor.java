package id.co.nio;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MainProcessor extends RouteBuilder {
    Logger logger = LoggerFactory.getLogger(MainProcessor.class.getName());

    @Autowired
    private Environment env;

    @Autowired
    ProducerTemplate producerTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    final static String ROUTE_NAME="Route-2:Processor";


    @Override
    public void configure() throws Exception {


        String uri = "seda:processor";

        logger.info(uri);
        from(uri)
                .routeId(ROUTE_NAME)
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        final String text = exchange.getIn().getBody(String.class);
                        log.info("**********************************");
                        log.info("Thanks God, I got some work to do");
                        log.info("Processing {}",ROUTE_NAME);

                        JSONObject result = mainProcess(text,ROUTE_NAME,exchange);

                        if (result != null) {
                            Map<String, Object> headersMap = new HashMap<>();
                            producerTemplate.sendBodyAndHeaders("seda:process-responder", result.toString(), headersMap);
                        }
                    }
                });
    }




    private JSONObject mainProcess(final String payload, String callback, Exchange exchange) {
        logger.info("mainProcess");

        JSONObject result = new JSONObject();

        // your logic here...

        return result;
    }
}
