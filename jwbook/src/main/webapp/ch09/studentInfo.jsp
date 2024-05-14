<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
  <tr><th>id</th><th>이름</th><th>대학</th><th>생일</th><th>이메일</th></tr>
  <c:forEach items="${students}" var="s">
    <tr>
      <td>${s.id}</td> <td>${s.username}</td> <td>${s.univ}</td>
      <td>${s.birth}</td> <td>${s.email}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>