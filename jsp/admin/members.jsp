<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="java.util.*, tk.c4se.halt.ih31.nimunimu.model.*"%>
<%
	Member currentMember = (Member) request
			.getAttribute("currentMember");
	List<Member> members = (List<Member>) request
			.getAttribute("members");
%>
<table>
	<tr>
		<th>ID</th>
	</tr>
	<%
		for (Member member : members) {
	%>
	<tr>
		<td><%=member.getId()%></td>
	</tr>
	<%
		}
	%>
</table>