package com.tech.ass.Camel.service;

import com.tech.ass.Camel.dto.EventDto;
import com.tech.ass.Camel.model.Event;
import com.tech.ass.Camel.repository.EventRepository;
import com.tech.ass.Camel.util.EventModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventImpl implements IEvent {

    private final EventRepository eventRepository;

    public EventImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    @Override
    public void processEvent(EventDto dto) {
        if(dto == null){
            //Create custom exception
            throw new NullPointerException();
        }
        eventRepository.save(EventModelMapper.mapToEntity(dto));
    }

    @Override
    public List<Event> fetchAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> fetchAllEventsAfterData(LocalDateTime date) {
        if (date == null){
            return null;
        }
        return eventRepository.findEventsAfterDate(date);
    }

    @Override
    public List<Event> fetchAllEventsByType(String eventType) {
        if (eventType == null){
            return null;
        }
        return eventRepository.findAllEventsByEventType(eventType);
    }

    @Override
    public void deleteAllEvent() {
        eventRepository.deleteAll();
    }
}
