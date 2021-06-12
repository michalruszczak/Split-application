<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@include file="header.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Dodawanie rachunku</h1>
<form:form method="post" modelAttribute="bill">
    Opis: <form:input path="description"/>
    Kwota: <form:input path="amount"/>
    <input type="submit" value="Dodaj"/>
</form:form>
</body>
</html>
