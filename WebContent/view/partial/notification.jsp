<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div id="editable">
	<div>
		<button class="delete pure-button button-warning">削除</button>
	</div>
	<div class="pure-form pure-form-aligned">
		<div class="pure-control-group">
			<label for="">ID</label> <span class="field pk" data-field-name="id">${fn:escapeXml(notification.id)}</span>
		</div>
		<div class="pure-control-group">
			<label for="">メンバーID</label> <span class="field" data-field-name="menberId">${fn:escapeXml(notification.memberId)}</span>
		</div>
		<div class="pure-control-group">
			<label for="">メッセージ</label> <span class="field"
				data-field-name="message">${fn:escapeXml(notification.message)}</span>
		</div>
	</div>
</div>
<div>
	<a class="pure-button" href="notifications">お報せ一覧へ戻る</a>
</div>
<script src="${baseUri}/resource/editUi.js"></script>
<script>
	var ui = new EditUi();
	ui.start();
</script>
