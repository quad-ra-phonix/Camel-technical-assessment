package com.tech.ass.Camel.controller.model;

public class EventRequest {
    private String eventType;
    private String eventBody;


    public EventRequest() {
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventBody() {
        return eventBody;
    }

    public void setEventBody(String eventBody) {
        this.eventBody = eventBody;
    }
}
