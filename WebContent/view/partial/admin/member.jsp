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
			<label for="">ID</label> <span class="field pk" data-field-name="id">${fn:escapeXml(member.id)}</span>
		</div>
		<div class="pure-control-group">
			<label for="">名前</label> <span class="field" data-field-name="name">${fn:escapeXml(member.name)}</span>
		</div>
		<div class="pure-control-group">
			<label for="">権限</label> <span class="field"
				data-field-name="authority">${fn:escapeXml(member.authority)}</span>
		</div>
		<div class="pure-control-group">
			<label for="">パスワード (debug)</label> <span class="field"
				data-field-name="password">${fn:escapeXml(member.password)}</span>
		</div>
		<label for="">is password reseted (debug)</label>
		<div class="pure-control-group">
			<span class="" data-field-name="is_password_resetted">${fn:escapeXml(member.isPasswordReseted)}</span>
		</div>
	</div>
</div>
<div>
	<a class="pure-button" href="members">社員account一覧へ戻る</a>
</div>
<script src="${baseUri}/resource/editUi.js"></script>
<script>
	var ui = new EditUi();
	ui.start();
</script>