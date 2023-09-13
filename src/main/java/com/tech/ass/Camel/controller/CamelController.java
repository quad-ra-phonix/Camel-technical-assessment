package com.tech.ass.Camel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tech.ass.Camel.controller.model.EventRequest;
import com.tech.ass.Camel.dto.EventDto;
import com.tech.ass.Camel.service.IEvent;
import com.tech.ass.Camel.util.EventModelMapper;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class CamelController {

    Logger logger = LoggerFactory.getLogger(CamelController.class);

    private final IEvent eventService;

    @Produce(uri = "direct:startRabbitMQPoint")
    private ProducerTemplate template;

    public CamelController(IEvent eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/event/publish")
    public String publishEvent(@RequestParam(required = false) String eventType, @RequestParam(required = false) String eventBody) {
        try {
            EventRequest request = new EventRequest();
            request.setEventType(eventType);
            request.setEventBody(eventBody);

            template.asyncSendBody(template.getDefaultEndpoint(), request);

        } catch (Exception ex) {
            logger.error("Camel Controller - Event publish: Error creating json object to publish to exchange");
        }
        return "";
    }

    @RequestMapping(value = "/event", method = RequestMethod.GET)
    public List<EventDto> getAllEvents() {
        return EventModelMapper.mapResponseList(eventService.fetchAllEvents());
    }

    @GetMapping(value = "/events", produces = "application/json")
    public List<EventDto> getAllEventsAfter(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return EventModelMapper.mapResponseList(eventService.fetchAllEventsAfterData(date));
    }

    @GetMapping(value = "/events/type")
    public List<EventDto> getAllEventByType(@RequestParam String eventType) {
        return EventModelMapper.mapResponseList(eventService.fetchAllEventsByType(eventType));
    }

    @DeleteMapping(value = "/events")
    public void deleteAllEvents() {
        eventService.deleteAllEvent();
    }
}
