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
    <title>Kebabownia - Edytowanie</title>
    <link rel="stylesheet" href="css/style.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  </head>
  <body>
  <a href="logout">Logout</a> |
  <a href="${context}">Home</a>
  <hr>
  <c:out value="${message}"/>
  <hr>

<a href="${context}/adres?operacja=list">Baza adresów</a> <br>
<a href="${context}/restauracja?operacja=list">Baza restauracji</a> <br>
<a href="${context}/menu?operacja=list">Baza menu</a> <br>
<a href="${context}/porcja?operacja=list">Baza porcji</a> <br>
<a href="${context}/platnosc?operacja=list">Baza płatności</a> <br>
<a href="${context}/listaDodatkow?operacja=list">Słownik dodatków</a> <br>
<a href="${context}/dodatkiMenu?operacja=list">Dodatki do menu</a> <br>
  </body>
</html>
