'use client';

import React, { useEffect, useState } from 'react';
import FullCalendar from '@fullcalendar/react';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from "@fullcalendar/timegrid";
import interactionPlugin from "@fullcalendar/interaction";
import { Modal, Button, Form, Row, Col } from 'react-bootstrap';

import './calendar2.css';

export default function Calendar2() {
  // const [events, setEvents] = useState([
    // { title: 'Event 1', start: '2025-01-11T08:00:00', end: '2025-01-11T09:00:00', id: '1' },
    // { title: 'Event 2', start: '2024-01-11T10:00:00', end: '2025-01-11T11:00:00', id: '2' },
  // ]);

  const [events, setEvents] = useState([
  ]); // 이벤트 목
  const [loading, setLoading] = useState(true); // 로딩 
  const [error, setError] = useState(null); // 에러 상태

  const [showModal, setShowModal] = useState(false);
  const [showDetails, setShowDetails] = useState(false);
  const [newEvent, setNewEvent] = useState({
    title: '',
    description: '',
    start: '',
    startTime: '08:00',
    end: '',
    endTime: '09:00',
    id: null
  });
  const [isEditing, setIsEditing] = useState(false);

    const fetchEvents = async () => {
      setLoading(true); // 로딩 상태로 설정
      setError(null); // 이전 에러 초기화
      try {
          const response = await fetch("http://43.201.217.228:8080/api/calendar/events"); // 백엔드 API 호출
          if (!response.ok) {
              throw new Error(`HTTP error! status: ${response.status}`);
          }
      
          const data = await response.json();
      
          // 응답 데이터가 배열이 아닌 경우 처리
          if (!Array.isArray(data)) {
              throw new Error("Invalid data format: Expected an array of events");
          }
      
          // 이벤트 데이터 상태에 저장

          const scaledData = data.reduce((acc, cur) => {
            acc.push({ title: cur.title, description: cur.description, start: cur.startTime, end: cur.endTime, id: cur.id});
            return acc;
          }, []);
          setEvents(scaledData);
      } catch (err) {
          setError(err.message); // 에러 설정
      } finally {
          setLoading(false); // 로딩 완료
      }
  };

  
    // 컴포넌트 마운트 시 데이터 가져오기
    useEffect(() => {
      fetchEvents();
  }, []);

  useEffect(() => {
    console.log('events', events);
  }, [events])

  
    // 이벤트가 없을 때 표시
    if (events.length === 0) return <p>No events found.</p>;
  
  
    // 에러 발생 시 표시
    if (error) return <p style={{ color: "red" }}>Error: {error}</p>;

    // 로딩 중일 때 표시
    if (loading) return <p>Loading events...</p>;

  const handleDateClick = (date) => {
    const clickedDate = new Date(date.date);
    const formattedDate = clickedDate.toISOString().split('T')[0];
   
    setNewEvent({
      title: '',
      description: '',
      start: formattedDate,
      startTime: '08:00',
      end: formattedDate,
      endTime: '09:00',
      id: null
    });
    setIsEditing(false);
    setShowModal(true);
    setShowDetails(false);
  };

  const handleEventClick = (clickInfo) => {
    const event = clickInfo.event;
    const startDate = event.start.toISOString().split('T')[0];
    const startTime = event.start.toTimeString().slice(0, 5);
    const endDate = event.end ? event.end.toISOString().split('T')[0] : startDate;
    const endTime = event.end ? event.end.toTimeString().slice(0, 5) : startTime;

    setNewEvent({
      title: event.title,
      description: event.extendedProps.description || '',
      start: startDate,
      startTime: startTime,
      end: endDate,
      endTime: endTime,
      id: event.id,
    });
    setIsEditing(true);
    setShowModal(true);
    setShowDetails(false);
  };

  const handleSaveEvent = () => {
    const eventToSave = {
      title: newEvent.title,
      description: newEvent.description,
      start: `${newEvent.start}T${newEvent.startTime}:00`,
      end: `${newEvent.end}T${newEvent.endTime}:00`,
      id: isEditing ? newEvent.id : Date.now().toString()
    };

    if (isEditing) {
      setEvents(
        events.map((event) =>
          event.id === newEvent.id ? eventToSave : event
        )
      );
    } else {
      setEvents([...events, eventToSave]);
    }
    setShowModal(false);
  };

  const handleDeleteEvent = () => {
    setEvents(events.filter(event => event.id !== newEvent.id));
    setShowModal(false);
  };

  const handleCancel = () => {
    setShowModal(false);
    setShowDetails(false);
  };

  const handleSelect = (selectInfo) => {
    const startDate = selectInfo.startStr.split('T')[0];
    const endDate = selectInfo.endStr.split('T')[0];
    setNewEvent({
      title: '',
      description: '',
      start: startDate,
      startTime: '08:00',
      end: endDate,
      endTime: '09:00',
      id: null,
    });
    setIsEditing(false);
    setShowModal(true);
    setShowDetails(false);
  };

  const handleEventDrop = (dropInfo) => {
    const movedEvent = dropInfo.event;
    setEvents(events.map(event =>
      event.id === movedEvent.id
        ? { ...event, start: movedEvent.startStr, end: movedEvent.endStr }
        : event
    ));
  };

  const toggleDetails = () => {
    setShowDetails(!showDetails);
  };

  return (
    <div id="calendar-container">
      <FullCalendar
        plugins={[dayGridPlugin, timeGridPlugin, interactionPlugin]}
        initialView="dayGridMonth"
        height="auto"
        headerToolbar={{
          start: 'prev next',
          center: 'title',
          end: 'dayGridMonth timeGridWeek',
        }}
        locale={'ko'}
        events={events}
        dayHeaderFormat={{ weekday: 'short' }}
        dayCellContent={(arg) => arg.date.getDate()}
        dateClick={handleDateClick}
        eventClick={handleEventClick}
        editable={true}
        droppable={true}
        selectable={true}
        select={handleSelect}
        eventDrop={handleEventDrop}
      />
      <Modal show={showModal} onHide={() => setShowModal(false)}>
        <Modal.Header closeButton>
          <Modal.Title>{isEditing ? '일정 수정' : '새 일정 추가'}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group controlId="formEventTitle">
              <Form.Label>제목</Form.Label>
              <Form.Control
                type="text"
                value={newEvent.title}
                onChange={(e) => setNewEvent({ ...newEvent, title: e.target.value })}
              />
            </Form.Group>
           
            <Form.Group controlId="formEventDescription" className="d-flex align-items-center">
              <Form.Label className="me-2">내용</Form.Label>
              <Form.Control
                as="textarea"
                rows={3}
                value={newEvent.description}
                onChange={(e) => setNewEvent({ ...newEvent, description: e.target.value })}
              />
            </Form.Group>

            {showDetails && (
              <Row>
                <Col md={6}>
                  <Form.Group controlId="formEventDates">
                    <Form.Label>날짜</Form.Label>
                    <Row>
                      <Col>
                        <Form.Control
                          type="date"
                          value={newEvent.start}
                          onChange={(e) => setNewEvent({ ...newEvent, start: e.target.value })}
                        />
                      </Col>
                      <Col>
                        <Form.Control
                          type="date"
                          value={newEvent.end}
                          onChange={(e) => setNewEvent({ ...newEvent, end: e.target.value })}
                        />
                      </Col>
                    </Row>
                  </Form.Group>
                </Col>
                <Col md={6}>
                  <Form.Group controlId="formEventTimes">
                    <Form.Label>시간</Form.Label>
                    <Row>
                      <Col>
                        <Form.Control
                          type="time"
                          value={newEvent.startTime}
                          onChange={(e) => setNewEvent({ ...newEvent, startTime: e.target.value })}
                        />
                      </Col>
                      <Col>
                        <Form.Control
                          type="time"
                          value={newEvent.endTime}
                          onChange={(e) => setNewEvent({ ...newEvent, endTime: e.target.value })}
                        />
                      </Col>
                    </Row>
                  </Form.Group>
                </Col>
              </Row>
            )}
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleCancel}>
            취소
          </Button>
          {isEditing && (
            <Button variant="danger" onClick={handleDeleteEvent}>
              삭제
            </Button>
          )}
          <Button variant="primary" onClick={handleSaveEvent}>
            저장
          </Button>
          <Button variant="info" onClick={toggleDetails}>
            {showDetails ? '-' : '+'}
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
}

