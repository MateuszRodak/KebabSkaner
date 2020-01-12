<%--
  Created by IntelliJ IDEA.
  User: Mateusz - Laptop
  Date: 05.01.2020
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Kebabownia - Menu</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<center>
    <h1>Menu</h1>
    <div align="left">
        <a href="${context}">Home</a>
    <a href="${context}/edit.jsp">Lista tabel</a>
    <br>
    <c:out value="${message}"/>
    </div>
    <h2>
        <a href="${context}/menu?operacja=new&narrowId=${narrowId}">Dodaj nowe menu</a>
        &nbsp;&nbsp;&nbsp;
        <a href="${context}/menu?operacja=list&narrowId=${narrowId}">Lista menu</a>

    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>Lista <c:if test="${not empty narrowId}">zawężona</c:if></h2></caption>
        <tr>
            <th>ID</th>
            <th>Restauracja</th>
            <th>Nazwa produktu</th>
            <th>Cena</th>
            <th></th>
        </tr>
        <c:forEach var="menu" items="${listMenu}">
            <tr>
                <td><c:out value="${menu.id}"/></td>
                <td><c:out value="${menu.restauracja.nazwa}"/>, <c:out value="${menu.restauracja.adres.miejscowosc}"/></td>
                <td><c:out value="${menu.nazwaProduktu}"/></td>
                <td><c:out value="${menu.cena}"/></td>
                <td>
                    <a href="${context}/menu?operacja=edit&id=<c:out value='${menu.id}' />&narrowId=${narrowId}">Edytuj</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="${context}/menu?operacja=delete&id=<c:out value='${menu.id}' />&narrowId=${narrowId}">Usuń</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>