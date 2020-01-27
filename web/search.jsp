<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="css/style.css">
<c:set var="context" value="${pageContext.request.contextPath}" />
szukaj
<br>
<br>
<a href="${context}/szukajMenu?operacja=list">Wyszukaj menu</a> <br>
<a href="${context}/szukaj?operacja=list">Wyszukaj menu ze szczegółami</a> <br>
<br>
<br>
<br>
<br>
<a href="edit.jsp">włamanie</a> - gdyby ktoś wszedł z linku do edycji.