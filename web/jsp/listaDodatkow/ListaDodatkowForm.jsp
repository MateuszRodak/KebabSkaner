<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Kebabownia - listaDodatkow</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<center>
    <h1>ListaDodatkow</h1>
    <div align="left">
        <a href="${context}">Home</a>
        <a href="${context}/edit.jsp">Lista tabel</a>
    </div>
    <h2>
        <a href="${context}/listaDodatkow?operacja=new">Dodaj nowy dodatek</a>
        &nbsp;&nbsp;&nbsp;
        <a href="${context}/listaDodatkow?operacja=list">Lista dodatkow</a>

    </h2>
</center>
<div align="center">
    <c:if test="${listaDodatkow != null}">
    <form action="listaDodatkow" method="post">
        <input type="hidden" name="operacja" value="update"/>
        <input type="hidden" name="narrowId" value="${narrowId}"/>
        </c:if>
        <c:if test="${listaDodatkow == null}">
        <form action="listaDodatkow" method="post">
            <input type="hidden" name="operacja" value="insert"/>
            <input type="hidden" name="narrowId" value="${narrowId}"/>
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${listaDodatkow != null}">
                            Edytuj listaDodatkow
                        </c:if>
                        <c:if test="${listaDodatkow == null}">
                            Dodaj nowa listaDodatkow
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${listaDodatkow != null}">
                    <input type="hidden" name="id" value="<c:out value='${listaDodatkow.id}' />"/>
                </c:if>
                <tr>
                    <th>Nazwa:</th>
                    <td>
                        <input type="text" name="nazwa" size="45"
                               value="<c:out value='${listaDodatkow.nazwa}' />"
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