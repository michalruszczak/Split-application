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
        <th>Nazwa</th>
        <th>Twórca</th>
    </tr>
    <c:forEach items="${events}" var="event">
        <tr>
            <td>${event.name}</td>
            <td>${event.creator.username}</td>
            <td>
                <c:if test="${!event.participant}">
                    <a href="/event/signin/${event.id}">
                        <button>Zapisz się</button>
                    </a>
                </c:if>
            </td>
            <td>
                <a href="/event/${event.id}">
                    <button>Wyświetl wydarzenie</button>
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="/event/add">
    <button>Dodaj wydarzenie</button>
</a>
</body>
</html>
