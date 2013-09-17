<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String title = (String) request.getAttribute("title");
	if (title == null)
		title = "nimunimu";
	String partial = (String) request.getAttribute("partial");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8" />
<title><%=title%> | nimunimu</title>
</head>
<body>
	<div class="container">
		<header class="header">
			<hgroup>
				<h1><%=title%></h1>
			</hgroup>
		</header>
		<article class="main">
			<jsp:include page="<%=partial%>"></jsp:include>
		</article>
		<footer class="footer"></footer>
	</div>
</body>
</html>