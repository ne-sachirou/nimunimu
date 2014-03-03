<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div>
	<a class="pure-button" href="goods?new=true">新規</a>
</div>
<table class="pure-table pure-table-bordered">
	<thead>
		<tr>
			<th>ID</th>
			<th>商品名</th>
			<th>商品カテゴリーID</th>
			<th>仕入先ID</th>
			<th>単価</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${goodsList}" var="goods">
			<tr>
				<td><a href="goods?id=${fn:escapeXml(goods.id)}">${fn:escapeXml(goods.id)}</a></td>
				<td>${fn:escapeXml(goods.name)}</td>
				<td><a
					href="goods_category?id=${fn:escapeXml(goods.goodsCategoryId)}">${fn:escapeXml(goods.goodsCategoryId)}</a></td>
				<td><a href="supplier?id=${fn:escapeXml(goods.supplierId)}">${fn:escapeXml(goods.supplierId)}</a></td>
				<td>${fn:escapeXml(goods.price)}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>