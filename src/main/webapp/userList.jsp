<%@ page import="java.util.List"%>
<%@ page import="com.codegnan.app.javawebapp10.dto.UserDto"%>
<%@ page import="com.codegnan.app.javawebapp10.service.UserService"%>
<%@ page import="com.codegnan.app.javawebapp10.service.UserServiceImpl"%>

<%
UserService userService = new UserServiceImpl();

List<UserDto> users = userService.getAllUsers();
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>All Users</title>

<link rel="stylesheet" href="userList.css">

</head>

<body>

<div class="container">

<h2>Registered Users</h2>

<table>

<tr>
<th>ID</th>
<th>First Name</th>
<th>Last Name</th>
<th>Username</th>
<th>Password</th>
</tr>

<%
for(UserDto user : users){
%>

<tr>

<td><%=user.getUserId()%></td>

<td><%=user.getFirstName()%></td>

<td><%=user.getLastName()%></td>

<td><%=user.getUsername()%></td>

<td><%=user.getLoginPassword()%></td>

</tr>

<%
}
%>

</table>

<a href="index.html" class="btn">Home</a>

</div>

</body>
</html>