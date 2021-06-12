<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@include file="header.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Dodawanie wydarzenia</h1>
<form:form method="post" modelAttribute="event" action="/event/add">
    Nazwa wydarzenia: <form:input path="name"/>
    <input type="submit" value="Utwórz"/>
</form:form>
</body>
</html>
