<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="miniProject.User" %>
<%
    User loggedInUser = (User) session.getAttribute("loggedInUser");
    int game1hp = loggedInUser != null ? loggedInUser.getGame1hp() : 0;
%>
<!DOCTYPE html>
<html>
<head>
    <title>미니게임</title>
    <style>
        body {
            background-color: #d6cfff;
            font-family: 'Arial', sans-serif;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            padding: 20px;
        }

        .container {
            background-color: #fff;
            border-radius: 15px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
            padding: 20px;
            text-align: center;
            width: 600px;
        }

        .header {
            font-size: 2em;
            margin-bottom: 20px;
        }

        .choices {
            display: flex;
            justify-content: space-around;
            align-items: center;
            margin-bottom: 20px;
        }

        .choice {
            text-align: center;
        }

        .choice img {
            width: 100px;
            height: 100px;
            cursor: pointer;
        }

        .vs {
            font-size: 2em;
            margin: 0 20px;
        }

        .selection img {
            width: 150px;
            height: 150px;
        }

        .footer {
            display: flex;
            justify-content: space-around;
            margin-top: 20px;
        }

        .footer img {
            width: 80px;
            height: 80px;
            cursor: pointer;
            transition: transform 0.3s ease;
        }

        .footer img:hover {
            transform: scale(1.1);
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            ★ 가위 바위 보 게임 ★
        </div>
        <div class="choices">
            <div class="choice">
                <h2>YOU</h2>
                <img id="yourChoice" src="${pageContext.request.contextPath}/img/바위.png" alt="Your choice">
                <p id="yourChoiceText">Rock</p>
                <p>HP: <%= game1hp %></p>
            </div>
            <div class="vs">VS</div>
            <div class="choice">
                <h2>COMPUTER</h2>
                <img id="computerChoice" src="${pageContext.request.contextPath}/img/바위.png" alt="Computer's choice">
                <p id="computerChoiceText">Rock</p>
            </div>
        </div>
        <div class="footer">
			<form action="/jwbook/userControl?action=play" method="post" style="margin: 0;">
			    <input type="hidden" name="move" value="ROCK">
			    <button type="submit" style="border: none; background: none; padding: 0;">
			        <img src="${pageContext.request.contextPath}/img/바위.png" alt="Rock">
			    </button>
			</form>
			<form action="/jwbook/userControl?action=play" method="post" style="margin: 0;">
			    <input type="hidden" name="move" value="PAPER">
			    <button type="submit" style="border: none; background: none; padding: 0;">
			        <img src="${pageContext.request.contextPath}/img/보.png" alt="Paper">
			    </button>
			</form>
			<form action="/jwbook/userControl?action=play" method="post" style="margin: 0;">
			    <input type="hidden" name="move" value="SCISSORS">
			    <button type="submit" style="border: none; background: none; padding: 0;">
			        <img src="${pageContext.request.contextPath}/img/가위.png" alt="Scissors">
			    </button>
			</form>
        </div>
    </div>

    <script>
        // 이미지와 텍스트를 매핑
        const moves = ['ROCK', 'PAPER', 'SCISSORS'];
        const moveImages = {
            'ROCK': '${pageContext.request.contextPath}/img/바위.png',
            'PAPER': '${pageContext.request.contextPath}/img/보.png',
            'SCISSORS': '${pageContext.request.contextPath}/img/가위.png'
        };
        const moveTexts = {
            'ROCK': 'Rock',
            'PAPER': 'Paper',
            'SCISSORS': 'Scissors'
        };

        function getRandomMove() {
            return moves[Math.floor(Math.random() * moves.length)];
        }

        function updateChoices() {
            const yourMove = getRandomMove();
            const computerMove = getRandomMove();

            document.getElementById('yourChoice').src = moveImages[yourMove];
            document.getElementById('yourChoiceText').innerText = moveTexts[yourMove];

            document.getElementById('computerChoice').src = moveImages[computerMove];
            document.getElementById('computerChoiceText').innerText = moveTexts[computerMove];
        }

        // 일정 시간 간격으로 이미지 변경
        setInterval(updateChoices, 100);
    </script>
</body>
</html>
 