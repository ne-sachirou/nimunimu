<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="tk.c4se.halt.ih31.nimunimu.model.*"%>
<%
	Member currentMember = (Member) request
			.getAttribute("currentMember");
	String menuJspPath = "";
	switch (currentMember.getAuthority()) {
	case ADMIN:
		menuJspPath = "/resource/partial/admin/index.jsp";
		break;
	case SALES:
		menuJspPath = "/resource/partial/sales/index.jsp";
		break;
	case SALES_MANAGER:
		menuJspPath = "/resource/partial/sales_manager/index.jsp";
		break;
	case STORE:
		menuJspPath = "/resource/partial/store/index.jsp";
		break;
	case STORE_MANAGER:
		menuJspPath = "/resource/partial/store_manager/index.jsp";
		break;
	case ACCOUNTING:
		menuJspPath = "/resource/partial/accounting/index.jsp";
		break;
	}
%>
<title>index</title>
<jsp:include page="<%=menuJspPath%>"></jsp:include>