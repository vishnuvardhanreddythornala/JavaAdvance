<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Detail</title>
</head>
<body>
<h1>User Details</h1>
<p><strong>ID:</strong> ${user.id}</p>
<p><strong>Name:</strong> ${user.name}</p>
<p><strong>Email:</strong> ${user.email}</p>
<br/>
<a href="${pageContext.request.contextPath}/users">Back to User List</a>
</body>
</html>