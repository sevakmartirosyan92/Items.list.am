<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Login</title>
</head>
<body>

<%
  String loginErrorMmsg = (String) request.getAttribute("loginErrorMmsg");
%>

<%if (loginErrorMmsg != null) {%>
<span style="color: red"><%=loginErrorMmsg%></span>
<%}%>

<form method="post" action="/login">
  <input type="email" name="email" placeholder="email">
  <input type="password" name="password" placeholder="password">
  <input type="submit" value="login">
</form>

</body>
</html>