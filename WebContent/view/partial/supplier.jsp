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
			<label for="">ID</label> <span class="pk" data-field-name="id">${fn:escapeXml(supplier.id)}</span>
		</div>
		<div class="pure-control-group">
			<label for="">仕入先名</label> <span class="field" data-field-name="name">${fn:escapeXml(supplier.name)}</span>
		</div>
		<div class="pure-control-group">
			<label for="">郵便番号</label> <span class="field"
				data-field-name="zipcode">${fn:escapeXml(supplier.zipcode)}</span>
		</div>
		<div class="pure-control-group">
			<label for="">住所</label> <span class="field"
				data-field-name="address">${fn:escapeXml(supplier.address)}</span>
		</div>
		<div class="pure-control-group">
			<label for="">電話番号</label> <span class="field" data-field-name="tel">${fn:escapeXml(supplier.tel)}</span>
		</div>
		<div class="pure-control-group">
			<label for="">FAX番号</label> <span class="field" data-field-name="fax">${fn:escapeXml(supplier.fax)}</span>
		</div>
		<div class="pure-control-group">
			<label for="">仕入先側担当者名</label> <span class="field"
				data-field-name="person">${fn:escapeXml(supplier.person)}</span>
		</div>
		<div class="pure-control-group">
			<label for="">請求締め日</label> <span class="field"
				data-field-name="billing_cutoff_date">${fn:escapeXml(supplier.billingCutoffDate)}</span>
		</div>
	</div>
</div>
<div>
	<a class="pure-button" href="suppliers">仕入先一覧へ戻る</a>
</div>
<script src="${baseUri}/resource/editUi.js"></script>
<script>
	var ui = new EditUi();
	ui.start();
</script>
