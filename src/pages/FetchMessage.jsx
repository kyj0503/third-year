import React, { useState } from 'react';

function FetchMessage() {
    const [response, setResponse] = useState('');

    const fetchMessage = async () => {
        try {
            const res = await fetch('http://43.201.217.228:8080/api/message'); // Spring Boot API URL
            if (!res.ok) {
                throw new Error(`HTTP error! status: ${res.status}`);
            }
            const data = await res.text();
            setResponse(data);
        } catch (error) {
            console.error("Error fetching the message:", error);
        }
    };

    return (
        <div>
            <h2>Fetch Message</h2>
            <button onClick={fetchMessage}>Fetch Message</button>
            <p>Response: {response}</p>
        </div>
    );
}

export default FetchMessage;
