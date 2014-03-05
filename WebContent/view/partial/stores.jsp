<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div>
	<a class="pure-button" href="store/store_in">入庫する</a> <a
		class="pure-button" href="store/store_out">出庫する</a>
</div>
<table class="pure-table pure-table-bordered">
	<thead>
		<tr>
			<th>在庫位置</th>
			<th>商品ID</th>
			<th>商品ID</th>
			<th>個数</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${stores}" var="store">
			<tr>
				<td>${fn:escapeXml(store.place)}</td>
				<td><a href="goods?id=${fn:escapeXml(store.goodsId)}">${fn:escapeXml(store.goodsId)}</a></td>
				<td>${fn:escapeXml(store.goods.name)}</td>
				<td>${fn:escapeXml(store.goodsNumber)}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
