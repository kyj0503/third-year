<%-- basePath.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String basePath = request.getContextPath() + "/jwbook/miniProject/";
    request.setAttribute("basePath", basePath);
%>
