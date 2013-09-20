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

		<p><b>Signed in as ${user.screenName}</b> (<a href="rest/content/signout">sign out</a>)</p>

		<h4>Content</h4>

		<p>
			<a href="create.jsp">add content</a>
		</p>

		<table width="100%">
			<thead>
				<tr>
					<th align="left" width="20%">Author</th>
					<th align="left">Message</th>
					<th align="left" width="20%">Date</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="content" items="${contents}">
					<tr>
						<td valign="top">${content.author.screenName}</td>
						<td valign="top">
							<p><b>${content.title}</b></p>
							<p>${content.content}</p>
						</td>
						<td valign="top">
							<jsp:useBean class="java.util.Date" id="creationDate"/>
							<jsp:setProperty name="creationDate" property="time" value="${content.creationDate}"/>
							<fmt:formatDate value="${creationDate}" pattern="yyyy/MM/dd hh:mma"/>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>
