<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="header.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Wydarzenie</h1>
<br>ID: <c:out value="${event.id}"></c:out>
<br>Nazwa: <c:out value="${event.name}"></c:out>
<br>Dodał: <c:out value="${event.creator.username}"></c:out>
<h1>Rachunki</h1>
<table>
    <tr>
        <th>Opis</th>
        <th>Kwota</th>
        <th>Płatnik</th>
    </tr>
    <c:forEach items="${event.bills}" var="bill">
        <tr>
            <td>${bill.description}</td>
            <td>${bill.amount}</td>
            <td>${bill.creator.username}</td>
        </tr>
    </c:forEach>
</table>

<c:if test="${event.participant}">
    <a href="/bill/add/${event.id}">
        <button>Dodaj rachunek</button>
    </a>
</c:if>

<h1>Płatnosci</h1>
<table>
    <tr>
        <th>Kwota</th>
        <th>Status</th>
        <th>Od</th>
        <th>Do</th>
    </tr>
    <c:forEach items="${event.payments}" var="payment">
        <tr>
            <td>${payment.amount}</td>
            <td>${payment.status}</td>
            <td>${payment.from.username}</td>
            <td>${payment.to.username}</td>
            <td>
                <c:if test="${payment.from.username == currentUser.username}">
                    <a href="/pay/${payment.id}">
                        <button>Zapłać</button>
                    </a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>

<c:if test="${event.closeable}">
    <a href="/event/close/${event.id}">
        <button>Zamknij wydarzenie</button>
    </a>
</c:if>
</body>
</html>