<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div id="editable">
	<div>
		<button class="edit pure-button pure-button-primary">編集</button>
		<button class="delete pure-button button-warning">削除</button>
		<button class="save pure-button pure-button-primary">保存</button>
		<button class="cancel pure-button button-warning">編集取り消し</button>
	</div>
	<div class="pure-form pure-form-aligned">
		<div class="pure-control-group">
			<label for="">発注ID</label> <span class="" data-field-name="order_id">${fn:escapeXml(order.id)}</span>
		</div>
		<div class="pure-control-group">
			<label for="">仕入先ID</label> <span class="field"
				data-field-name="order_supplier_id">${fn:escapeXml(order.supplierId)}</span>
		</div>
		<div class="pure-control-group">
			<label for="">担当社員ID</label> <span class="field"
				data-field-name="order_member_id">${fn:escapeXml(order.memberId)}</span>
		</div>
		<div class="pure-control-group">
			<label for="">発注の状態</label> <span class="field"
				data-field-name="order_status">${fn:escapeXml(order.status)}</span>
		</div>

		<div class="pure-control-group">
			<label for="">発注書ID</label> <span class="pk" data-field-name="id">${fn:escapeXml(sheet.id)}</span>
		</div>
		<div class="pure-control-group">
			<label for="">合計金額</label> <span class="" data-field-name="amount">${fn:escapeXml(sheet.amount)}</span>
		</div>
		<div class="pure-control-group">
			<label for="">税額</label> <span class="" data-field-name="tax">${fn:escapeXml(sheet.tax)}</span>
		</div>
		<div class="pure-control-group">
			<label for="">作成日時</label> <span class=""
				data-field-name="created_at">${fn:escapeXml(sheet.createdAt)}</span>
		</div>
		<div class="pure-control-group">
			<label for="">更新日時</label> <span class=""
				data-field-name="updated_at">${fn:escapeXml(sheet.updatedAt)}</span>
		</div>
	</div>

	<c:forEach items="${sheet.ourOrderSheetDetails}" var="detail"
		varStatus="v">
		<div class="pure-form pure-form-aligned input-detail">
			<div class="pure-control-group">
				<label for="">商品ID</label> <span class="field"
					data-field-name="detail_goods_id${v.index}">${fn:escapeXml(detail.goodsId)}</span>
			</div>
			<div class="pure-control-group">
				<label for="">単価</label> <span class="field"
					data-field-name="detail_price${v.index}">${fn:escapeXml(detail.price)}</span>
			</div>
			<div class="pure-control-group">
				<label for="">個数</label> <span class="field"
					data-field-name="detail_goods_number${v.index}">${fn:escapeXml(detail.goodsNumber)}</span>
			</div>
			<button class="pure-button button-warning delete-detail">削除</button>
		</div>
	</c:forEach>
	<template id="input-detail-template">
	<div class="pure-form pure-form-aligned input-detail">
		<div class="pure-control-group">
			<label for="">商品ID</label> <span class="field"
				data-field-name="detail_goods_id{{v.index}}">{{detail.goodsId}}</span>
		</div>
		<div class="pure-control-group">
			<label for="">単価</label> <span class="field"
				data-field-name="detail_price${v.index}">{{detail.price}}</span>
		</div>
		<div class="pure-control-group">
			<label for="">個数</label> <span class="field"
				data-field-name="detail_goods_number{{v.index}}">{{detail.goodsNumber}}</span>
		</div>
		<button class="pure-button button-warning delete-detail">削除</button>
	</div>
	</template>
	<button class="pure-button pure-button-primary add-detail">商品追加</button>
</div>
<div>
	<a class="pure-button" href="our_orders">発注一覧へ戻る</a>
</div>
<script src="${baseUri}/resource/editUi.js"></script>
<script src="${baseUri}/resource/editSheetUi.js"></script>
<script>
	var ui = new EditUi();
	ui.start();
</script>
