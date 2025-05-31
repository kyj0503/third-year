package com.webproject.kangneng_back.calendar.controller;

import com.webproject.kangneng_back.calendar.entity.Event;
import com.webproject.kangneng_back.calendar.service.CalendarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calendar")
public class CalendarController {

    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @PostMapping("/save")
    public ResponseEntity<Event> saveEvent(@RequestBody String jsonString) {
        try {
            Event savedEvent = calendarService.saveEvent(jsonString);
            return ResponseEntity.ok(savedEvent);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents() {
        try {
            List<Event> events = calendarService.getAllEvents();
            return ResponseEntity.ok(events);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}
