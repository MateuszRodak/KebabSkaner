<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Kebabownia - dodatki do menu</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<center>
    <h1>Dodatki do menu</h1>
    <div align="left">
        <a href="${context}">Home</a>
        <a href="${context}/edit.jsp">Lista tabel</a>
        <br>
        <c:out value="${message}"/>
    </div>
    <h2>
        <a href="${context}/dodatkiMenu?operacja=new">Dodaj nowy dodatek do menu</a>
        &nbsp;&nbsp;&nbsp;
        <a href="${context}/dodatkiMenu?operacja=list">Lista dodatkow do menu</a>

    </h2>
</center>
<div align="center">
    <c:if test="${dodatkiMenu != null}">
    <form action="dodatkiMenu" method="post">
        <input type="hidden" name="operacja" value="update"/>
        <input type="hidden" name="narrowId" value="${narrowId}"/>
        </c:if>
        <c:if test="${dodatkiMenu == null}">
        <form action="dodatkiMenu" method="post">
            <input type="hidden" name="operacja" value="insert"/>
            <input type="hidden" name="narrowId" value="${narrowId}"/>
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${dodatkiMenu != null}">
                            Edytuj dodatkiMenu
                        </c:if>
                        <c:if test="${dodatkiMenu == null}">
                            Dodaj nowa dodatkiMenu
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${dodatkiMenu != null}">
                    <input type="hidden" name="id" value="<c:out value='${dodatkiMenu.id}' />"/>
                </c:if>
                <tr>
                    <th>Produkt:</th>
                    <td>
                        <select name="idMenu">
                            <c:forEach items="${listMenu}" var="menu">
                                <option value="${menu.id}" <c:if test="${menu.id eq dodatkiMenu.idMenu }">selected="selected"</c:if>>
                                        ${menu.nazwaProduktu},
                                        ${menu.restauracja.nazwa},
                                        ${menu.restauracja.adres.miejscowosc},
                                        ${menu.restauracja.adres.ulica},
                                        ${menu.restauracja.adres.nrLokalu}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>Dodatek:</th>
                    <td>
                        <select name="idListyDodatkow">
                            <c:forEach items="${listListaDodatkow}" var="listaDodatkow">
                                <option value="${listaDodatkow.id}" <c:if test="${listaDodatkow.id eq dodatkiMenu.idListyDodatkow}">selected="selected"</c:if>>
                                        ${listaDodatkow.nazwa}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Zapisz"/>
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>