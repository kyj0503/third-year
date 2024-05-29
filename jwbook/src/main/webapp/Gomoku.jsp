<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Gomoku Game</title>
	<style>
        .board {
            display: grid;
            grid-template-columns: repeat(15, 20px);
            grid-template-rows: repeat(15, 20px);
            gap: 1px;
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
    </style>
</head>
<body>
	<h1>Gomoku Game</h1>
    <div id="board" class="board">
    	<c:forEach var="row" begin="0" end="14">
    		<c:forEach var="col" begin="0" end="14">
    			<div class="omocpan" id="omocpan_${row}_${col}" onclick="placeStone(${row}, ${col})"></div>
    		</c:forEach>
    	</c:forEach>
    </div>
    <form id="resultForm" action="/jwbook/gomock" method="post">
        <input type="hidden" id="player1Win" name="player1Win" value="0">
        <input type="hidden" id="player1Lose" name="player1Lose" value="0">
        <input type="hidden" id="player2Win" name="player2Win" value="0">
        <input type="hidden" id="player2Lose" name="player2Lose" value="0">
        <button type="submit">결과 전송</button>
    </form>
     <div>
    	<button id='btnAgain' class='btnResult' onclick="resetBoard()">초기화</button>
    </div>
    
    <script>
    var currentPlayer = "black"; // 흑돌부터 시작
    const SIZE = 15;
    var board = [];
    
    function initializeBoard() {
        board = [];
        for (let i = 0; i < SIZE; i++) {
            board[i] = [];
            for (let j = 0; j < SIZE; j++) {
                board[i][j] = null;
            }
        }
    }
    
    function placeStone(row, col, player1, player2) {
    	var omocpan = document.getElementById("omocpan_" + row + "_" + col);
        if (!board[row][col]) {
            board[row][col] = currentPlayer;
            if (currentPlayer === "black") {
                omocpan.classList.add("black");
                if (checkWin(row, col, "black")) {
                    alert("플레이어 1 (흑돌) 승리!");
                    document.getElementById('player1Win').value = parseInt(document.getElementById('player1Win').value) + 1;
                    document.getElementById('player2Lose').value = parseInt(document.getElementById('player2Lose').value) + 1;
                    //document.getElementById('resultForm').submit();
                    resetBoard();
                }
                currentPlayer = "white"; // 플레이어 교체
            } else {
                omocpan.classList.add("white");
                if (checkWin(row, col, "white")) {
                    alert("플레이어 2 (백돌) 승리!");
                    document.getElementById('player2Win').value = parseInt(document.getElementById('player2Win').value) + 1;
                    document.getElementById('player1Lose').value = parseInt(document.getElementById('player1Lose').value) + 1;
                    //document.getElementById('resultForm').submit();
                    resetBoard();
                }
                currentPlayer = "black"; // 플레이어 교체
            }
        }
    }
    
    function checkWin(i, j, color) {
        // 가로, 세로, 대각선 방향에 대해 5개의 연속된 돌이 놓였는지 체크
        // 실제 게임에서는 이 부분의 로직이 더 복잡할 수 있음
        return (
            checkDirection(i, j, color, 1, 0) ||  // 가로 체크
            checkDirection(i, j, color, 0, 1) ||  // 세로 체크
            checkDirection(i, j, color, 1, 1) ||  // 대각선 체크
            checkDirection(i, j, color, 1, -1)  // 반대편 대각선 체크
        );
    }
 	// 여기서 Controller에 플레이어의 정보와 돌의 위치를 전달
    function sendMoveToController(row, col, currentPlayer, player1, player2) {
    	let winner = checkwin(row, col);
    	if (currentPlayer === 'black' && winner) {
    		document.getElementById('player1Win').value = 1;
    		document.getElementById('player2Lose').value = 1;
    		alert(player1 + '가 이겼습니다!');
        } else if (currentPlayer === 'white' && winner) {
            document.getElementById('player1Lose').value = 1;
            document.getElementById('player2Win').value = 1;
            alert(player2 + '가 이겼습니다!');
        }
    	document.getElementById('resultForm').submit();
    }
    
    //아래는 방향함수 2개, 초기화함수
    
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
        var cells = document.getElementsByClassName("omocpan");
        for (var i = 0; i < cells.length; i++) {
            cells[i].classList.remove("black", "white");
        }
    }
  
    window.onload = function() {
        initializeBoard();
    };
    </script>
   
</body>
</html>