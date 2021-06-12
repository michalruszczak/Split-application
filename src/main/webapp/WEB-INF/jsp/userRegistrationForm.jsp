<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@include file="header.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Formularz rejestracyjny</h1>
<form:form method="post" modelAttribute="user"><br>
    Login: <form:input path="username"/><br>
    Email: <form:input path="email"/><br>
    Haslo: <form:password path="password"/><br>
    Imię: <form:input path="firstName"/><br>
    Nazwisko: <form:input path="lastName"/><br>
    <input type="submit" value="Zarejestruj!"/>
</form:form>
<h2>Jeśli masz już konto - zaloguj się!</h2><br>
<a href="/login">
    <button>Zaloguj się!</button>
</a>
</body>
</html>
