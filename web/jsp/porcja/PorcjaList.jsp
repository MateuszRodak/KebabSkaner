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
    <title>Kebabownia - Porcja</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<center>
    <h1>Porcja</h1>
    <div align="left">
        <a href="${context}">Home</a>
    </div>
    <h2>
        <a href="${context}/porcja?operacja=new&narrowId=${narrowId}">Dodaj nową porcje</a>
        &nbsp;&nbsp;&nbsp;
        <a href="${context}/porcja?operacja=list&narrowId=${narrowId}">Lista porcji</a>

    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>Lista <c:if test="${not empty narrowId}">zawężona</c:if></h2></caption>
        <tr>
            <th>ID</th>
            <th>Restauracja</th>
            <th>Produkt</th>
            <th>Wielkosc</th>
            <th>Jednostka</th>
            <th>Opis</th>
            <th></th>
        </tr>
        <c:forEach var="porcja" items="${listPorcja}">
            <tr>
                <td><c:out value="${porcja.id}"/></td>
                <td><c:out value="${porcja.menu.restauracja.nazwa}"/>, <c:out value="${porcja.menu.restauracja.adres.miejscowosc}"/></td>
                <td><c:out value="${porcja.menu.nazwaProduktu}"/></td>
                <td><c:out value="${porcja.wielkosc}"/></td>
                <td><c:out value="${porcja.jednostka}"/></td>
                <td><c:out value="${porcja.opis}"/></td>

                <td>
                    <a href="${context}/porcja?operacja=edit&id=<c:out value='${porcja.id}' />&narrowId=${narrowId}">Edytuj</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="${context}/porcja?operacja=delete&id=<c:out value='${porcja.id}' />&narrowId=${narrowId}">Usuń</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>