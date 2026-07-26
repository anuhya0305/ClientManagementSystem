<%@ page import="com.codegnan.app.javawebapp10.service.UserService" %>
<%@ page import="com.codegnan.app.javawebapp10.service.UserServiceImpl" %>

<%
String username = request.getParameter("username");
String newPassword = request.getParameter("newPassword");

UserService userService = new UserServiceImpl();

boolean status = userService.updateLoginPasswordByUsername(username, newPassword);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Password Update</title>

<link rel="stylesheet" href="passwordUpdateResult.css">

</head>
<body>

<div class="container">

<%
if(status){
%>

<h2>Password Updated Successfully</h2>

<a href="userSignInForm.html" class="btn">Login</a>

<%
}
else{
%>

<h2>Username Not Found</h2>

<a href="forgotPasswordForm.html" class="btn">Try Again</a>

<%
}
%>

</div>

</body>
</html>