<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8" />
<title>${fn:escapeXml(title)}|nimunimu</title>
<link rel="stylesheet" href="${baseUri}/resource/style.css" />
<script src="${baseUri}/resource/bower_components/q/q.js"></script>
<script>
	// ES6 Array.from
	if (!Array.from) {
		Array.from = function(list) {
			return [].slice.call(list);
		};
	}

	// ES6 Promise http://c4se.hatenablog.com/entry/2014/01/30/023352
	if (!window.Promise) {
		window.Promise = Q.Promise || Q.promise;
		(function() {
			'use strict';
			var i = 0, iz = 0, keys = Object.keys(Q), key;

			for (i = 0, iz = keys.length; i < iz; ++i) {
				key = keys[i];
				window.Promise[key] = Q[key];
			}
		}());
	}
</script>
</head>
<body>
	<div class="container">
		<header class="header">
			<div
				class="pure-menu pure-menu-open pure-menu-horizontal pure-menu-fixed home-menu">
				<a class="pure-menu-heading" href="${baseUri}">nimunimu</a>
				<ul>
					<li class="pure-menu-selected"><a href="${baseUri}">メニュー</a></li>
					<li><a href="${baseUri}/notifications">おしらせ</a></li>
					<li><a href="${baseUri}/admin/member?id=${loginMember.id}">アカウント設定</a></li>
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
	</div>
	<footer class="footer">
		<div>
			<a href="${baseUri}/">nimunimuメニュー</a>
		</div>
	</footer>
</body>
</html>