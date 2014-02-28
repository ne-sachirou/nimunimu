<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div>
	<a class="pure-button" href="member?new=true">新規</a>
</div>
<table>
	<tr>
		<th>ID</th>
		<th>名前</th>
		<th>権限</th>
	</tr>
	<c:forEach items="${members}" var="member">
		<tr>
			<td><a href="member?id=${fn:escapeXml(member.id)}">${fn:escapeXml(member.id)}</a></td>
			<td>${member.name}</td>
			<td>${member.authority}</td>
		</tr>
	</c:forEach>
</table>