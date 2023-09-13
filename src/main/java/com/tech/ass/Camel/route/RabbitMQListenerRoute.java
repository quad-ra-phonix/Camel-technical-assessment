package com.tech.ass.Camel.route;

import com.tech.ass.Camel.dto.EventDto;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RabbitMQListenerRoute extends RouteBuilder {
    @Value("${spring.listener.route.url}")
    private String routeUrl;

    @Override
    public void configure() throws Exception {
        from(routeUrl)
                .log(LoggingLevel.ERROR, "Got this message from rabbit: ${body}")
                .unmarshal().json(JsonLibrary.Jackson, EventDto.class)
                .process(this::buildEventDTO)
                .to("bean:eventImpl?method=processEvent(${body})");
    }

    private void buildEventDTO(Exchange exchange) {
        EventDto event = exchange.getMessage().getBody(EventDto.class);
        event.setEventDate(LocalDateTime.now().toString());
    }
}
