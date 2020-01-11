<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Kebabownia - Porcje</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<center>
    <h1>Porcje</h1>
    <div align="left">
        <a href="${context}">Home</a>
        <a href="${context}/edit.jsp">Lista tabel</a>
    </div>
    <h2>
        <a href="${context}/porcja?operacja=new">Dodaj nową porcję</a>
        &nbsp;&nbsp;&nbsp;
        <a href="${context}/porcja?operacja=list">Lista porcji</a>

    </h2>
</center>
<div align="center">
    <c:if test="${porcja != null}">
    <form action="porcja" method="post">
        <input type="hidden" name="operacja" value="update"/>
        </c:if>
        <c:if test="${porcja == null}">
        <form action="porcja" method="post">
            <input type="hidden" name="operacja" value="insert"/>
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${porcja != null}">
                            Edytuj porcje
                        </c:if>
                        <c:if test="${porcja == null}">
                            Dodaj nowa porcja
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${porcja != null}">
                    <input type="hidden" name="id" value="<c:out value='${porcja.id}' />"/>
                </c:if>
                <tr>
                    <th>ID_Menu:</th>
                    <td>
                        <input type="text" name="ID_Menu" size="45"
                               value="<c:out value='${porcja.idMenu}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Wielkość:</th>
                    <td>
                        <input type="text" name="wielkosc" size="45"
                               value="<c:out value='${porcja.wielkosc}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Jednostka:</th>
                    <td>
                        <input type="text" name="jednostka" size="5"
                               value="<c:out value='${porcja.jednostka}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Opis:</th>
                    <td>
                        <input type="text" name="opis" size="10"
                               value="<c:out value='${porcja.opis}' />"
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