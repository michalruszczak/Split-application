<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="header.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <th>Opis</th>
        <th>Kwota</th>
        <th>Dodał</th>
    </tr>
    <c:forEach items="${bills}" var="bill">
        <tr>
            <td>${bill.description}</td>
            <td>${bill.amount}</td>
            <td>${bill.creatorId}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
