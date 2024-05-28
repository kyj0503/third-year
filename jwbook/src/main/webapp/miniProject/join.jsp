<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
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
</style>
<script>
    function validateForm() {
        var password = document.forms["registerForm"]["password"].value;
        if (password == "") {
            alert("비밀번호를 입력하세요.");
            return false;
        }
    }
</script>
</head>
<body>
<header>
    <div class="container">
        <div id="branding">
            <h1>학생정보 시스템</h1>
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
    <form name="registerForm" action="/jwbook/userControl?action=join" method="post" onsubmit="return validateForm()">
        <label>아이디</label>
        <input type="text" name="id"><br>
        <label>비밀번호</label>
        <input type="password" name="password"><br>
        <label>이름</label>
        <input type="text" name="name"><br>
        
        <button type="submit">가입</button> 
    </form>
</div>

</body>
</html>
