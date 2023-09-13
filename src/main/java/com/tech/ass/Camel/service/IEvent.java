package com.tech.ass.Camel.service;

import com.tech.ass.Camel.dto.EventDto;
import com.tech.ass.Camel.model.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface IEvent {
    void processEvent(EventDto dto);

    List<Event> fetchAllEvents();

    List<Event> fetchAllEventsAfterData(LocalDateTime date);

    List<Event> fetchAllEventsByType(String eventType);

    void deleteAllEvent();
}
