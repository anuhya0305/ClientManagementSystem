<%@ page import="com.codegnan.app.javawebapp10.dto.UserDto"%>
<%@ page import="com.codegnan.app.javawebapp10.service.UserService"%>
<%@ page import="com.codegnan.app.javawebapp10.service.UserServiceImpl"%>

<%
String username = request.getParameter("username");
String loginPassword = request.getParameter("loginPassword");

UserService userService = new UserServiceImpl();

UserDto user = userService.signIn(username, loginPassword);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Result</title>

<link rel="stylesheet" href="signInResult.css">

</head>
<body>

<div class="container">

<%
if(user != null){
%>

<h2>Login Successful</h2>

<p>Welcome <b><%=user.getFirstName()%> <%=user.getLastName()%></b></p>

<a href="index.html" class="btn">Home</a>

<%
}
else{
%>

<h2>Invalid Username or Password</h2>

<a href="userSignInForm.html" class="btn">Try Again</a>

<%
}
%>

</div>

</body>
</html>