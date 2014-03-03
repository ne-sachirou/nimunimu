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
			<label for="">ID</label> <span class="field pk" data-field-name="id">${fn:escapeXml(goods.id)}</span>
		</div>
		<div class="pure-control-group">
			<label for="">商品名</label> <span class="field" data-field-name="name">${fn:escapeXml(goods.name)}</span>
		</div>
		<div class="pure-control-group">
			<label for="">商品カテゴリーID</label> <span class="field"
				data-field-name="goods_category_id">${fn:escapeXml(goods.goodsCategoryId)}</span>
		</div>
		<div class="pure-control-group">
			<label for="">仕入先ID</label> <span class="field"
				data-field-name="supplier_id">${fn:escapeXml(goods.supplierId)}</span>
		</div>
		<div class="pure-control-group">
			<label for="">単価</label> <span class="field" data-field-name="price">${fn:escapeXml(goods.price)}</span>
		</div>
	</div>
</div>
<div>
	<a class="pure-button" href="goods_list">商品一覧へ戻る</a>
</div>
<script src="${baseUri}/resource/editUi.js"></script>
<script>
	var ui = new EditUi();
	ui.start();
</script>
