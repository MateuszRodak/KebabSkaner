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
    <c:if test="${restauracja != null}">
    <form action="restauracja" method="post">
        <input type="hidden" name="operacja" value="update"/>
        </c:if>
        <c:if test="${restauracja == null}">
        <form action="restauracja" method="post">
            <input type="hidden" name="operacja" value="insert"/>
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${restauracja != null}">
                            Edytuj restaurację
                        </c:if>
                        <c:if test="${restauracja == null}">
                            Dodaj nową restaurację
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${restauracja != null}">
                    <input type="hidden" name="id" value="<c:out value='${restauracja.id}' />"/>
                </c:if>
                <tr>
                    <th>Nazwa:</th>
                    <td>
                        <input type="text" name="nazwa" size="45"
                               value="<c:out value='${restauracja.nazwa}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Adres:</th>
                    <td>
                        <select name="idAdres">
                            <c:forEach items="${listAdres}" var="adres">
                                <option value="${adres.id}" <c:if test="${adres.id eq restauracja.idAdres}">selected="selected"</c:if>>
                                        ${adres.miejscowosc}, ${adres.ulica} ${adres.nrLokalu}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>Godzina otwarcia:</th>
                    <td>
                        <input type="text" name="godzOtw" size="15"
                               value="<c:out value='${restauracja.godzOtw}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Godzina zamknięcia:</th>
                    <td>
                        <input type="text" name="godzZam" size="15"
                               value="<c:out value='${restauracja.godzZam}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Dowóz:</th>
                    <td>
                        <input type="checkbox" name="dowoz" value="true" <c:if test="${restauracja.dowoz==true}">checked=checked</c:if>>
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