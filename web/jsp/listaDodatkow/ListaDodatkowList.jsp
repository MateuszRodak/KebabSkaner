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
    <title>Kebabownia - ListaDodatkow</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<center>
    <h1>ListaDodatkow</h1>
    <div align="left">
        <a href="${context}">Home</a>
        <a href="${context}/edit.jsp">Lista tabel</a>
        <br>
        <c:out value="${message}"/>
    </div>
    <h2>
        <a href="${context}/listaDodatkow?operacja=new&narrowId=${narrowId}">Dodaj nowy dodatek</a>
        &nbsp;&nbsp;&nbsp;
        <a href="${context}/listaDodatkow?operacja=list&narrowId=${narrowId}">Lista dodatkow</a>

    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>Lista <c:if test="${not empty narrowId}">zawężona</c:if></h2></caption>
        <tr>
            <th>ID</th>
            <th>Nazwa</th>
            <th></th>
        </tr>
        <c:forEach var="listaDodatkow" items="${listListaDodatkow}">
            <tr>
                <td><c:out value="${listaDodatkow.id}"/></td>
                <td><c:out value="${listaDodatkow.nazwa}"/></td>
                <td>
                    <a href="${context}/listaDodatkow?operacja=delete&id=<c:out value='${listaDodatkow.id}' />&narrowId=${narrowId}">Usuń</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>