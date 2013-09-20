<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JavaEE7 Samples - Social - Content</title>
	</head>
	<body>
		<h2>JavaEE 7 Samples - Social - Content</h2>

		<h4>Sign up</h4>
		<form action="rest/content/signup" method="POST">
			<table>
				<c:if test="${param.signup == 'false'}">
					<tr>
						<td colspan="2"><p style="color:#d22"><b>Failed to sign up: email address already in use!</b></p></td>
					</tr>
				</c:if>
        <tr>
					<td><label for="signup_name">name:</label></td><td><input type="text" name="name" id="signup_name"/></td>
        </tr>
        <tr>
					<td><label for="signup_email">email:</label></td><td><input type="email" name="email" id="signup_email"/></td>
        </tr>
        <tr>
					<td><label for="signup_password">password:</label></td><td><input type="password" name="password" id="signup_password"/></td>
        </tr>
        <tr>
					<td colspan="2"><input type="submit" value="sign up"/></td>
        </tr>
			</table>
		</form>

		<h4>Sign in</h4>
		<form action="rest/content/signin" method="POST">
			<table>
				<c:if test="${param.signin == 'false'}">
					<tr>
						<td colspan="2"><p style="color:#d22"><b>Failed to sign in: invalid email and/or password!</b></p></td>
					</tr>
				</c:if>
				<tr>
					<td><label for="signin_email">email:</label></td><td><input type="text" name="email" id="signin_email"/></td>
				</tr>
				<tr>
					<td><label for="signin_password">password:</label></td><td><input type="password" name="password" id="signin_password"/></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="sign in"/></td>
				</tr>
			</table>
		</form>
	</body>
</html>