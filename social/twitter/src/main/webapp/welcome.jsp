<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JavaEE7 Samples - Social - Twitter</title>
	</head>
	<body>
		<h1>Welcome ${user.firstName}!</h1>

		<p>
			<img src="${user.depiction}"/> <a href="http://twitter.com/${user.screenName}">@${user.screenName}</a>
		</p>
	</body>
</html>
