<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="context" value="${pageContext.request.contextPath}"/>
<c:set var="opisEnums" value="<%=pl.mr.kebab.model.enums.OpisPorcja.values()%>"/>
<c:set var="jednostkaEnums" value="<%=pl.mr.kebab.model.enums.JednostkaPorcja.values()%>"/>

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
        <br>
        <c:out value="${message}"/>
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
        <input type="hidden" name="narrowId" value="${narrowId}"/>
        </c:if>
        <c:if test="${porcja == null}">
        <form action="porcja" method="post">
            <input type="hidden" name="operacja" value="insert"/>
            <input type="hidden" name="narrowId" value="${narrowId}"/>
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
                    <th>Porcja:</th>
                    <td>
                        <select name="idMenu">
                            <c:forEach items="${listMenu}" var="menu">
                                <option value="${menu.id}" <c:if test="${menu.id eq porcja.idMenu }">selected="selected"</c:if>>
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
                        <select name="jednostka">
                            <c:forEach items="${jednostkaEnums}" var="jednostkaEnum">
                                <option value="${jednostkaEnum.value}" <c:if test="${jednostkaEnum eq porcja.jednostka}">selected="selected"</c:if>>
                                        ${jednostkaEnum.value}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>Opis:</th>
                    <td>
                        <select name="opis">
                            <c:forEach items="${opisEnums}" var="opisEnum">
                                <option value="${opisEnum.value}" <c:if test="${opisEnum eq porcja.opis}">selected="selected"</c:if>>
                                        ${opisEnum.value}
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