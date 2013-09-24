<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="tk.c4se.halt.ih31.nimunimu.model.*"%>
<%
	Member currentMember = (Member) request
			.getAttribute("currentMember");
	String menuJspPath = "";
	switch (currentMember.getAuthority()) {
	case ADMIN:
		menuJspPath = "/jsp/admin/index.jsp";
		break;
	case SALES:
		menuJspPath = "/jsp/sales/index.jsp";
		break;
	case SALES_MANAGER:
		menuJspPath = "/jsp/sales_manager/index.jsp";
		break;
	case STORE:
		menuJspPath = "/jsp/store/index.jsp";
		break;
	case STORE_MANAGER:
		menuJspPath = "/jsp/store_manager/index.jsp";
		break;
	case ACCOUNTING:
		menuJspPath = "/jsp/accountion/index.jsp";
		break;
	}
%>
<title>index</title>
<jsp:include page="<%=menuJspPath%>"></jsp:include>