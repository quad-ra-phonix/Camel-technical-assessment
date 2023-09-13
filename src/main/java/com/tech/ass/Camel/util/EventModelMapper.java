package com.tech.ass.Camel.util;

import com.tech.ass.Camel.dto.EventDto;
import com.tech.ass.Camel.model.Event;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class EventModelMapper {

    public static EventDto mapToDTO(Event event){
        EventDto dto = new EventDto();
        dto.setEventBody(event.getEventBody());
        dto.setEventType(event.getEventType());
        dto.setEventDate(event.getEventDate().toString());
        dto.setEventId(event.getId());
        return dto;
    }

    public static Event mapToEntity(EventDto dto){
        Event event = new Event();
        event.setId(dto.getEventId());
        event.setEventType(dto.getEventType());
        event.setEventBody(dto.getEventBody());
        event.setEventDate(LocalDateTime.parse(dto.getEventDate()));
        return event;
    }

    public static List<EventDto> mapResponseList(List<Event> events){
        List<EventDto> responses = new ArrayList<>();
        events.forEach(c -> {
            responses.add(mapToDTO(c));
        });
        return responses;
    }
}
