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
    <title>Kebabownia - Restauracje</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<center>
    <h1>Restauracje</h1>
    <div align="left">
        <a href="${context}">Home</a>
        <a href="${context}/edit.jsp">Lista tabel</a>
        <br>
        <c:out value="${message}"/>
    </div>
    <h2>
        <a href="${context}/restauracja?operacja=new">Dodaj nową restaurację</a>
        &nbsp;&nbsp;&nbsp;
        <a href="${context}/restauracja?operacja=list">Lista restauracji</a>

    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>Lista</h2></caption>
        <tr>
            <th>ID</th>
            <th>Nazwa</th>
            <th>Adres</th>
            <th>Godzina otwarcia</th>
            <th>Godzina zamknięcia</th>
            <th>Dowóz</th>
            <th></th>
        </tr>
        <c:forEach var="restauracja" items="${listRest}">
            <tr>
                <td><c:out value="${restauracja.id}"/></td>
                <td><c:out value="${restauracja.nazwa}"/></td>
                <td><c:out value="${restauracja.adres.miejscowosc}"/>, <c:out value="${restauracja.adres.ulica}"/> <c:out value="${restauracja.adres.nrLokalu}"/></td>
                <td><c:out value="${restauracja.godzOtw}"/></td>
                <td><c:out value="${restauracja.godzZam}"/></td>
                <td><c:out value="${restauracja.dowoz}"/></td>
                <td>
                    <a href="${context}/restauracja?operacja=edit&id=<c:out value='${restauracja.id}' />">Edytuj</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="${context}/restauracja?operacja=delete&id=<c:out value='${restauracja.id}' />">Usuń</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="${context}/menu?operacja=list&narrowId=<c:out value='${restauracja.id}' />">Menu</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>