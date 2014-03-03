<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<form class="pure-form pure-form-aligned" action="" method="post">
	<c:if test="${errors != null && !errors.isEmpty()}">
		<ul>
			<li>IDかパスワードが異なります。</li>
		</ul>
	</c:if>
	<div class="pure-control-group">
		<label for="id">ID</label> <input id="id" type="text" name="id"
			placeholder="ID" value="${fn:escapeXml(id)}" />
	</div>
	<div class="pure-control-group">
		<label for="password">パスワード</label> <input id="password"
			type="password" name="password" placeholder="パスワード"
			value="${fn:escapeXml(password)}" />
	</div>
	<div class="pure-controls">
		<button class="pure-button pure-button-primary" type="submit">送信</button>
	</div>
	<input type="hidden" name="redirectUrl"
		value="${fn:escapeXml(redirectUrl)}" /> <input type="hidden"
		name="csrf" value="${fn:escapeXml(csrfToken)}" />
</form>