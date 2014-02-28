<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div id="editable">
	<div>
		<button class="edit">編集</button>
		<button class="delete">削除</button>
		<button class="save">保存</button>
		<button class="cancel">編集取り消し</button>
	</div>
	<table>
		<tr>
			<th>ID</th>
			<th>名前</th>
			<th>権限</th>
			<th>パスワード (debug)</th>
			<th>is password reseted (debug)</th>
		</tr>
		<tr>
			<td class="field pk" data-field-name="id">${fn:escapeXml(member.id)}</td>
			<td class="field" data-field-name="name">${member.name}</td>
			<td class="field" data-field-name="authority">${member.authority}</td>
			<td class="field" data-field-name="password">${member.password}</td>
			<td class="" data-field-name="is_password_resetted">${member.isPasswordReseted}</td>
		</tr>
	</table>
</div>
<style>
.editing {
	border: 1px solid yellow;
	box-shadow: 0 0 1px yellow;
}
</style>
<script src="${baseUri}/resource/editUi.js"></script>
<script>
	var ui = new EditUi();
	ui.start();
</script>