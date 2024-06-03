<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*, miniProject.UserDAO, miniProject.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
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
                <li><a href="userList.jsp">회원목록</a></li>
            </ul>
        </nav>
    </div>
</header>

<div class="container">
    <h2>회원 목록</h2>
    <table>
        <tr>
            <th>아이디</th>
            <th>이름</th>
            <th>게임1 기록</th>
            <th>게임2 기록</th>
            <th>게임3 기록</th>
        </tr>
        <%
            UserDAO userDao = new UserDAO();
            List<User> users = userDao.getAll();
            for (User user : users) {
        %>
        <tr>
            <td><%= user.getId() %></td>
            <td><%= user.getName() %></td>
            <td><%= user.getGame1hp() %></td>
            <td><%= user.getGame2hp() %></td>
            <td><%= user.getGame3hp() %></td>
        </tr>
        <%
            }
        %>
    </table>
</div>
</body>
</html>
