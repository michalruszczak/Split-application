<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="header.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Płatność zakończona sukcesem</h1>
ID płatności: <%= request.getParameter("paymentId") %>
ID płatnika: <%= request.getParameter("PayerID") %>
</body>
</html>
