<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Select Game</title>
    <style>
        body {
            background-image: linear-gradient(to bottom right, #4b6cb7, #182848);
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
            margin-bottom: 30px;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
        }

        .home-button {
            position: absolute;
            top: 20px;
            right: 20px;
            text-decoration: none;
            color: white;
            font-size: 1.2em;
            padding: 10px 20px;
            background-color: #007BFF;
            border-radius: 8px;
            transition: background-color 0.3s ease;
        }

        .home-button:hover {
            background-color: #0056b3;
        }

        a {
            display: inline-block;
            text-decoration: none;
            color: white;
            background-color: #007BFF;
            padding: 15px 30px;
            font-size: 1.5em;
            border-radius: 8px;
            transition: background-color 0.3s ease;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            margin: 10px; /* 추가: 버튼 사이 간격을 위해 여백 추가 */
        }

        a:hover {
            background-color: #0056b3;
        }

        .container {
            background-color: rgba(255, 255, 255, 0.8);
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            text-align: center;
        }
    </style>
</head>
<body>
    <a class="home-button" href="startPage.jsp">로그아웃</a>
    <div class="container">
        <h1>게임을 시작하세요!</h1>
        <a href="GameView.jsp">가위바위보 게임</a>
        <a href="Gomoku.jsp">오목 게임</a>
    </div>
</body>
</html>
