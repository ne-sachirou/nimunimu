<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<title>Admin Login</title>
<form action="" method="post">
	<c:if test="${errors != null && !errors.isEmpty()}">
		<ul>
			<li>IDかパスワードが異なります。</li>
		</ul>
	</c:if>
	<div>
		<div>id</div>
		<div>
			<input type="text" name="id" value="${fn:escapeXml(id)}" />
		</div>
	</div>
	<div>
		<div>password</div>
		<div>
			<input type="password" name="password"
				value="${fn:escapeXml(password)}" />
		</div>
	</div>
	<button type="submit">送信</button>
	<input type="hidden" name="redirect"
		value="${fn:escapeXml(redirectUrl)}" /> <input type="hidden"
		name="csrf" value="${fn:escapeXml(csrfToken)}" />
</form>