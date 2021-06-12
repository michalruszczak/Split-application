<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Formularz logowania</h1>
<form action="login" method="post">
    <table>
        <tr style="color: red;">
            <td></td>
            <td>${SPRING_SECURITY_LAST_EXCEPTION.message}</td>
        </tr>
        <tr>
            <td>Login:</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>Hasło:</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Zaloguj się"></td>
        </tr>
    </table>
</form>
<h2>Jeśli nie masz konta - Zarejestruj się!</h2><br>
<a href="/user/register">
    <button>Zarejestruj się!</button>
</a>
</body>
</html>
