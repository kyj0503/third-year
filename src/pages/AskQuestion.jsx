import React, { useState } from 'react';

function AskQuestion() {
    const [question, setQuestion] = useState('');
    const [answer, setAnswer] = useState('');

    const askOpenAI = async () => {
        try {
            const res = await fetch('http://192.168.195.166:8080/api/openai/ask', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ prompt: question }), // JSON 형식으로 데이터 전송
            });

            if (!res.ok) {
                throw new Error(`HTTP error! status: ${res.status}`);
            }

            // 응답을 JSON 형식으로 파싱
            const data = await res.json();

            // OpenAI 응답에서 content 추출
            const content = data?.choices?.[0]?.message?.content || "No response content available.";
            setAnswer(content); // content 저장
        } catch (error) {
            console.error("Error asking question:", error);
            setAnswer("Failed to get a response.");
        }
    };

    return (
        <div>
            <h2>Ask OpenAI</h2>
            <input
                type="text"
                value={question}
                onChange={(e) => setQuestion(e.target.value)}
                placeholder="Enter your question"
            />
            <button onClick={askOpenAI}>Ask</button>
            <p>Answer: {answer}</p>
        </div>
    );
}

export default AskQuestion;
