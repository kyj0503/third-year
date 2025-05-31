<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="miniProject.UserDAO, miniProject.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Game</title>
<link rel="stylesheet" type="text/css" href="styles.css">
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
    }
    .container {
        width: 80%;
        margin: auto;
        overflow: hidden;
    }
    header {
        background: #50b3a2;
        color: #fff;
        padding-top: 30px;
        min-height: 70px;
        border-bottom: #2980b9 3px solid;
    }
    header a {
        color: #fff;
        text-decoration: none;
        text-transform: uppercase;
        font-size: 16px;
    }
    header ul {
        padding: 0;
        list-style: none;
    }
    header li {
        float: left;
        display: inline;
        padding: 0 20px 0 20px;
    }
    header #branding {
        float: left;
    }
    header #branding h1 {
        margin: 0;
    }
    header nav {
        float: right;
        margin-top: 10px;
    }
    #showcase {
        min-height: 400px;
        background: url('background.jpg') no-repeat 0 -400px;
        text-align: center;
        color: #fff;
    }
    #showcase h1 {
        margin-top: 100px;
        font-size: 55px;
        margin-bottom: 10px;
    }
    form {
        background: #fff;
        padding: 20px;
        margin-top: 30px;
        box-shadow: 0px 0px 10px 0px #000;
    }
    form label {
        display: block;
        margin-bottom: 10px;
    }
    form input[type="text"], form input[type="password"] {
        width: 100%;
        padding: 10px;
        margin-bottom: 10px;
        border: 1px solid #ccc; /* 추가 */
        border-radius: 5px; /* 추가 */
    }
    form button {
        width: 100%;
        padding: 10px;
        background: #50b3a2;
        border: 0;
        color: #fff;
        border-radius: 5px; /* 추가 */
        cursor: pointer; /* 추가 */
    }
    table {
        width: 100%;
        margin-top: 20px;
        border-collapse: collapse;
    }
    table, th, td {
        border: 1px solid #ddd;
    }
    table th, table td {
        padding: 10px;
        text-align: left;
    }
    table th {
        background: #50b3a2;
        color: #fff;
    }
    .btn {
        display: inline-block;
        padding: 10px 20px;
        text-align: center;
        color: #fff;
        background: #2980b9;
        border: none;
        margin-top: 20px;
        cursor: pointer;
        border-radius: 5px; /* 추가 */
    }
</style>
</head>
<body>

<header>
    <div class="container">
        <div id="branding">
            <h1>JSP Game</h1>
        </div>
        <nav>
            <ul>
                <li><a href="startPage.jsp">홈</a></li>
                <li><a href="join.jsp">회원가입</a></li>
                <li><a href="userList.jsp">회원 목록</a></li>
            </ul>
        </nav>
    </div>
</header>

<div class="container">
    <form action="/jwbook/userControl?action=login" method="post">
        <label>아이디</label>
        <input type="text" name="id"><br>
        <label>비밀번호</label>
        <input type="password" name="password"><br>
        <button type="submit">로그인</button> 
    </form>  

    <c:if test="${not empty message}">
        <p>${message}</p>
    </c:if>
</div>

</body>
</html>
