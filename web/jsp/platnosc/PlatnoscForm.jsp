<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Kebabownia - platnosc</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<center>
    <h1>Platnosc</h1>
    <div align="left">
        <a href="${context}">Home</a>
        <a href="${context}/edit.jsp">Lista tabel</a>
    </div>
    <h2>
        <a href="${context}/platnosc?operacja=new">Dodaj nową platnosc</a>
        &nbsp;&nbsp;&nbsp;
        <a href="${context}/platnosc?operacja=list">Lista platnosci</a>

    </h2>
</center>
<div align="center">
    <c:if test="${platnosc != null}">
    <form action="platnosc" method="post">
        <input type="hidden" name="operacja" value="update"/>
        <input type="hidden" name="narrowId" value="${narrowId}"/>
        </c:if>
        <c:if test="${platnosc == null}">
        <form action="platnosc" method="post">
            <input type="hidden" name="operacja" value="insert"/>
            <input type="hidden" name="narrowId" value="${narrowId}"/>
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${platnosc != null}">
                            Edytuj platnosc
                        </c:if>
                        <c:if test="${platnosc == null}">
                            Dodaj nowa platnosc
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${platnosc != null}">
                    <input type="hidden" name="id" value="<c:out value='${platnosc.id}' />"/>
                </c:if>
                <tr>
                    <th>Restauracja:</th>
                    <td>
                        <select name="idRest">
                            <c:forEach items="${listRest}" var="restauracja">
                                <option value="${restauracja.id}" <c:if test="${restauracja.id eq platnosc.idRest}">selected="selected"</c:if>>
                                        ${restauracja.nazwa}, ${restauracja.adres.miejscowosc}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>RodzajPlatnosci:</th>
                    <td>
                        <input type="text" name="rodzajPlatnosci" size="45"
                               value="<c:out value='${platnosc.rodzajPlatnosci}' />"
                        />
                    </td>
                </tr>

                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save"/>
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>