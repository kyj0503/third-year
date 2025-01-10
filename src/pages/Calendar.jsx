import React, { useEffect, useState } from "react";

function Calendar() {
    const [events, setEvents] = useState([]); // 이벤트 목록 저장
    const [loading, setLoading] = useState(true); // 로딩 상태
    const [error, setError] = useState(null); // 에러 상태

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
            setEvents(data);
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

    // 로딩 중일 때 표시
    if (loading) return <p>Loading events...</p>;

    // 에러 발생 시 표시
    if (error) return <p style={{ color: "red" }}>Error: {error}</p>;

    // 이벤트가 없을 때 표시
    if (events.length === 0) return <p>No events found.</p>;

    // 테이블 스타일
    const tableStyle = { width: "100%", borderCollapse: "collapse" };
    const cellStyle = { border: "1px solid black", padding: "8px" };

    return (
        <div>
            <h2>Calendar Events</h2>
            <button onClick={fetchEvents} style={{ marginBottom: "10px" }}>
                Refresh Events
            </button>
            <table style={tableStyle}>
                <thead>
                    <tr>
                        <th style={cellStyle}>Summary</th>
                        <th style={cellStyle}>Description</th>
                        <th style={cellStyle}>Location</th>
                        <th style={cellStyle}>Start Time</th>
                        <th style={cellStyle}>End Time</th>
                        <th style={cellStyle}>Priority</th>
                        <th style={cellStyle}>Attendees</th>
                    </tr>
                </thead>
                <tbody>
                    {events.map((event) => (
                        <tr key={event.id}>
                            <td style={cellStyle}>{event.summary}</td>
                            <td style={cellStyle}>{event.description || "N/A"}</td>
                            <td style={cellStyle}>{event.location || "N/A"}</td>
                            <td style={cellStyle}>
                                {new Date(event.startTime).toLocaleString()}
                            </td>
                            <td style={cellStyle}>
                                {new Date(event.endTime).toLocaleString()}
                            </td>
                            <td style={cellStyle}>{event.priority}</td>
                            <td style={cellStyle}>
                                {event.attendees && event.attendees.length > 0
                                    ? event.attendees.map((attendee) => attendee.email).join(", ")
                                    : "No attendees"}
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default Calendar;
