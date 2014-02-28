<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8" />
<title>${fn:escapeXml(title)}|nimunimu</title>
<script src="${baseUri}/resource/bower_components/q/q.js"></script>
</head>
<body>
	<div class="container">
		<header class="header">
			<h1 class="title">${fn:escapeXml(title)}</h1>
		</header>
		<section class="main">
			<jsp:include page="${partial}"></jsp:include>
		</section>
		<footer class="footer"></footer>
	</div>
</body>
</html>