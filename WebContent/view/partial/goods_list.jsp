<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div>
	<a class="pure-button" href="goods?new=true">新規</a>
</div>
<table class="pure-table pure-table-bordered">
	<thead>
		<tr>
			<th>ID</th>
			<th>商品名</th>
			<th>商品カテゴリー</th>
			<th>仕入先</th>
			<th>単価</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${goodsList}" var="goods">
			<tr>
				<td><a href="goods?id=${fn:escapeXml(goods.id)}">${fn:escapeXml(goods.id)}</a></td>
				<td>${fn:escapeXml(goods.name)}</td>
				<td><a
					href="goods_category?id=${fn:escapeXml(goods.goodsCategoryId)}">${fn:escapeXml(goods.goodsCategory.name)}</a></td>
				<td><a href="supplier?id=${fn:escapeXml(goods.supplierId)}">${fn:escapeXml(goods.supplier.name)}</a></td>
				<td style="text-align: right;"><fmt:formatNumber
						value="${fn:escapeXml(goods.price)}" pattern="###,###" /> 円</td>
			</tr>
		</c:forEach>
	</tbody>
</table>