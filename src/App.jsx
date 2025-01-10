import React from 'react';
import FetchMessage from './pages/FetchMessage';
import SendMessage from './pages/SendMessage';
import AskQuestion from './pages/AskQuestion'; // Import 추가 확인

function App() {
    return (
        <div>
            <h1>React & Spring Boot Communication</h1>
            <AskQuestion /> {/* AskQuestion 컴포넌트 추가 */}
            <FetchMessage />
            <SendMessage />

        </div>
    );
}

export default App;
