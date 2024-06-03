<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="miniProject.User" %>

<%
    User loggedInUser = (User) session.getAttribute("loggedInUser");
    if (loggedInUser == null) {
%>
    <script>
        alert("로그인이 필요합니다. 로그인 페이지로 이동합니다.");
        window.location.href = "startPage.jsp"; // 로그인 페이지로 리디렉션
    </script>
<%
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gomoku Game</title>
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

        .board {
            display: grid;
            grid-template-columns: repeat(15, 20px);
            grid-template-rows: repeat(15, 20px);
            gap: 1px;
            justify-content: center;
            margin: 0 auto 20px auto;
        }

        .omocpan {
            width: 20px;
            height: 20px;
            border: 1px solid #000;
            background-color: #f0f0f0;
        }

        .black {
            background-color: #000;
            border-radius: 50%;
        }

        .white {
            background-color: #fff;
            border-radius: 50%;
        }

        .footer {
            display: flex;
            justify-content: space-around;
            margin-top: 20px;
            flex-wrap: wrap;
        }

        .footer button, .footer form button, .footer a.button {
            display: inline-block;
            text-decoration: none;
            color: white;
            background-color: #007BFF;
            padding: 15px 30px;
            font-size: 1em;
            border-radius: 8px;
            transition: background-color 0.3s ease;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            cursor: pointer;
            border: none;
            text-align: center;
            margin: 5px;
        }

        .footer button:hover, .footer form button:hover, .footer a.button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            ★ Gomoku Game ★
        </div>
        <div id="board" class="board">
            <c:forEach var="row" begin="0" end="14">
                <c:forEach var="col" begin="0" end="14">
                    <div class="omocpan" id="omocpan_${row}_${col}" onclick="placeStone(${row}, ${col})"></div>
                </c:forEach>
            </c:forEach>
        </div>
        <form id="resultForm" action="/jwbook/gomokuControl?action=playGomoku" method="post">
            <input type="hidden" id="player1Win" name="player1Win" value="0">
            <input type="hidden" id="player1Lose" name="player1Lose" value="0">
            <input type="hidden" id="player2Win" name="player2Win" value="0">
            <input type="hidden" id="player2Lose" name="player2Lose" value="0">
        </form>
        <div class="footer">
            <a href="startPage.jsp" class="button">홈</a>
            <a href="index.jsp" class="button">게임 선택</a>
        
            <button id="btnAgain" onclick="resetBoard()">초기화</button>
            <form action="/jwbook/gomokuControl?action=playGomoku" method="post">
                <input type="hidden" id="player1WinForm" name="player1Win" value="0">
                <input type="hidden" id="player1LoseForm" name="player1Lose" value="0">
                <input type="hidden" id="player2WinForm" name="player2Win" value="0">
                <input type="hidden" id="player2LoseForm" name="player2Lose" value="0">
                <button type="submit">결과 전송</button>
                
            </form>

        </div>
    </div>

    <script>
        var currentPlayer = "black"; // 흑돌부터 시작
        const SIZE = 15;
        var board = [];
        var gameEnded = false; // 게임 종료 여부를 나타내는 변수

        function initializeBoard() {
            board = [];
            gameEnded = false; // 게임 종료 상태 초기화
            for (let i = 0; i < SIZE; i++) {
                board[i] = [];
                for (let j = 0; j < SIZE; j++) {
                    board[i][j] = null;
                }
            }
        }

        function placeStone(row, col) {
            if (gameEnded) return; // 게임이 종료되면 돌을 놓지 않음
            var omocpan = document.getElementById("omocpan_" + row + "_" + col);
            if (!board[row][col]) {
                board[row][col] = currentPlayer;
                if (currentPlayer === "black") {
                    omocpan.classList.add("black");
                    if (checkWin(row, col, "black")) {
                        alert("플레이어 1 (흑돌) 승리!");
                        document.getElementById('player1Win').value = 1;
                        document.getElementById('player2Lose').value = 1;
                        document.getElementById('resultForm').submit(); // 결과를 전송
                        gameEnded = true; // 게임 종료 상태로 설정
                    }
                    currentPlayer = "white"; // 플레이어 교체
                } else {
                    omocpan.classList.add("white");
                    if (checkWin(row, col, "white")) {
                        alert("플레이어 2 (백돌) 승리!");
                        document.getElementById('player2Win').value = 1;
                        document.getElementById('player1Lose').value = 1;
                        document.getElementById('resultForm').submit(); // 결과를 전송
                        gameEnded = true; // 게임 종료 상태로 설정
                    }
                    currentPlayer = "black"; // 플레이어 교체
                }
            }
        }

        function checkWin(i, j, color) {
            return (
                checkDirection(i, j, color, 1, 0) ||  // 가로 체크
                checkDirection(i, j, color, 0, 1) ||  // 세로 체크
                checkDirection(i, j, color, 1, 1) ||  // 대각선 체크
                checkDirection(i, j, color, 1, -1)  // 반대편 대각선 체크
            );
        }

        function checkDirection(row, col, color, dRow, dCol) {
            let count = 1;
            for (let i = 1; i <= 4; i++) {
                if (isValid(row + i * dRow, col + i * dCol) && board[row + i * dRow][col + i * dCol] === color) {
                    count++;
                } else {
                    break;
                }
            }
            for (let i = 1; i <= 4; i++) {
                if (isValid(row - i * dRow, col - i * dCol) && board[row - i * dRow][col - i * dCol] === color) {
                    count++;
                } else {
                    break;
                }
            }
            return count >= 5;
        }

        function isValid(row, col) {
            return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
        }

        function resetBoard() {
            initializeBoard();
            currentPlayer = "black"; // 흑돌부터 시작
            const cells = document.querySelectorAll('.omocpan');
            cells.forEach(cell => {
                cell.classList.remove('black', 'white');
            });
        }

        window.onload = () => {
            initializeBoard();
        };
    </script>
</body>
</html>

