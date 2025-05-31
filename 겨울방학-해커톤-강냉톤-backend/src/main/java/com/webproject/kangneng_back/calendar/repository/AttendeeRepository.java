package com.webproject.kangneng_back.calendar.repository;

import com.webproject.kangneng_back.calendar.entity.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendeeRepository extends JpaRepository<Attendee, Integer> {
}
