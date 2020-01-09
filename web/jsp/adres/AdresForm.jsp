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
    <c:if test="${adres != null}">
    <form action="adres" method="post">
        <input type="hidden" name="operacja" value="update"/>
        </c:if>
        <c:if test="${adres == null}">
        <form action="adres" method="post">
            <input type="hidden" name="operacja" value="insert"/>
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${adres != null}">
                            Edytuj adres
                        </c:if>
                        <c:if test="${adres == null}">
                            Dodaj nowy adres
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${adres != null}">
                    <input type="hidden" name="id" value="<c:out value='${adres.id}' />"/>
                </c:if>
                <tr>
                    <th>Miejscowośc:</th>
                    <td>
                        <input type="text" name="miejscowosc" size="45"
                               value="<c:out value='${adres.miejscowosc}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Ulica:</th>
                    <td>
                        <input type="text" name="ulica" size="45"
                               value="<c:out value='${adres.ulica}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Numer lokalu:</th>
                    <td>
                        <input type="text" name="nrLokalu" size="5"
                               value="<c:out value='${adres.nrLokalu}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Kod:</th>
                    <td>
                        <input type="text" name="kod" size="10"
                               value="<c:out value='${adres.kod}' />"
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