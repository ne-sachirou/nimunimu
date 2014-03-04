<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<table class="pure-table pure-table-bordered">
	<tbody>
		<c:forEach items="${notifications}" var="notification">
			<tr>
				<td>${fn:escapeXml(notification.message}</td>
				<td><button class="pure-button pure-button-primary read-button"
						data-notification-id="${notification.id}">既読にする</button></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<script>
	// ES6 Array.from
	if (!Array.from) {
		Array.from = function(list) {
			return [].slice.call(list);
		};
	}

	// ES6 Promise http://c4se.hatenablog.com/entry/2014/01/30/023352
	if (!window.Promise) {
		window.Promise = Q.Promise || Q.promise;
		(function() {
			'use strict';
			var i = 0, iz = 0, keys = Object.keys(Q), key;

			for (i = 0, iz = keys.length; i < iz; ++i) {
				key = keys[i];
				window.Promise[key] = Q[key];
			}
		}());
	}

	Array.from(document.querySelectorAll('.read-button')).forEach(
			function(btn) {
				btn.onclick = function() {
					var notificationId = btn.dataset.notificationId - 0;

					markAsRead(notificationId).then(function() {
						location.reload();
					}, function(err) {
						alert(err.message);
					});
				};
			});

	function markAsRead(notificationId) {
		var req;

		req = new XmlHttpRequest();
		req.open('POST', location.href, true);
		req.setRequestHeader('Content-type',
				'application/x-www-form-urlencoded');
		req.send('id=' + notificationId);
		return new Promise(function(resolve, reject) {
			req.onreadystatechange = function() {
				if (req.readyState === 4) {
					if (req.status !== 200) {
						reject(new Error(req.statusText + '\n'
								+ req.responseText));
					}
					resolve();
				}
			};
		});
	}
</script>