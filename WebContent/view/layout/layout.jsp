<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8" />
<title>${fn:escapeXml(title)}|nimunimu</title>
<link rel="stylesheet"
	href="${baseUri}/resource/bower_components/pure/pure-min.css" />
<link rel="stylesheet" href="${baseUri}/resource/style.css" />
<script src="${baseUri}/resource/bower_components/q/q.js"></script>
</head>
<body>
	<div class="container">
		<header class="header">
			<div
				class="home-menu pure-menu pure-menu-open pure-menu-horizontal pure-menu-fixed">
				<a class="pure-menu-heading" href="${baseUri}">nimunimu</a>
				<ul>
					<li class="pure-menu-selected"><a href="${baseUri}">Home</a></li>
					<li><a href="${baseUri}/logout">ログアウト</a></li>
				</ul>
			</div>
		</header>
		<h1 class="title">${fn:escapeXml(title)}</h1>
		<div class="main-box">
			<section class="main">
				<jsp:include page="${partial}"></jsp:include>
			</section>
		</div>
		<footer class="footer"></footer>
	</div>
	<footer class="footer">
		<div>
			<a href="${baseUri}/">nimunimuトップ</a>
		</div>
	</footer>
</body>
</html>