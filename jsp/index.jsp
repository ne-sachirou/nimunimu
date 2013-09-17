<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="tk.c4se.halt.ih31.nimunimu.model.*"%>
<%
	Member currentMember = (Member) request
			.getAttribute("currentMenber");
%>
<title>index</title>
<%=currentMember == null ? "null" : currentMember.toString()%>