<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JavaEE7 Samples - Social - Content</title>
	</head>
	<body>
		<h2>JavaEE 7 Samples - Social - Content</h2>

		<p><b>Signed in as ${user.screenName}</b> (<a href="rest/user/signout">sign out</a>)</p>

		<h4>Messages</h4>

		<p>
			<a href="create.jsp">add message</a>
		</p>

		<table width="100%">
			<thead>
				<tr>
					<th align="left" width="20%">Author</th>
					<th align="left">Message</th>
					<th align="left" width="20%">Date</th>
					<th align="left">Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="message" items="${messages}" varStatus="stat">
					<tr>
						<td valign="top">${message.author.screenName}</td>
						<td valign="top">
							<p><b>${message.title}</b></p>
							<p>${message.content}</p>
						</td>
						<td valign="top">
							<jsp:useBean class="java.util.Date" id="creationDate"/>
							<jsp:setProperty name="creationDate" property="time" value="${message.creationDate}"/>
							<fmt:formatDate value="${creationDate}" pattern="yyyy/MM/dd hh:mma"/>
						</td>
						<td>
							<c:if test="${user.id == message.author.id}"><a href="rest/content/${message.id}/delete" onclick="return confirm('Are you sure you want to delete this message?');">delete</a><br/></c:if>
							<a href="comment.jsp?message=${message.id}">comment</a>
						</td>
					</tr>
					<c:forEach var="comment" items="${comments[message.id]}">
						<tr>
							<td valign="top"><small>${comment.author.screenName}</small></td>
							<td valign="top">
								<p><small>${comment.content}</small></p>
							</td>
							<td valign="top">
								<jsp:useBean class="java.util.Date" id="commentCreationDate"/>
								<jsp:setProperty name="commentCreationDate" property="time" value="${comment.creationDate}"/>
								<small><fmt:formatDate value="${commentCreationDate}" pattern="yyyy/MM/dd hh:mma"/></small>
							</td>
							<td>
								<c:if test="${user.id == comment.author.id}"><a href="rest/content/${comment.id}/delete" onclick="return confirm('Are you sure you want to delete this comment?');">delete</a><br/></c:if>
							</td>
						</tr>
					</c:forEach>
					<c:if test="${not stat.last}"><tr><td colspan="4"><hr/></td></tr></c:if>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>
