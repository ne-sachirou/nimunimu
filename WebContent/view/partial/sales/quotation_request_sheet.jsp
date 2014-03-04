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
			<label for="">ID</label> <span class="field pk" data-field-name="id">${fn:escapeXml(sheet.id)}</span>
		</div>
		<div class="pure-control-group">
			<label for="">amount</label> <span class="field"
				data-field-name="amount">${fn:escapeXml(sheet.amount)}</span>
		</div>
		<div class="pure-control-group">
			<label for="">tax</label> <span class="field"
				data-field-name="tax">${fn:escapeXml(sheet.tax)}</span>
		</div>
		<table class="pure-table pure-table-bordered">
		<c:forEach items="${sheet.quotationRequestSheetDetails}" var="details">
			<tr>
				<td>商品ID</td>
				<td>${fn:escapeXml(details.id)}</td>
			</tr>
			<tr>
				<td>価格</td>
				<td>${fn:escapeXml(details.price)}</td>
			</tr>
		</c:forEach>
		</table>
	</div>
</div>
<script src="${baseUri}/resource/editUi.js"></script>
<script>
	var ui = new EditUi();
	ui.start();
</script>
