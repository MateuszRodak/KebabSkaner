<%--
  Created by IntelliJ IDEA.
  User: Mateusz - Laptop
  Date: 10.01.2020
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Kebabownia - Platnosc</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<center>
    <h1>Platnosc</h1>
    <div align="left">
        <a href="${context}">Home</a>
        <a href="${context}/edit.jsp">Lista tabel</a>
        <br>
        <c:out value="${message}"/>
    </div>
    <h2>
        <a href="${context}/platnosc?operacja=new&narrowId=${narrowId}">Dodaj nową platnosc</a>
        &nbsp;&nbsp;&nbsp;
        <a href="${context}/platnosc?operacja=list&narrowId=${narrowId}">Lista platnosci</a>

    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>Lista <c:if test="${not empty narrowId}">zawężona</c:if></h2></caption>
        <tr>
            <th>ID</th>
            <th>Restauracja</th>
            <th>Rodzaj Platnosci</th>
            <th></th>
        </tr>
        <c:forEach var="platnosc" items="${listPlatnosc}">
            <tr>
                <td><c:out value="${platnosc.id}"/></td>
                <td><c:out value="${platnosc.restauracja.nazwa}"/>, <c:out value="${platnosc.restauracja.adres.miejscowosc}"/></td>
                <td><c:out value="${platnosc.rodzajPlatnosci}"/></td>


                <td>
                    <a href="${context}/platnosc?operacja=delete&id=<c:out value='${platnosc.id}' />&narrowId=${narrowId}">Usuń</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>