<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<table class="pure-table pure-table-bordered">
	<thead>
		<tr>
			<th>ID</th>
			<th>仕入先</th>
			<th>発注書</th>
			<th>状態</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${orders}" var="order">
			<tr>
				<td>${fn:escapeXml(order.id)}</td>
				<td><a href="${baseUri}/supplier?id=${order.supplierId}">${order.supplierId}</a></td>
				<td><a href="our_order_sheet?id=${order.ourOrderSheetId}">${order.ourOrderSheetId}</a></td>
				<td>${order.status}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
