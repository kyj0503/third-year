import React, { useState, useEffect } from "react";

function Calendar() {
    const [events, setEvents] = useState([]); // DB에서 가져온 이벤트 목록
    const [jsonResponse, setJsonResponse] = useState(""); // OpenAI에서 받은 JSON 데이터
    const [error, setError] = useState(null); // 에러 상태

    // API로 DB 이벤트 데이터를 가져오는 함수
    const fetchEvents = async () => {
        try {
            const res = await fetch("http://43.201.217.228:8080/api/calendar/events");
            if (!res.ok) {
                throw new Error(`HTTP error! status: ${res.status}`);
            }
            const data = await res.json();
            setEvents(data); // 이벤트 상태 업데이트
        } catch (err) {
            console.error("Error fetching events:", err);
            setError(err.message);
        }
    };

    // OpenAI로부터 받은 JSON 데이터를 DB에 저장하는 함수
    const saveToDatabase = async () => {
        try {
            const res = await fetch("http://localhost:8080/api/calendar/save", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: jsonResponse, // JSON 데이터를 그대로 전송
            });
            if (!res.ok) {
                throw new Error(`HTTP error! status: ${res.status}`);
            }

            // 저장 후 새로 데이터를 가져옴
            fetchEvents();
        } catch (err) {
            console.error("Error saving to database:", err);
            setError(err.message);
        }
    };

    // 컴포넌트가 마운트될 때 DB 데이터 가져오기
    useEffect(() => {
        fetchEvents();
    }, []);

    return (
        <div>
            <h2>Event Calendar</h2>
            <textarea
                rows="10"
                cols="50"
                value={jsonResponse}
                onChange={(e) => setJsonResponse(e.target.value)}
                placeholder="Paste JSON data here"
            />
            <button onClick={saveToDatabase}>Save to Database</button>
            {error && <p style={{ color: "red" }}>Error: {error}</p>}
            <h3>Events from Database</h3>
            {events.length > 0 ? (
                <table border="1" style={{ width: "100%" }}>
                    <thead>
                        <tr>
                            <th>Title</th>
                            <th>Description</th>
                            <th>Location</th>
                            <th>Start Time</th>
                            <th>End Time</th>
                            <th>Priority</th>
                            <th>Attendees</th>
                        </tr>
                    </thead>
                    <tbody>
                        {events.map((event) => (
                            <tr key={event.id}>
                                <td>{event.title}</td>
                                <td>{event.description || "N/A"}</td>
                                <td>{event.location || "N/A"}</td>
                                <td>{new Date(event.startTime).toLocaleString()}</td>
                                <td>{new Date(event.endTime).toLocaleString()}</td>
                                <td>{event.priority}</td>
                                <td>
                                    {event.attendees &&
                                        event.attendees.map((attendee) => attendee.name).join(", ")}
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            ) : (
                <p>No events found.</p>
            )}
        </div>
    );
}

export default Calendar;
