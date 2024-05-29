<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP연습</title>	
</head>
<body>
<h3>
1. JSP 주석
<!-- HTML 주석: 화면에는 안보이고 소스보기에서는 보인다. -->
<%-- JSP 주석: 화면과 소스보기 둘다에서 안보인다. --%>
</h3>
<hr>
<%!
/* 선언태그: 멤버변수나 메서드 선언시 사용 */

String[] members = {"홍길동", "신사임당", "강감찬", "이순신"};
int num1 = 10;

int calc(int num2) {
	return num1 + num2;
}
%>
<h3>2. calc(10) 메서드 결과</h3>
<%=calc(10) %>
<hr>
<h3>3. include</h3>
<%@ include file='../hello.jsp' %>
<hr>
<h3>4. 스크립트립 (모든 Java 코드)</h3>
<ul>
<% 
	for(String name: members) {
%>
	<li><%=name %></li>
<%
	}
%>	
</ul>
</body>
</html>