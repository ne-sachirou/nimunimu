<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="java.util.*"%>
<%
	String id = (String) request.getAttribute("id");
	String password = (String) request.getAttribute("password");
	Map<String, Exception> errors = (HashMap<String, Exception>) request
			.getAttribute("errors");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Admin Login</title>
</head>
<body>
	<form action="" method="post">
		<%
			if (errors != null && !errors.isEmpty()) {
		%>
		<ul>
			<li>IDかパスワードが異なります。</li>
		</ul>
		<%
			}
		%>
		<div>
			<div>id</div>
			<div>
				<input type="text" name="id"
					value="<%=id == null || id.isEmpty() ? "" : id%>" />
			</div>
		</div>
		<div>
			<div>password</div>
			<div>
				<input type="password" name="password"
					value="<%=password == null || password.isEmpty() ? "" : id%>" />
			</div>
		</div>
		<button type="submit">送信</button>
	</form>
</body>
</html>