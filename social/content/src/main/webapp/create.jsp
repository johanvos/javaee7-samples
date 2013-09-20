<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JavaEE7 Samples - Social - Content</title>
	</head>
	<body>
		<h2>JavaEE 7 Samples - Social - Content</h2>

		<h4>Create content</h4>

		<form action="rest/content/create" method="POST">
			<table>
				<tr><td><label for="title">title:</label></td><td><input type="text" id="title" name="title"/></td></tr>
				<tr><td><label for="message">message:</label></td><td><textarea id="message" name="message" rows="10" cols="80"></textarea></td></tr>
				<tr><td colspan="2"><input type="submit" value="create"/></td></tr>
			</table>
		</form>
	</body>
</html>
