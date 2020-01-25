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
    <title>Kebabownia - Wyszukiwarka</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<center>
    <h1>Wyszukaj w menu</h1>
    <div align="left">
        <a href="${context}">Home</a>
        <a href="${context}/search.jsp">Wyszukiwarki</a>
        <br>
        <c:out value="${message}"/>
    </div>
    <h2>

        <a href="${context}/szukajMenu/?operacja=list">Resetuj listę</a>

    </h2>
</center>
<div align="center">

        <form action="szukajMenu" method="post">
            <input type="hidden" name="operacja" value="search"/>

            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                         Podaj parametry wyszukiwania
                    </h2>
                </caption>

                <tr>
                    <th>Nazwa produktu:</th>
                    <td>
                        <input type="text" name="nazwaProduktu" size="45" value="<c:out value='${menu.nazwaProduktu}' />"/>
                    </td>
                </tr>
                <tr>
                    <th>Cena do:</th>
                    <td>
                        <input type="text" name="cena" size="15" value="<c:out value='${menu.cena}' />"/> PLN
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Szukaj"/>
                    </td>
                </tr>
            </table>
        </form>
</div>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>Lista</h2></caption>
        <tr>
            <th>ID</th>
            <th>Restauracja</th>
            <th>Nazwa produktu</th>
            <th>Cena PLN</th>
        </tr>
        <c:forEach var="menuL" items="${listMenu}">
            <tr>
                <td><c:out value="${menuL.id}"/></td>
                <td><c:out value="${menuL.restauracja.nazwa}"/>, <c:out value="${menuL.restauracja.adres.miejscowosc}"/></td>
                <td><c:out value="${menuL.nazwaProduktu}"/></td>
                <td><c:out value="${menuL.cena}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>