package com.tech.ass.Camel.route;

import com.tech.ass.Camel.controller.CamelController;
import com.tech.ass.Camel.controller.model.EventRequest;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQPublishRoute extends RouteBuilder {
    Logger logger = LoggerFactory.getLogger(RabbitMQPublishRoute.class);
    @Value("${spring.publisher.route.url}")
    private String routeUrl;

    @Override
    public void configure() throws Exception {
        logger.debug("Sending to {}", routeUrl);
        JacksonDataFormat jsonDataFormat = new JacksonDataFormat(EventRequest.class);

        from("direct:startRabbitMQPoint").id("rabbitMQRoute").marshal(jsonDataFormat)
                .to(routeUrl).end();
    }
}
