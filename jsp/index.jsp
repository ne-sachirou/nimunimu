<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="tk.c4se.halt.ih31.nimunimu.model.*"%>
<%
	Member currentMember = (Member) request
			.getAttribute("currentMenber");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>index</title>
</head>
<body>
	<%=currentMember == null ? "null" : currentMember.toString()%>
</body>
</html>