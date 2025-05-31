package com.webproject.kangneng_back.calendar.repository;

import com.webproject.kangneng_back.calendar.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
