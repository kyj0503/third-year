import React, { useState } from 'react';

function SendMessage() {
    const [message, setMessage] = useState('');
    const [response, setResponse] = useState('');

    const sendMessage = async () => {
        try {
            console.log("Sending message:", message); // 요청 데이터 확인
            const res = await fetch('http://localhost:8080/api/message', { // Spring Boot 서버 URL
                method: 'POST',
                headers: { 
                    'Content-Type': 'application/json', 
                    'Accept': 'application/json' // JSON 응답 기대
                },
                body: JSON.stringify({ content: message }), // 전송할 데이터
            });
    
            console.log("Response status:", res.status); // 응답 상태 코드 확인
            if (!res.ok) {
                const errorText = await res.text();
                console.error("Server error response:", errorText);
                throw new Error(`HTTP error! status: ${res.status}`);
            }
    
            const data = await res.text();
            setResponse(data);
            console.log("Server response:", data); // 서버 응답 확인
            setMessage(''); // 메시지 전송 후 입력 초기화
        } catch (error) {
            console.error("Error sending the message:", error);
            setResponse("Error sending the message. Check console for details.");
        }
    };
    
    return (
        <div>
            <h2>Send Message</h2>
            <input
                type="text"
                value={message}
                onChange={(e) => setMessage(e.target.value)}
                placeholder="Enter your message"
            />
            <button onClick={sendMessage}>Send Message</button>
            <p>Response: {response}</p>
        </div>
    );
}

export default SendMessage;
