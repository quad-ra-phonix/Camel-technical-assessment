package com.tech.ass.Camel.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.ass.Camel.controller.model.EventRequest;
import com.tech.ass.Camel.util.DataUtil;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CamelControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void publishEvents() throws Exception {
        //list objects
        List<EventRequest> requests = DataUtil.getEvents();
        requests.forEach(eventRequest -> {
            try {
                mockMvc.perform(get("/event/publish")
                        .param("eventType", eventRequest.getEventType())
                        .param("eventBody", eventRequest.getEventBody()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void publishEvents_Random() throws Exception {
        //list objects
        EasyRandom generator = new EasyRandom();
        List<EventRequest> requests = generator.objects(EventRequest.class, 5)
                .collect(Collectors.toList());
        requests.forEach(eventRequest -> {
            try {
                mockMvc.perform(get("/event/publish")
                        .param("eventType", eventRequest.getEventType())
                        .param("eventBody", eventRequest.getEventBody()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void publishEvent_nullEventType() throws Exception {
        EasyRandom generator = new EasyRandom();
        EventRequest request = generator.nextObject(EventRequest.class);
        mockMvc.perform(get("/event/publish")
                .param("eventBody", request.getEventBody()));
    }

    @Test
    public void publishEvent_nullEventBody() throws Exception {
        EasyRandom generator = new EasyRandom();
        EventRequest request = generator.nextObject(EventRequest.class);
        mockMvc.perform(get("/event/publish")
                .param("eventType", request.getEventType()));
    }

}
