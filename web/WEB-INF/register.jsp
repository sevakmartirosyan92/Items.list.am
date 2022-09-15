<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Register</title>
</head>
<body>

<%
  String emailErrorMsg = (String) request.getAttribute("emailErrorMsg");
%>

<%if (emailErrorMsg != null) {%>
<span style="color: red"><%=emailErrorMsg%></span>
<%}%>

<form method="post" action="/register">
  <input type="text" name="name" placeholder="name">
  <input type="text" name="surname" placeholder="surname">
  <input type="email" name="email" placeholder="email">
  <input type="password" name="password" placeholder="password">
  <input type="submit" value="register">
</form>

</body>
</html>