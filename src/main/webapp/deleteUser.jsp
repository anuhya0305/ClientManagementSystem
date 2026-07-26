<%@ page import="com.codegnan.app.javawebapp10.service.UserService"%>
<%@ page import="com.codegnan.app.javawebapp10.service.UserServiceImpl"%>

<%
int userId = Integer.parseInt(request.getParameter("userId"));

UserService userService = new UserServiceImpl();

boolean status = userService.deleteByUserId(userId);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete User</title>

<link rel="stylesheet" href="deleteResult.css">

</head>

<body>

<div class="container">

<%
if(status){
%>

<h2>User Deleted Successfully</h2>

<a href="index.html" class="btn">Home</a>

<%
}
else{
%>

<h2>User Not Found</h2>

<a href="deleteUserForm.html" class="btn">Try Again</a>

<%
}
%>

</div>

</body>
</html>