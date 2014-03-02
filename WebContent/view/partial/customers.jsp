<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div>
	<a class="pure-button" href="customer?new=true">新規</a>
</div>
<table class="pure-table pure-table-bordered">
	<thead>
		<tr>
			<th>ID</th>
			<th>顧客名</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${customers}" var="customer">
			<tr>
				<td><a href="customer?id=${fn:escapeXml(customer.id)}">${fn:escapeXml(customer.id)}</a></td>
				<td>${fn:escapeXml(customer.name)}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
