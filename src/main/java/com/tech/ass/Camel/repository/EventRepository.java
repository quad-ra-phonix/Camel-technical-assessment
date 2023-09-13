package com.tech.ass.Camel.repository;

import com.tech.ass.Camel.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("select e from Event e where e.eventDate >= :compareDate")
    List<Event> findEventsAfterDate(@Param("compareDate") LocalDateTime compareDate);

    @Query("select e from Event e where e.eventType = :eventType")
    List<Event> findAllEventsByEventType(@Param("eventType") String eventType);
}
