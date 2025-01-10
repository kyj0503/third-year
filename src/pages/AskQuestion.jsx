import React, { useState } from "react";

function AskQuestion() {
    const [naturalLanguage, setNaturalLanguage] = useState('');
    const [jsonResponse, setJsonResponse] = useState('');
    const [error, setError] = useState(null); // 에러 상태 추가

    const convertToJSON = async () => {
        try {
            const res = await fetch('http://43.201.217.228:8080/api/openai/convert', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ prompt: naturalLanguage }), // JSON 형식으로 데이터 전송
            });
    
            if (!res.ok) {
                throw new Error(`HTTP error! status: ${res.status}`);
            }
    
            const data = await res.json(); // OpenAI 응답 데이터를 JSON으로 파싱
            const content = data?.choices?.[0]?.message?.content;
    
            // content가 JSON 문자열일 경우 추가 파싱
            let parsedContent;
            try {
                parsedContent = JSON.parse(content); // JSON 문자열 파싱
            } catch (parseError) {
                throw new Error(`Error parsing content: ${content}`);
            }
    
            setJsonResponse(parsedContent);
            setError(null); // 에러 초기화
        } catch (err) {
            console.error("Error converting to JSON:", err);
            setError(err.message); // 에러 상태 설정
            setJsonResponse(''); // 응답 초기화
        }
    };
    

    return (
        <div>
            <h2>Convert Natural Language to JSON</h2>
            <textarea
                value={naturalLanguage}
                onChange={(e) => setNaturalLanguage(e.target.value)}
                placeholder="Enter your schedule or appointment details"
                rows="5"
                style={{ width: '100%' }}
            />
            <button onClick={convertToJSON}>Convert</button>
            {error && <p style={{ color: 'red' }}>Error: {error}</p>}
            <h3>Converted JSON:</h3>
            <pre style={{ backgroundColor: '#f4f4f4', padding: '10px', borderRadius: '5px' }}>
                {jsonResponse ? JSON.stringify(jsonResponse, null, 2) : 'No response yet.'}
            </pre>
        </div>
    );
}

export default AskQuestion;
