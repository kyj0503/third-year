package com.webproject.kangneng_back.calendar.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webproject.kangneng_back.calendar.entity.Attendee;
import com.webproject.kangneng_back.calendar.entity.Event;
import com.webproject.kangneng_back.calendar.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalendarService {

    private final EventRepository eventRepository;

    public CalendarService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event saveEvent(String jsonString) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonString);

        Event event = new Event();
        event.setTitle(jsonNode.path("title").asText());
        event.setDescription(jsonNode.path("description").asText(null));
        event.setLocation(jsonNode.path("location").asText(null));

        // ZonedDateTime을 사용하여 날짜와 시간을 파싱
        String startDateTime = jsonNode.path("start").path("dateTime").asText();
        String endDateTime = jsonNode.path("end").path("dateTime").asText();

        // start가 null이 아닐 때만 ZonedDateTime으로 파싱
        if (!startDateTime.isEmpty()) {
            event.setStartTime(ZonedDateTime.parse(startDateTime).toLocalDateTime());  // ZonedDateTime -> LocalDateTime으로 변환
        }

        // end가 null이 아니면 ZonedDateTime으로 파싱
        if (!endDateTime.isEmpty()) {
            event.setEndTime(ZonedDateTime.parse(endDateTime).toLocalDateTime());  // ZonedDateTime -> LocalDateTime으로 변환
        }

        event.setTimeZone(jsonNode.path("start").path("timeZone").asText());
        event.setPriority(jsonNode.path("priority").asInt(0));

        List<Attendee> attendees = new ArrayList<>();
        jsonNode.path("attendees").forEach(attendeeNode -> {
            Attendee attendee = new Attendee();
            attendee.setName(attendeeNode.path("name").asText());
            attendee.setEvent(event);
            attendees.add(attendee);
        });
        event.setAttendees(attendees);

        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
