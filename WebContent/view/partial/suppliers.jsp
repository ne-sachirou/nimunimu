<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div>
	<a class="pure-button" href="supplier?new=true">新規</a>
</div>
<table class="pure-table pure-table-bordered">
	<thead>
		<tr>
			<th>ID</th>
			<th>仕入先名</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${suppliers}" var="supplier">
			<tr>
				<td><a href="supplier?id=${fn:escapeXml(supplier.id)}">${fn:escapeXml(supplier.id)}</a></td>
				<td>${fn:escapeXml(supplier.name)}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
