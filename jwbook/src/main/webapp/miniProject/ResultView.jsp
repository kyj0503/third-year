<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>미니게임결과</title>
    <style>
        body {
            background-image: linear-gradient(to bottom right, #ff9a9e, #fad0c4);
            font-family: 'Arial', sans-serif;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            padding: 20px;
        }

        h1 {
            color: #fff;
            font-size: 3em;
            margin-bottom: 20px;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
        }

        .result-box {
            background-color: rgba(255, 255, 255, 0.9);
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            text-align: center;
        }

        .result-label {
            font-size: 1.2em;
            font-weight: bold;
            margin-bottom: 5px;
            color: #333;
        }

        .result-value {
            font-size: 1.2em;
            color: #555;
            margin-bottom: 10px;
        }

        a {
            display: inline-block;
            text-decoration: none;
            color: white;
            background-color: #007BFF;
            padding: 15px 30px;
            font-size: 1.2em;
            border-radius: 8px;
            transition: background-color 0.3s ease;
            margin-top: 20px;
        }

        a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h1>★ 가위바위보 결과 ★</h1>
    <div class="result-box">
        <p class="result-label">당신의 선택:</p>
        <p class="result-value">${playerMove}</p>
        <p class="result-label">컴퓨터의 선택:</p>
        <p class="result-value">${computerMove}</p>
        <p class="result-label">결과:</p>
        <p class="result-value">${result}</p>
    </div>
    <a href="miniProject/index.jsp">다시 하기</a>
</body>
</html>
