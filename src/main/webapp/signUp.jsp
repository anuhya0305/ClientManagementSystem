<%@ page import="com.codegnan.app.javawebapp10.dto.UserDto" %>
<%@ page import="com.codegnan.app.javawebapp10.dto.CredentialsDto" %>
<%@ page import="com.codegnan.app.javawebapp10.service.UserService" %>
<%@ page import="com.codegnan.app.javawebapp10.service.UserServiceImpl" %>

<%
String firstName = request.getParameter("firstName");
String lastName = request.getParameter("lastName");
String username = request.getParameter("username");
String loginPassword = request.getParameter("loginPassword");

UserDto userDto = new UserDto();
userDto.setFirstName(firstName);
userDto.setLastName(lastName);

CredentialsDto credentialsDto = new CredentialsDto();
credentialsDto.setUsername(username);
credentialsDto.setLoginPassword(loginPassword);

UserService userService = new UserServiceImpl();

boolean status = userService.signUp(userDto, credentialsDto);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Result</title>

<link rel="stylesheet" href="signUpResult.css">

</head>

<body>

<div class="container">

<%
if(status){
%>

<h2>User Registered Successfully!</h2>

<%
}
else{
%>

<h2>Registration Failed!</h2>

<%
}
%>

<a href="index.html" class="btn">Home</a>

</div>

</body>
</html>