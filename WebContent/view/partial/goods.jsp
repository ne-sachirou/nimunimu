<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div>
	<a class="pure-button" href="good?new=true">新規</a>
</div>
<table class="pure-table pure-table-bordered">
	<thead>
		<tr>
			<th>ID</th>
			<th>商品名</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${goods}" var="good">
			<tr>
				<td><a href="good?id=${fn:escapeXml(good.id)}">${fn:escapeXml(good.id)}</a></td>
				<td>${fn:escapeXml(good.name)}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>