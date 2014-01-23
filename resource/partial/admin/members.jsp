<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<table>
	<tr>
		<th>ID</th>
		<th>権限</th>
		<th>is password reseted</th>
		<th>作成日時</th>
		<th>更新日時</th>
	</tr>
	<c:forEach items="${members}" var="member">
		<tr>
			<td><a href="member?id=${fn:escapeXml(member.id)}">${fn:escapeXml(member.id)}</a></td>
			<td>${member.authority}</td>
			<td>${member.isPasswordReseted}</td>
			<td>${member.createdAt}</td>
			<td>${member.updatedAt}</td>
		</tr>
	</c:forEach>
</table>