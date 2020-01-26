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
<c:set var="opisEnums" value="<%=pl.mr.kebab.model.enums.OpisPorcja.values()%>"/>

<html>
<head>
    <title>Kebabownia - Wyszukiwarka</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<center>
    <h1>Wyszukiwarka kebabów</h1>
    <div align="left">
        <a href="${context}">Home</a>
        <a href="${context}/search.jsp">Wyszukiwarki</a>
        <br>
        <c:out value="${message}"/>
    </div>
    <h2>

        <a href="${context}/szukaj/?operacja=list">Resetuj listę</a>

    </h2>
</center>
<div align="center">

        <form action="szukaj" method="post">
            <input type="hidden" name="operacja" value="search"/>
            <input type="hidden" name="clicked" value="true"/>

            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                         Podaj parametry wyszukiwania
                    </h2>
                </caption>

                <tr>
                    <td>
                        Nazwa produktu: <input type="text" name="nazwaProduktu" size="10" value="<c:out value='${menu.nazwaProduktu}' />"/> *
                    </td>
                    <td>
                        Dowóz: <input type="checkbox" name="dowoz" value="true" <c:if test="${menu.restauracja.dowoz==true}">checked=checked</c:if>>
                    </td>
                    <td>
                        Aktualnie otwarte: <input type="checkbox" name="otwarte" value="true" <c:if test="${menu.restauracja.restOpen==true}">checked=checked</c:if>>
                    </td>
                </tr>
                <tr>
                    <td>
                        Cena do: <input type="text" name="cena" size="5" value="<c:out value='${menu.cena}' />"/> PLN
                    </td>
                    <td>
                        Porcja:
                        <select name="porcja">
                            <option value="">Wszystkie rozmiary</option>
                            <c:forEach items="${opisEnums}" var="opisEnum">
                                <option value="${opisEnum.value}" <c:if test="${opisEnum eq menu.porcjaList[0].opis}">selected="selected"</c:if>>
                                        ${opisEnum.value}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        Dodatek: <input type="text" name="dodatek" size="10" value="<c:out value='${menu.dodatkiMenuList[0].listaDodatkow.nazwa}' />"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="3" align="center">
                        <input type="submit" value="Szukaj"/>
                    </td>
                </tr>
                <c:if test="${param.clicked && empty menu.nazwaProduktu}">
                    <tr>
                        <td colspan="3"><font color="red">
                            Wypełnij wymagane parametry!
                        </font>
                        </td>
                    </tr>
                </c:if>
            </table>
            * - wartość wymagana
        </form>
</div>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>Lista</h2></caption>
        <tr>
            <th>ID</th>
            <th>Restauracja</th>
            <th>Czynne:</th>
            <th>Nazwa produktu</th>
            <th>Cena PLN</th>
            <th>Dowóz</th>
            <th>Rozmiar</th>
            <th>Płatność</th>
            <th>Dodatki</th>
        </tr>
        <c:forEach var="menuL" items="${listMenu}">
            <tr>
                <td><c:out value="${menuL.id}"/></td>
                <td><c:out value="${menuL.restauracja.nazwa}"/>, <c:out value="${menuL.restauracja.adres.miejscowosc}"/></td>
                <td><c:out value="${menuL.restauracja.godzOtw}"/><br><c:out value="${menuL.restauracja.godzZam}"/></td>
                <td><c:out value="${menuL.nazwaProduktu}"/></td>
                <td><c:out value="${menuL.cena}"/></td>
                <td><c:if test="${menuL.restauracja.dowoz}">Tak</c:if><c:if test="${!menuL.restauracja.dowoz}">Nie</c:if></td>
                <td><c:out value="${menuL.porcjaList[0].wielkosc}"/> <c:out value="${menuL.porcjaList[0].jednostka}"/></td>
                <td>
                    <c:forEach items="${menuL.restauracja.platnoscList}" var="elementPlatnosc">
                        ${elementPlatnosc.rodzajPlatnosci},
                    </c:forEach>
                </td>
                <td>
                    <c:forEach items="${menuL.dodatkiMenuList}" var="elementDodatki">
                        ${elementDodatki.listaDodatkow.nazwa}<br>
                    </c:forEach>
                </td>

            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>