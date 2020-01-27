<%--
Created by IntelliJ IDEA.
User: Mateusz - Laptop
Date: 05.01.2020
Time: 14:34
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="context" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Kebabownia - logowanie</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h1>Logowanie do systemu</h1>
<div align="left">
    <a href="${context}">Home</a>
</div>
<br>
<br>
<br>
<form action="login" method="post">
    Name:<input type="text" name="name"><br>
    Password:<input type="password" name="password"><br>
    <input type="submit" value="Zaloguj">
</form>
<br>
<c:out value="${message}"/>

</body>
</html>