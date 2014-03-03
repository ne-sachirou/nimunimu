<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<form class="pure-form pure-form-aligned" action="" method="post">
	<c:if test="${errors != null && !errors.isEmpty()}">
		<ul>
		</ul>
	</c:if>
	<div class="pure-control-group">
		<label for="place">在庫位置</label> <input id="place" type="text"
			name="place" placeholder="在庫位置" value="${fn:escapeXml(place)}"
			required />
	</div>
	<div class="pure-control-group">
		<label for="goods_id">商品ID</label> <input id="goods_id" type="text"
			name="goods_id" placeholder="商品ID" value="${fn:escapeXml(goodsId)}"
			required />
	</div>
	<div class="pure-control-group">
		<label for="goods_number">商品個数</label> <input id="goods_number"
			type="number" name="goods_number" placeholder="商品個数"
			value="${fn:escapeXml(goodsNumber)}" required />
	</div>
	<div class="pure-controls">
		<button class="pure-button pure-button-primary" type="submit">出庫する</button>
	</div>
	<input type="hidden" name="csrf" value="${fn:escapeXml(csrfToken)}" />
</form>
