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
    <a href="${context}/menu?operacja=new">Dodaj nowe menu</a>
    &nbsp;&nbsp;&nbsp;
    <a href="${context}/menu?operacja=list">Lista menu</a>

    </h2>
</center>
<div align="center">
    <c:if test="${menu != null}">
    <form action="menu" method="post">
        <input type="hidden" name="operacja" value="update"/>
        <input type="hidden" name="narrowId" value="${narrowId}"/>
        </c:if>
        <c:if test="${menu == null}">
        <form action="menu" method="post">
            <input type="hidden" name="operacja" value="insert"/>
            <input type="hidden" name="narrowId" value="${narrowId}"/>
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${menu != null}">
                            Edytuj menu
                        </c:if>
                        <c:if test="${menu == null}">
                            Dodaj nowe menu
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${menu != null}">
                    <input type="hidden" name="id" value="<c:out value='${menu.id}' />"/>
                </c:if>
                <tr>
                    <th>Restauracja:</th>
                    <td>
                        <select name="idRest">
                            <c:forEach items="${listRest}" var="restauracja">
                                <option value="${restauracja.id}" <c:if test="${restauracja.id eq menu.idRest}">selected="selected"</c:if>>
                                        ${restauracja.nazwa}, ${restauracja.adres.miejscowosc}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>Nazwa produktu:</th>
                    <td>
                        <input type="text" name="nazwaProduktu" size="45" value="<c:out value='${menu.nazwaProduktu}' />"/>
                    </td>
                </tr>
                <tr>
                    <th>Cena:</th>
                    <td>
                        <input type="text" name="cena" size="15" value="<c:out value='${menu.cena}' />"/>
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