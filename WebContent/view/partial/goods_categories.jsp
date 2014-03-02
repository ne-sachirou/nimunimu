<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div>
	<a class="pure-button" href="goods_category?new=true">新規</a>
</div>
<table class="pure-table pure-table-bordered">
	<thead>
		<tr>
			<th>ID</th>
			<th>商品category名</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${goodsCategories}" var="goodsCategory">
			<tr>
				<td><a
					href="goods_category?id=${fn:escapeXml(goodsCategory.id)}">${fn:escapeXml(goodsCategory.id)}</a></td>
				<td>${goodsCategory.name}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>