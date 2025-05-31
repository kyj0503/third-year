package com.webproject.kangneng_back.config;

import com.webproject.kangneng_back.calendar.entity.Attendee;
import com.webproject.kangneng_back.calendar.entity.Event;
import com.webproject.kangneng_back.calendar.repository.EventRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Arrays;

// 캘린더 개발용 더미 데이터
@Configuration
public class CalendarDataInitializer {

    @Bean
    public CommandLineRunner initCalendarData(EventRepository eventRepository) {
        return args -> {
            // 이미 데이터가 있는 경우 초기화하지 않음
            if (eventRepository.count() > 0) {
                return;
            }

            // 더미 데이터 생성
            Event event1 = new Event();
            event1.setTitle("팀 회의");
            event1.setDescription("주간 진행 상황 논의");
            event1.setLocation("서울특별시 강남구");
            event1.setStartTime(LocalDateTime.of(2025, 1, 10, 10, 0));
            event1.setEndTime(LocalDateTime.of(2025, 1, 10, 11, 0));
            event1.setTimeZone("Asia/Seoul");
            event1.setPriority(10);

            Attendee attendee1 = new Attendee();
            attendee1.setEmail("example1@gmail.com");
            attendee1.setEvent(event1);

            Attendee attendee2 = new Attendee();
            attendee2.setEmail("example2@gmail.com");
            attendee2.setEvent(event1);

            event1.setAttendees(Arrays.asList(attendee1, attendee2));

            Event event2 = new Event();
            event2.setTitle("프로젝트 발표 준비");
            event2.setDescription("최종 발표 자료 준비");
            event2.setLocation("서울특별시 중구");
            event2.setStartTime(LocalDateTime.of(2025, 1, 15, 14, 0));
            event2.setEndTime(LocalDateTime.of(2025, 1, 15, 16, 0));
            event2.setTimeZone("Asia/Seoul");
            event2.setPriority(8);

            Attendee attendee3 = new Attendee();
            attendee3.setEmail("example3@gmail.com");
            attendee3.setEvent(event2);

            event2.setAttendees(Arrays.asList(attendee3));

            // 저장
            eventRepository.saveAll(Arrays.asList(event1, event2));

            System.out.println("Dummy calendar data initialized.");
        };
    }
}
