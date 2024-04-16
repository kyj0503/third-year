<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카운터</title>
</head>
<body>

<%!
	 int count = 0;
%>
<%!
	count += 1;
// 지역변수 (doGet이 호출될 때마다 초기화)
%>

<img alt='1.jpg' src="../img/num/<%=count %>.png" width="50"/>

</body>
</html>