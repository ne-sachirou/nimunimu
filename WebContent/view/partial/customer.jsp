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
	<table>
		<tr>
			<th>ID</th>
			<th>顧客名</th>
			<th>郵便番号</th>
			<th>住所</th>
			<th>電話番号</th>
			<th>FAX番号</th>
			<th>顧客側担当者名</th>
			<th>請求締め日</th>
			<th>与信限度額</th>
		</tr>
		<tr>
			<td class="pk" data-field-name="id">${fn:escapeXml(customer.id)}</td>
			<td class="field" data-field-name="name">${fn:escapeXml(customer.name)}</td>
			<td class="field" data-field-name="zipcode">${fn:escapeXml(customer.zipcode)}</td>
			<td class="field" data-field-name="address">${fn:escapeXml(customer.address)}</td>
			<td class="field" data-field-name="tel">${fn:escapeXml(customer.tel)}</td>
			<td class="field" data-field-name="fax">${fn:escapeXml(customer.fax)}</td>
			<td class="field" data-field-name="person">${fn:escapeXml(customer.person)}</td>
			<td class="field" data-field-name="billing_cutoff_date">${fn:escapeXml(customer.billingCutoffDate)}</td>
			<td class="field" data-field-name="credit_limit">${fn:escapeXml(customer.creditLimit)}</td>
		</tr>
	</table>
</div>
<div>
	<a class="pure-button" href="customers">顧客一覧へ戻る</a>
</div>
<script src="${baseUri}/resource/editUi.js"></script>
<script>
	var ui = new EditUi();
	ui.start();
</script>
