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
    <title>Kebabownia - Adresy</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<center>
    <h1>Adresy</h1>
    <div align="left">
        <a href="${context}">Home</a>
    </div>
    <h2>
        <a href="${context}/adres?operacja=new">Dodaj nowy adres</a>
        &nbsp;&nbsp;&nbsp;
        <a href="${context}/adres?operacja=list">Lista adresów</a>

    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>Lista</h2></caption>
        <tr>
            <th>ID</th>
            <th>Miejscowość</th>
            <th>Ulica</th>
            <th>Numer lokalu</th>
            <th>Kod</th>
            <th></th>
        </tr>
        <c:forEach var="adres" items="${listAdres}">
            <tr>
                <td><c:out value="${adres.id}"/></td>
                <td><c:out value="${adres.miejscowosc}"/></td>
                <td><c:out value="${adres.ulica}"/></td>
                <td><c:out value="${adres.nrLokalu}"/></td>
                <td><c:out value="${adres.kod}"/></td>
                <td>
                    <a href="${context}/adres?operacja=edit&id=<c:out value='${adres.id}' />">Edytuj</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="${context}/adres?operacja=delete&id=<c:out value='${adres.id}' />">Usuń</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>