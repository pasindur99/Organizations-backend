<%--
  Created by IntelliJ IDEA.
  User: pasindu
  Date: 2021-07-20
  Time: 08.42
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.entities.Organization" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Organizations</title>
</head>
<body>

<h1>In organizaton page </h1>
<%
    Organization org1 = (Organization) request.getAttribute("Organizations");
    out.println(org1);
%>

</body>
</html>
