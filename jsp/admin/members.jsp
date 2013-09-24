<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="java.util.*, tk.c4se.halt.ih31.nimunimu.model.*"%>
<!-- FIXME: this taglibs has bugs! -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	Member currentMember = (Member) request
			.getAttribute("currentMember");
	List<Member> members = (List<Member>) request
			.getAttribute("members");
%>
<table>
	<tr>
		<th>ID</th>
		<th>権限</th>
		<th>is password reseted</th>
		<th>作成日時</th>
		<th>更新日時</th>
	</tr>
	<%
		for (Member member : members) {
	%>
	<tr>
		<td><a href="member?id=<%=member.getId()%>"><%=member.getId()%></a></td>
		<td><%=member.getAuthority()%></td>
		<td><%=member.getIsPasswordReseted()%></td>
		<td><%=member.getCreatedAt()%></td>
		<td><%=member.getUpdatedAt()%></td>
	</tr>
	<%
		}
	%>
</table>