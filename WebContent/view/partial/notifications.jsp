<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<table class="pure-table pure-table-bordered">
	<thead>
		<tr>
			<th>ID</th>
			<th>メンバーID</th>
			<th>メッセージ</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${notificationsList}" var="notifications">
			<tr>
				<td><a href="notification?id=${fn:escapeXml(notifications.id)}">${fn:escapeXml(notifications.id)}</a></td>
				<td><a href="member?id=${fn:escapeXml(notifications.memberId)}">${fn:escapeXml(notifications.memberId)}</a></td>
				<td>${fn:escapeXml(notifications.message)}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>