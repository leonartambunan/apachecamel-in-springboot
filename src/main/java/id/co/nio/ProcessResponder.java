package id.co.nio;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ProcessResponder extends RouteBuilder {

    @Autowired
    private EipRedisPublisher eipRedisPublisher;

    @Autowired
    private Environment env;

    @Autowired
    ProducerTemplate producerTemplate;

    final static String ROUTE_NAME="Route-10:ProcessResponder";
    @Override
    public void configure() throws Exception {
        from("seda:process-responder")
                .routeId(ROUTE_NAME)
                .process(new Processor() {
                             public void process(Exchange exchange) throws Exception {

                                 log.info("I am in seda:process-responder");

                                 String response = (String) exchange.getIn().getBody();

                                 log.info("I am sending a response ", response);

                                 eipRedisPublisher.publish("task-response",response);
                             }
                         }
                );
    }
}
