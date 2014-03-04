<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<table class="pure-table pure-table-bordered">
	<thead>
		<tr>
			<th>ID</th>
			<th>顧客</th>
			<th>見積依頼書</th>
			<th>注文書</th>
			<th>担当社員</th>
			<th>状態</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${orders}" var="order">
			<tr>
				<td>${fn:escapeXml(order.id)}</td>
				<td><a
					href="${baseUri}/customer?id=${fn:escapeXml(order.customerId)}">${fn:escapeXml(order.customerId)}</a></td>
				<td><a
					href="quotation_request_sheet?id=${fn:escapeXml(order.quotationRequestSheetId)}">${fn:escapeXml(order.quotationRequestSheetId)}</a></td>
				<td><a
					href="customer_order_sheet?id=${fn:escapeXml(order.customerOrderSheetId)}">${fn:escapeXml(order.customerOrderSheetId)}</a></td>
				<td><a
					href="${baseUri}/admin/member?id=${fn:escapeXml(order.memberId)}">${fn:escapeXml(order.memberId)}</a></td>
				<td>${fn:escapeXml(order.status)}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
